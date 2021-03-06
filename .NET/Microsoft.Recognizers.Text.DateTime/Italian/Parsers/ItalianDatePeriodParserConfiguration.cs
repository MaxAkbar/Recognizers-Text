﻿using System.Collections.Immutable;
using System.Text.RegularExpressions;

using Microsoft.Recognizers.Definitions.Italian;

namespace Microsoft.Recognizers.Text.DateTime.Italian
{
    public class ItalianDatePeriodParserConfiguration : BaseOptionsConfiguration, IDatePeriodParserConfiguration
    {
        public int MinYearNum { get; }

        public int MaxYearNum { get; }

        public string TokenBeforeDate { get; }

        #region internalParsers

        public IDateExtractor DateExtractor { get; }

        public IExtractor CardinalExtractor { get; }

        public IExtractor OrdinalExtractor { get; }

        public IDateTimeExtractor DurationExtractor { get; }

        public IExtractor IntegerExtractor { get; }

        public IParser NumberParser { get; }

        public IDateTimeParser DateParser { get; }

        public IDateTimeParser DurationParser { get; }

        #endregion

        #region Regexes

        public Regex MonthFrontBetweenRegex { get; }
        public Regex BetweenRegex { get; }
        public Regex MonthFrontSimpleCasesRegex { get; }
        public Regex SimpleCasesRegex { get; }
        public Regex OneWordPeriodRegex { get; }
        public Regex MonthWithYear { get; }
        public Regex MonthNumWithYear { get; }
        public Regex YearRegex { get; }
        public Regex PastRegex { get; }
        public Regex FutureRegex { get; }
        public Regex FutureSuffixRegex { get; }
        public Regex NumberCombinedWithUnit { get; }
        public Regex WeekOfMonthRegex { get; }
        public Regex WeekOfYearRegex { get; }
        public Regex QuarterRegex { get; }
        public Regex QuarterRegexYearFront { get; }
        public Regex AllHalfYearRegex { get; }
        public Regex SeasonRegex { get; }
        public Regex WhichWeekRegex { get; }
        public Regex WeekOfRegex { get; }
        public Regex MonthOfRegex { get; }
        public Regex InConnectorRegex { get; }
        public Regex WithinNextPrefixRegex { get; }
        public Regex RestOfDateRegex { get; }
        public Regex LaterEarlyPeriodRegex { get; }
        public Regex WeekWithWeekDayRangeRegex { get; }
        public Regex YearPlusNumberRegex { get; }
        public Regex DecadeWithCenturyRegex { get; }
        public Regex YearPeriodRegex { get; }
        public Regex ComplexDatePeriodRegex { get; }
        public Regex RelativeDecadeRegex { get; }
        public Regex ReferenceDatePeriodRegex { get; }
        public Regex AgoRegex { get; }
        public Regex LaterRegex { get; }
        public Regex LessThanRegex { get; }
        public Regex MoreThanRegex { get; }
        public Regex CenturySuffixRegex { get; }

        public static readonly Regex NextPrefixRegex =
            new Regex(@"(prochain|prochaine)\b", RegexOptions.Singleline);

        public static readonly Regex PastPrefixRegex =
            new Regex(@"(dernier)\b", RegexOptions.Singleline);

        public static readonly Regex ThisPrefixRegex =
            new Regex(@"(ce|cette)\b", RegexOptions.Singleline);

        public static readonly Regex RelativeRegex =
            new Regex(DateTimeDefinitions.RelativeRegex, RegexOptions.Singleline);

        Regex IDatePeriodParserConfiguration.NextPrefixRegex => NextPrefixRegex;
        Regex IDatePeriodParserConfiguration.PastPrefixRegex => PastPrefixRegex;
        Regex IDatePeriodParserConfiguration.ThisPrefixRegex => ThisPrefixRegex;
        Regex IDatePeriodParserConfiguration.RelativeRegex => RelativeRegex;
        #endregion

        #region Dictionaries

        public IImmutableDictionary<string, string> UnitMap { get; }

        public IImmutableDictionary<string, int> CardinalMap { get; }

        public IImmutableDictionary<string, int> DayOfMonth { get; }

        public IImmutableDictionary<string, int> MonthOfYear { get; }

        public IImmutableDictionary<string, string> SeasonMap { get; }

        public IImmutableDictionary<string, int> WrittenDecades { get; }

