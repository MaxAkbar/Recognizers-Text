package com.microsoft.recognizers.text.datetime.extractors;

import com.microsoft.recognizers.text.ExtractResult;
import com.microsoft.recognizers.text.ParseResult;
import com.microsoft.recognizers.text.datetime.Constants;
import com.microsoft.recognizers.text.datetime.extractors.config.IDateExtractorConfiguration;
import com.microsoft.recognizers.text.datetime.utilities.AgoLaterUtil;
import com.microsoft.recognizers.text.datetime.utilities.DateUtil;
import com.microsoft.recognizers.text.datetime.utilities.Token;
import com.microsoft.recognizers.text.utilities.Match;
import com.microsoft.recognizers.text.utilities.RegExpUtility;
import com.microsoft.recognizers.text.utilities.StringUtility;
import org.javatuples.Pair;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;

public class BaseDateExtractor implements IDateTimeExtractor {

    private final IDateExtractorConfiguration config;

    @Override
    public String getExtractorName() {
        return Constants.SYS_DATETIME_DATE;
    }

    public BaseDateExtractor(IDateExtractorConfiguration config) {
        this.config = config;
    }

    @Override
    public List<ExtractResult> extract(String input) {
        return this.extract(input, LocalDateTime.now());
    }

    @Override
    public List<ExtractResult> extract(String input, LocalDateTime reference) {
        List<Token> tokens = new ArrayList<>();

        tokens.addAll(basicRegexMatch(input));
        tokens.addAll(implicitDate(input));
        tokens.addAll(numberWithMonth(input, reference));
        tokens.addAll(extractRelativeDurationDate(input, reference));

        return Token.mergeAllTokens(tokens, input, getExtractorName());
    }

    // match basic patterns in DateRegexList
    private Collection<Token> basicRegexMatch(String text) {
        List<Token> result = new ArrayList<>();

        for (Pattern regex : config.getDateRegexList()) {
            Match[] matches = RegExpUtility.getMatches(regex, text);

            for (Match match : matches) {
                result.add(new Token(match.index, match.index + match.length));
            }
        }

        return  result;
    }

    // match several other cases
    // including 'today', 'the day after tomorrow', 'on 13'
    private Collection<Token> implicitDate(String text) {
        List<Token> result = new ArrayList<>();

        for (Pattern regex : config.getImplicitDateList()) {
            Match[] matches = RegExpUtility.getMatches(regex, text);

            for (Match match : matches) {
                result.add(new Token(match.index, match.index + match.length));
            }
        }

        return  result;
    }

