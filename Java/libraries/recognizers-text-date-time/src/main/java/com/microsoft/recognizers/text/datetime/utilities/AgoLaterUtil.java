package com.microsoft.recognizers.text.datetime.utilities;

import com.google.common.collect.ImmutableMap;
import com.microsoft.recognizers.text.ExtractResult;
import com.microsoft.recognizers.text.datetime.Constants;
import com.microsoft.recognizers.text.datetime.extractors.IDateTimeExtractor;
import com.microsoft.recognizers.text.datetime.parsers.DateTimeParseResult;
import com.microsoft.recognizers.text.datetime.parsers.IDateTimeParser;
import com.microsoft.recognizers.text.datetime.parsers.config.IDateParserConfiguration;
import com.microsoft.recognizers.text.utilities.Match;
import com.microsoft.recognizers.text.utilities.MatchGroup;
import com.microsoft.recognizers.text.utilities.RegExpUtility;
import com.microsoft.recognizers.text.utilities.StringUtility;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class AgoLaterUtil {
    public static DateTimeResolutionResult parseDurationWithAgoAndLater(String text,
                                                                        LocalDateTime referenceTime,
                                                                        IDateTimeExtractor durationExtractor,
                                                                        IDateTimeParser durationParser,
                                                                        ImmutableMap<String, String> unitMap,
                                                                        Pattern unitRegex,
                                                                        IDateTimeUtilityConfiguration utilityConfiguration,
                                                                        IDateParserConfiguration config) {

        DateTimeResolutionResult ret = new DateTimeResolutionResult();
        List<ExtractResult> durationRes = durationExtractor.extract(text, referenceTime);
        if (durationRes.size() > 0) {
            DateTimeParseResult pr = durationParser.parse(durationRes.get(0), referenceTime);
            Match[] matches = RegExpUtility.getMatches(unitRegex, text);
            if (matches.length > 0) {
                String afterStr =
                        text.substring(durationRes.get(0).start + durationRes.get(0).length)
                                .trim().toLowerCase();

                String beforeStr =
                        text.substring(0, durationRes.get(0).start)
                                .trim().toLowerCase();

                AgoLaterMode mode = AgoLaterMode.DATE;
                if (pr.timexStr.contains("T")) {
                    mode = AgoLaterMode.DATETIME;
                }

                if (pr.value != null) {
                    return getAgoLaterResult(pr, afterStr, beforeStr, referenceTime,
                            utilityConfiguration, mode, config);
                }
            }
        }
        return ret;
    }

    private static DateTimeResolutionResult getAgoLaterResult(
            DateTimeParseResult durationParseResult,
            String afterStr,
            String beforeStr,
            LocalDateTime referenceTime,
            IDateTimeUtilityConfiguration utilityConfiguration,
            AgoLaterMode mode,
            IDateParserConfiguration config) {

        DateTimeResolutionResult ret = new DateTimeResolutionResult();
        LocalDateTime resultDateTime = referenceTime;
        String timex = durationParseResult.timexStr;

        if (((DateTimeResolutionResult) durationParseResult.value).getMod() == Constants.MORE_THAN_MOD) {
            ret.setMod(Constants.MORE_THAN_MOD);
        } else if (((DateTimeResolutionResult) durationParseResult.value).getMod() == Constants.LESS_THAN_MOD) {
            ret.setMod(Constants.LESS_THAN_MOD);
        }

        if (MatchingUtil.containsAgoLaterIndex(afterStr, utilityConfiguration.getAgoRegex())) {
            Optional<Match> match = Arrays.stream(RegExpUtility.getMatches(utilityConfiguration.getAgoRegex(), afterStr)).findFirst();
            int swift = 0;

            // Handle cases like "3 days before yesterday"
            if (match.isPresent() && !StringUtility.isNullOrEmpty(match.get().getGroup("day").value)) {
                swift = config.getSwiftDay(match.get().getGroup("day").value);
            }

            resultDateTime = DurationParsingUtil.shiftDateTime(timex, referenceTime.plusDays(swift), false);

            ((DateTimeResolutionResult) durationParseResult.value).setMod(Constants.BEFORE_MOD);
        } else if (MatchingUtil.containsAgoLaterIndex(afterStr, utilityConfiguration.getLaterRegex()) ||
                MatchingUtil.containsTermIndex(beforeStr, utilityConfiguration.getInConnectorRegex())) {
            Optional<Match> match = Arrays.stream(RegExpUtility.getMatches(utilityConfiguration.getLaterRegex(), afterStr)).findFirst();
            int swift = 0;

            // Handle cases like "3 days after tomorrow"
            if (match.isPresent() && !StringUtility.isNullOrEmpty(match.get().getGroup("day").value)) {
                swift = config.getSwiftDay(match.get().getGroup("day").value);
            }

            resultDateTime = DurationParsingUtil.shiftDateTime(timex, referenceTime.plusDays(swift), true);

            ((DateTimeResolutionResult) durationParseResult.value).setMod(Constants.AFTER_MOD);
        }

        if (resultDateTime != referenceTime) {
            if (mode.equals(AgoLaterMode.DATE)) {
                ret.setTimex(DateTimeFormatUtil.luisDate(resultDateTime));
            } else if (mode.equals(AgoLaterMode.DATETIME)) {
                ret.setTimex(DateTimeFormatUtil.luisDateTime(resultDateTime));
            }

            ret.setFutureValue(resultDateTime);
            ret.setPastValue(resultDateTime);

            List<Object> subDateTimeEntities = new ArrayList<>();
            subDateTimeEntities.add(durationParseResult);

            ret.setSubDateTimeEntities(subDateTimeEntities);
            ret.setSuccess(true);
        }

        return ret;
    }

    public static List<Token> extractorDurationWithBeforeAndAfter(String text, ExtractResult er, List<Token> result, IDateTimeUtilityConfiguration utilityConfiguration) {

        int pos = er.start + er.length;
        if (pos <= text.length()) {
            String afterString = text.substring(pos);
            String beforeString = text.substring(0, er.start);
            boolean isTimeDuration = RegExpUtility.getMatches(utilityConfiguration.getTimeUnitRegex(), er.text).length != 0;

            MatchingUtilResult resultIndex = MatchingUtil.getAgoLaterIndex(afterString, utilityConfiguration.getAgoRegex());
            if (resultIndex.result) {
                // We don't support cases like "5 minutes from today" for now
                // Cases like "5 minutes ago" or "5 minutes from now" are supported
                // Cases like "2 days before today" or "2 weeks from today" are also supported
                boolean isDayMatchInAfterString = isDayMatchInAfterString(afterString, utilityConfiguration.getAgoRegex(), "day");

                if (!(isTimeDuration && isDayMatchInAfterString)) {
                    result.add(new Token(er.start, er.start + er.length + resultIndex.index));
                }
            } else {
                resultIndex = MatchingUtil.getAgoLaterIndex(afterString, utilityConfiguration.getLaterRegex());
                if (resultIndex.result) {
                    boolean isDayMatchInAfterString = isDayMatchInAfterString(afterString, utilityConfiguration.getLaterRegex(), "day");

                    if (!(isTimeDuration && isDayMatchInAfterString)) {
                        result.add(new Token(er.start, er.start + er.length + resultIndex.index));
                    }
                } else {
                    resultIndex = MatchingUtil.getTermIndex(beforeString, utilityConfiguration.getInConnectorRegex());
                    if (resultIndex.result) {
                        // For range unit like "week, month, year", it should output dateRange or datetimeRange
                        Optional<Match> match = Arrays.stream(RegExpUtility.getMatches(utilityConfiguration.getRangeUnitRegex(), er.text)).findFirst();
                        if (!match.isPresent()) {
                            if (er.start >= resultIndex.index) {
                                result.add(new Token(er.start - resultIndex.index, er.start + er.length));
                            }
                        }
                    } else {
                        resultIndex = MatchingUtil.getTermIndex(beforeString, utilityConfiguration.getWithinNextPrefixRegex());
                        if (resultIndex.result) {
                            // For range unit like "week, month, year, day, second, minute, hour", it should output dateRange or datetimeRange
                            Optional<Match> matchDateUnitRegex = Arrays.stream(RegExpUtility.getMatches(utilityConfiguration.getDateUnitRegex(), er.text)).findFirst();
                            Optional<Match> matchTimeUnitRegex = Arrays.stream(RegExpUtility.getMatches(utilityConfiguration.getTimeUnitRegex(), er.text)).findFirst();
                            if (!matchDateUnitRegex.isPresent() && !matchTimeUnitRegex.isPresent()) {
                                if (er.start >= resultIndex.index) {
                                    result.add(new Token(er.start - resultIndex.index, er.start + er.length));
                                }
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

    private static boolean isDayMatchInAfterString(String text, Pattern pattern, String group) {

        Optional<Match> match = Arrays.stream(RegExpUtility.getMatches(pattern, text)).findFirst();

        if (match.isPresent()) {
            MatchGroup matchGroup = match.get().getGroup(group);
            return StringUtility.isNullOrEmpty(matchGroup.value);
        }

        return false;
    }

    public enum AgoLaterMode {
        DATE,
        DATETIME
    }
}