        public IImmutableDictionary<string, int> Numbers { get; }

        public IImmutableDictionary<string, int> SpecialDecadeCases { get; }

        #endregion

        public IImmutableList<string> InStringList { get; }

        public ItalianDatePeriodParserConfiguration(ICommonDateTimeParserConfiguration config) : base(config.Options)
        {
            TokenBeforeDate = DateTimeDefinitions.TokenBeforeDate;
            CardinalExtractor = config.CardinalExtractor;
            OrdinalExtractor = config.OrdinalExtractor;
            IntegerExtractor = config.IntegerExtractor;
            NumberParser = config.NumberParser;
            DurationExtractor = config.DurationExtractor;
            DateExtractor = config.DateExtractor;
            DurationParser = config.DurationParser;
            DateParser = config.DateParser;
            MonthFrontBetweenRegex = ItalianDatePeriodExtractorConfiguration.MonthFrontBetweenRegex;
            BetweenRegex = ItalianDatePeriodExtractorConfiguration.BetweenRegex;
            MonthFrontSimpleCasesRegex = ItalianDatePeriodExtractorConfiguration.MonthFrontSimpleCasesRegex;
            SimpleCasesRegex = ItalianDatePeriodExtractorConfiguration.SimpleCasesRegex;
            OneWordPeriodRegex = ItalianDatePeriodExtractorConfiguration.OneWordPeriodRegex;
            MonthWithYear = ItalianDatePeriodExtractorConfiguration.MonthWithYear;
            MonthNumWithYear = ItalianDatePeriodExtractorConfiguration.MonthNumWithYear;
            YearRegex = ItalianDatePeriodExtractorConfiguration.YearRegex;
            PastRegex = ItalianDatePeriodExtractorConfiguration.PastPrefixRegex;
            FutureRegex = ItalianDatePeriodExtractorConfiguration.NextPrefixRegex;
            FutureSuffixRegex = ItalianDatePeriodExtractorConfiguration.FutureSuffixRegex;
            NumberCombinedWithUnit = ItalianDurationExtractorConfiguration.NumberCombinedWithDurationUnit;
            WeekOfMonthRegex = ItalianDatePeriodExtractorConfiguration.WeekOfMonthRegex;
            WeekOfYearRegex = ItalianDatePeriodExtractorConfiguration.WeekOfYearRegex;
            QuarterRegex = ItalianDatePeriodExtractorConfiguration.QuarterRegex;
            QuarterRegexYearFront = ItalianDatePeriodExtractorConfiguration.QuarterRegexYearFront;
            AllHalfYearRegex = ItalianDatePeriodExtractorConfiguration.AllHalfYearRegex;
            SeasonRegex = ItalianDatePeriodExtractorConfiguration.SeasonRegex;
            WhichWeekRegex = ItalianDatePeriodExtractorConfiguration.WhichWeekRegex;
            WeekOfRegex = ItalianDatePeriodExtractorConfiguration.WeekOfRegex;
            MonthOfRegex = ItalianDatePeriodExtractorConfiguration.MonthOfRegex;
            RestOfDateRegex = ItalianDatePeriodExtractorConfiguration.RestOfDateRegex;
            LaterEarlyPeriodRegex = ItalianDatePeriodExtractorConfiguration.LaterEarlyPeriodRegex;
            WeekWithWeekDayRangeRegex = ItalianDatePeriodExtractorConfiguration.WeekWithWeekDayRangeRegex;
            YearPlusNumberRegex = ItalianDatePeriodExtractorConfiguration.YearPlusNumberRegex;
            DecadeWithCenturyRegex = ItalianDatePeriodExtractorConfiguration.DecadeWithCenturyRegex;
            YearPeriodRegex = ItalianDatePeriodExtractorConfiguration.YearPeriodRegex;
            ComplexDatePeriodRegex = ItalianDatePeriodExtractorConfiguration.ComplexDatePeriodRegex;
            RelativeDecadeRegex = ItalianDatePeriodExtractorConfiguration.RelativeDecadeRegex;
            InConnectorRegex = config.UtilityConfiguration.InConnectorRegex;
            WithinNextPrefixRegex = ItalianDatePeriodExtractorConfiguration.WithinNextPrefixRegex;
            ReferenceDatePeriodRegex = ItalianDatePeriodExtractorConfiguration.ReferenceDatePeriodRegex;
            AgoRegex = ItalianDatePeriodExtractorConfiguration.AgoRegex;
            LaterRegex = ItalianDatePeriodExtractorConfiguration.LaterRegex;
            LessThanRegex = ItalianDatePeriodExtractorConfiguration.LessThanRegex;
            MoreThanRegex = ItalianDatePeriodExtractorConfiguration.MoreThanRegex;
            CenturySuffixRegex = ItalianDatePeriodExtractorConfiguration.CenturySuffixRegex;
            UnitMap = config.UnitMap;
            CardinalMap = config.CardinalMap;
            DayOfMonth = config.DayOfMonth;
            MonthOfYear = config.MonthOfYear;
            SeasonMap = config.SeasonMap;
            WrittenDecades = config.WrittenDecades;
            SpecialDecadeCases = config.SpecialDecadeCases;
        }
        public int GetSwiftDayOrMonth(string text)
        {
            var trimmedText = text.Trim().ToLowerInvariant();
            var swift = 0;

            //TODO: Replace with a regex
            //TODO: Add 'upcoming' key word
            
            // example: "nous serons ensemble cette fois la semaine prochaine" - "We'll be together this time next week"
            if (trimmedText.EndsWith("prochain") || trimmedText.EndsWith("prochaine"))
            {
                swift = 1;
            }

            //TODO: Replace with a regex

            // example: Je l'ai vue pas plus tard que la semaine derniere - "I saw her only last week" 
            if (trimmedText.EndsWith("dernière") || trimmedText.EndsWith("dernières") ||
                trimmedText.EndsWith("derniere") || trimmedText.EndsWith("dernieres")) 
            {
                swift = -1;
            }
            return swift;
        }