    // Check every integers and ordinal number for date
    private Collection<Token> numberWithMonth(String text, LocalDateTime reference) {
        List<Token> tokens = new ArrayList<>();

        List<ExtractResult> ers = config.getOrdinalExtractor().extract(text);
        ers.addAll(config.getIntegerExtractor().extract(text));

        for (ExtractResult result : ers) {
            int num;
            try {
                ParseResult parseResult = config.getNumberParser().parse(result);
                num = Float.valueOf(parseResult.value.toString()).intValue();
            } catch (NumberFormatException e) {
                num = 0;
            }

            if (num < 1 || num > 31) {
                continue;
            }

            if (result.start >= 0) {
                // Handling cases like '(Monday,) Jan twenty two'
                String frontStr = text.substring(0, result.start);

                Optional<Match> match = Arrays.stream(RegExpUtility.getMatches(config.getMonthEnd(), frontStr)).findFirst();
                if (match.isPresent()) {
                    int startIndex = match.get().index;
                    int endIndex = match.get().index + match.get().length + result.length;

                    int month = config.getMonthOfYear().getOrDefault(match.get().getGroup("month").value.toLowerCase(), reference.getMonthValue());

                    Pair<Integer, Integer> startEnd = extendWithWeekdayAndYear(startIndex, endIndex, month, num, text, reference);

                    tokens.add(new Token(startEnd.getValue0(), startEnd.getValue1()));
                    continue;
                }

                // Handling cases like 'for the 25th'
                Match[] matches = RegExpUtility.getMatches(config.getForTheRegex(), text);
                boolean isFound = false;

                for (Match matchCase : matches) {
                    if (matchCase != null) {
                        String ordinalNum = matchCase.getGroup("DayOfMonth").value;
                        if (ordinalNum.equals(result.text)) {
                            int endLenght = 0;
                            if (!matchCase.getGroup("end").value.equals("")) {
                                endLenght = matchCase.getGroup("end").value.length();
                            }

                            tokens.add(new Token(matchCase.index, matchCase.index + matchCase.length - endLenght));
                            isFound = true;
                        }
                    }
                }

                if (isFound) {
                    continue;
                }

                // Handling cases like 'Thursday the 21st', which both 'Thursday' and '21st' refer to a same date
                matches = RegExpUtility.getMatches(config.getWeekDayAndDayOfMonthRegex(), text);
                isFound = false;
                for (Match matchCase : matches) {
                    if (matchCase != null) {
                        String ordinalNum = matchCase.getGroup("DayOfMonth").value;
                        if (ordinalNum.equals(result.text)) {
                            // Get week of day for the ordinal number which is regarded as a date of reference month
                            LocalDateTime date = DateUtil.safeCreateFromMinValue(reference.getYear(), reference.getMonthValue(), num);
                            String numWeekDayStr = date.getDayOfWeek().toString().toLowerCase();

                            // Get week day from text directly, compare it with the weekday generated above
                            // to see whether they refer to the same week day
                            String extractedWeekDayStr = matchCase.getGroup("weekday").value.toLowerCase();
                            int numWeekDay = config.getDayOfWeek().get(numWeekDayStr);
                            int extractedWeekDay = config.getDayOfWeek().get(extractedWeekDayStr);

                            if (date != DateUtil.minValue() && numWeekDay == extractedWeekDay) {
                                tokens.add(new Token(matchCase.index, result.start + result.length));
                                isFound = true;
                            }
                        }
                    }
                }

                if (isFound) {
                    continue;
                }

                // Handling cases like '20th of next month'
                String suffixStr = text.substring(result.start + result.length);
                match = Arrays.stream(RegExpUtility.getMatches(config.getRelativeMonthRegex(), suffixStr.trim())).findFirst();
                if (match.isPresent() && match.get().index == 0) {
                    int spaceLen = suffixStr.length() - suffixStr.trim().length();
                    int resStart = result.start;
                    int resEnd = resStart + result.length + spaceLen + match.get().length;

                    // Check if prefix contains 'the', include it if any
                    String prefix = text.substring(0, resStart);
                    Optional<Match> prefixMatch = Arrays.stream(RegExpUtility.getMatches(config.getPrefixArticleRegex(), prefix)).findFirst();
                    if (prefixMatch.isPresent()) {
                        resStart = prefixMatch.get().index;
                    }

                    tokens.add(new Token(resStart, resEnd));
                }

                // Handling cases like 'second Sunday'
                suffixStr = text.substring(result.start + result.length);
                match = Arrays.stream(RegExpUtility.getMatches(config.getWeekDayRegex(), suffixStr.trim())).findFirst();
                if (match.isPresent() && match.get().index == 0 && num <= 5 && result.type.equals("builtin.num.ordinal")) {
                    String weekDayStr = match.get().getGroup("weekday").value.toLowerCase();
                    if (config.getDayOfWeek().containsKey(weekDayStr)) {
                        int spaceLen = suffixStr.length() - suffixStr.trim().length();
                        tokens.add(new Token(result.start, result.start + result.length + spaceLen + match.get().length));
                    }
                }
            }

            // For cases like "I'll go back twenty second of June"
            if (result.start + result.length < text.length()) {
                String afterStr = text.substring(result.start + result.length);

                Optional<Match> match = Arrays.stream(RegExpUtility.getMatches(config.getOfMonth(), afterStr)).findFirst();
                if (match.isPresent()) {
                    int startIndex = result.start;
                    int endIndex = result.start + result.length + match.get().length;

                    int month = config.getMonthOfYear().getOrDefault(match.get().getGroup("month").value.toLowerCase(), reference.getMonthValue());

                    Pair<Integer, Integer> startEnd = extendWithWeekdayAndYear(startIndex, endIndex, month, num, text, reference);
                    tokens.add(new Token(startEnd.getValue0(), startEnd.getValue1()));
                }
            }
        }

        return tokens;
    }

    private Pair<Integer, Integer> extendWithWeekdayAndYear(int startIndex, int endIndex, int month, int day, String text, LocalDateTime reference) {
        int year = reference.getYear();
        int startIndexResult = startIndex;
        int endIndexResult = endIndex;

        // Check whether there's a year
        String suffix = text.substring(endIndexResult);
        Optional<Match> matchYear = Arrays.stream(RegExpUtility.getMatches(config.getYearSuffix(), suffix)).findFirst();

        if (matchYear.isPresent() && matchYear.get().index == 0) {
            year = getYearFromText(matchYear.get());
            endIndexResult += matchYear.get().length;
        }

        LocalDateTime date = DateUtil.safeCreateFromMinValue(year, month, day);

        // Check whether there's a weekday
        String prefix = text.substring(0, startIndexResult);
        Optional<Match> matchWeekDay = Arrays.stream(RegExpUtility.getMatches(config.getWeekDayEnd(), prefix)).findFirst();
        if (matchWeekDay.isPresent()) {
            // Get weekday from context directly, compare it with the weekday extraction above
            // to see whether they are referred to the same weekday
            String extractedWeekDayStr = matchWeekDay.get().getGroup("weekday").value.toLowerCase();
            String numWeekDayStr = date.getDayOfWeek().toString().toLowerCase();

            if (config.getDayOfWeek().containsKey(numWeekDayStr) && config.getDayOfWeek().containsKey(extractedWeekDayStr)) {
                int weekDay1 = config.getDayOfWeek().get(numWeekDayStr);
                int weekday2 = config.getDayOfWeek().get(extractedWeekDayStr);
                if (date != DateUtil.minValue() && weekDay1 == weekday2) {
                    startIndexResult = matchWeekDay.get().index;
                }

            }
        }

        return new Pair<>(startIndexResult, endIndexResult);
    }

    // TODO: Remove the parsing logic from here
    private Collection<Token> extractRelativeDurationDate(String text, LocalDateTime reference) {
        List<Token> tokens = new ArrayList<>();

        List<ExtractResult> durations = config.getDurationExtractor().extract(text, reference);

        for (ExtractResult duration : durations) {
            // if it is a multiple duration but its type is not equal to Date, skip it here
            if (isMultipleDuration(duration) && !isMultipleDurationDate(duration)) {
                continue;
            }

            // Some types of duration can be compounded with "before", "after" or "from" suffix to create a "date"
            // While some other types of durations, when compounded with such suffix, it will not create a "date", but create a "dateperiod"
            // For example, durations like "3 days", "2 weeks", "1 week and 2 days", can be compounded with such suffix to create a "date"
            // But "more than 3 days", "less than 2 weeks", when compounded with such suffix, it will become cases like "more than 3 days from today" which is a "dateperiod", not a "date"
            // As this parent method is aimed to extract RelativeDurationDate, so for cases with "more than" or "less than", we remove the prefix so as to extract the expected RelativeDurationDate
            if (isInequalityDuration(duration)) {
                duration = stripInequalityDuration(duration);
            }

            Optional<Match> match = Arrays.stream(RegExpUtility.getMatches(config.getDateUnitRegex(), duration.text)).findFirst();

            if (match.isPresent()) {
                tokens = AgoLaterUtil.extractorDurationWithBeforeAndAfter(text, duration, tokens, config.getUtilityConfiguration());
            }

        }

        return tokens;
    }