        public int GetSwiftYear(string text)
        {
            var trimmedText = text.Trim().ToLowerInvariant();
            var swift = -10;
            if (trimmedText.EndsWith("prochain") || trimmedText.EndsWith("prochaine"))
            {
                swift = 1;
            }

            if (trimmedText.EndsWith("dernières") || trimmedText.EndsWith("dernière") ||
                trimmedText.EndsWith("dernieres") || trimmedText.EndsWith("derniere") || trimmedText.EndsWith("dernier"))
            {
                swift = -1;
            }
            else if (trimmedText.StartsWith("cette"))
            {
                swift = 0;
            }

            return swift;
        }

        public bool IsFuture(string text)
        {
            var trimmedText = text.Trim().ToLowerInvariant();
            return (
                trimmedText.StartsWith("cette") ||
                trimmedText.EndsWith("prochaine") || trimmedText.EndsWith("prochain"));
        }

        public bool IsLastCardinal(string text)
        {
            var trimmedText = text.Trim().ToLowerInvariant();
            return (
                trimmedText.Equals("dernières") || trimmedText.Equals("dernière") ||
                trimmedText.Equals("dernieres") || trimmedText.Equals("derniere")||trimmedText.Equals("dernier"));
        }

        public bool IsMonthOnly(string text)
        {
            var trimmedText = text.Trim().ToLowerInvariant();
            return trimmedText.EndsWith("mois");
        }

        public bool IsMonthToDate(string text)
        {
            var trimmedText = text.Trim().ToLowerInvariant();
            return trimmedText.Equals("mois à ce jour");
        }

        public bool IsWeekend(string text)
        {
            var trimmedText = text.Trim().ToLowerInvariant();
            return (trimmedText.EndsWith("fin de semaine") || trimmedText.EndsWith("le weekend"));
        }

        public bool IsWeekOnly(string text)
        {
            var trimmedText = text.Trim().ToLowerInvariant();
            return (trimmedText.EndsWith("semaine") && !trimmedText.EndsWith("fin de semaine"));
        }

        public bool IsYearOnly(string text)
        {
            var trimmedText = text.Trim().ToLowerInvariant();
            return (trimmedText.EndsWith("années") || trimmedText.EndsWith("ans")
                || (trimmedText.EndsWith("l'annees") || trimmedText.EndsWith("l'annee"))
                );
        }

        public bool IsYearToDate(string text)
        {
            var trimmedText = text.Trim().ToLowerInvariant();
            return (trimmedText.Equals("année à ce jour") || trimmedText.Equals("an à ce jour"));
        }
    }
}