    private ExtractResult stripInequalityDuration(ExtractResult er) {
        ExtractResult result = er;
        result = stripInequalityPrefix(result, config.getMoreThanRegex());
        result = stripInequalityPrefix(result, config.getLessThanRegex());
        return  result;
    }

    private ExtractResult stripInequalityPrefix(ExtractResult er, Pattern regex) {
        ExtractResult result = er;
        Optional<Match> match = Arrays.stream(RegExpUtility.getMatches(regex, er.text)).findFirst();

        if (match.isPresent()) {
            int originalLength = er.text.length();
            String text = er.text.replace(match.get().value, "").trim();
            int start = er.start + originalLength - text.length();
            int length = text.length();
            String data = "";
            result = er.withText(text).withStart(start).withLength(length).withData(data);
        }

        return result;
    }

    // Cases like "more than 3 days", "less than 4 weeks"
    private boolean isInequalityDuration(ExtractResult er) {
        return er.data != null && (er.data.toString().equals(Constants.MORE_THAN_MOD) || er.data.toString().equals(Constants.LESS_THAN_MOD));
    }

    private boolean isMultipleDurationDate(ExtractResult er) {
        return er.data != null && er.data.toString().equals(Constants.MultipleDuration_Date);
    }

    private boolean isMultipleDuration(ExtractResult er) {
        return er.data != null && er.data.toString().startsWith(Constants.MultipleDuration_Prefix);
    }

    public int getYearFromText(Match match) {
        int year = Constants.InvalidYear;

        String yearStr = match.getGroup("year").value;
        if (!StringUtility.isNullOrEmpty(yearStr)) {
            year = Integer.parseInt(yearStr);
            if (year < 100 && year >= Constants.MinTwoDigitYearPastNum) {
                year += 1900;
            } else if (year >= 0 && year < Constants.MaxTwoDigitYearFutureNum) {
                year += 2000;
            }
        } else {
            String firstTwoYearNumStr = match.getGroup("firsttwoyearnum").value;
            if (!StringUtility.isNullOrEmpty(firstTwoYearNumStr)) {
                int start = match.getGroup("firsttwoyearnum").index;
                int length = match.getGroup("firsttwoyearnum").length;

                ExtractResult er = new ExtractResult(start, length, firstTwoYearNumStr, null, null);

                Object numberParsed = this.config.getNumberParser().parse(er).value;
                int firstTwoYearNum = Float.valueOf(numberParsed != null ? numberParsed.toString() : "0").intValue();

                int lastTwoYearNum = 0;
                String lastTwoYearNumStr = match.getGroup("lasttwoyearnum").value;
                if (!StringUtility.isNullOrEmpty(lastTwoYearNumStr)) {
                    er = er.withText(lastTwoYearNumStr);
                    er = er.withStart(match.getGroup("lasttwoyearnum").index);
                    er = er.withLength(match.getGroup("lasttwoyearnum").length);

                    Object parsed = this.config.getNumberParser().parse(er).value;
                    lastTwoYearNum = Float.valueOf(parsed != null ? parsed.toString() : "0").intValue();
                }

                // Exclude pure number like "nineteen", "twenty four"
                if (firstTwoYearNum < 100 && lastTwoYearNum == 0 || firstTwoYearNum < 100 && firstTwoYearNum % 10 == 0 && lastTwoYearNumStr.trim().split(" ").length == 1) {
                    year = Constants.InvalidYear;
                    return year;
                }

                if (firstTwoYearNum >= 100) {
                    year = firstTwoYearNum + lastTwoYearNum;
                } else {
                    year = firstTwoYearNum * 100 + lastTwoYearNum;
                }
            }
        }

        return year;
    }
}
