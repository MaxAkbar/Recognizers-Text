// ------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
// ------------------------------------------------------------------------------

package com.microsoft.recognizers.text.datetime.resources;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class EnglishTimeZone {

    public static final String DirectUtcRegex = "(utc|gmt)(\\s*[+\\-\\u00B1]?\\s*[\\d]{1,2}(\\s*:\\s*[\\d]{1,2})?)?";

    public static final String AbbreviationsRegex = "(\\b)(AFT|AKST|AKDT|ABST|AST|ARBST|ARST|ART|ADT|ACST|AEST|AEDT|AZT|AZST|AZOT|AZOST|BST|CCST|CT|CVT|AMT|AMST|ACDT|CAST|CADT|BTT|CBT|CBST|CET|CEST|SBT|CST|CDT|CSTM|CDTM|BIT|ET|EAT|EET|EEST|ESAT|ESAST|EST|EDT|ESTM|EDTM|EGST|YEKT|FJT|FJST|GET|GMT|GNST|GNDT|GST|GTBST|HAST|HST|HADT|HDT|IST|IRST|IRDT|ISST|ISDT|JST|JDT|PETT|KST|LINT|MAGT|MAGST|MUT|MAT|MEST|MVST|MOST|WEST|MST|MDT|MSTM|MDTM|MYST|NCAST|NMST|NMDT|NPT|NZST|NZDT|NST|NDT|IRKT|KRAT|PSAT|PSAST|PST|PDT|PSTM|PDTM|PKT|PYT|PYST|RST|MSK|SAEST|SAPST|SAWST|SST|SMST|THA|SNST|SAST|SLT|SGT|TIST|TAST|TADT|TST|TOT|TOST|TRT|ULAT|UTC|VET|VLAT|AWST|WAT|ECT|WAST|WET|WPST|YAKT|PDST|PT|AWDT|COT|HKT)(\\b)";

    public static final String FullNameRegex = "(Afghanistan Standard Time|Alaskan Standard Time|Arab Standard Time|Arabian Standard Time|Arabic Standard Time|Argentina Standard Time|Atlantic Standard Time|AUS Central Standard Time|AUS Eastern Standard Time|Azerbaijan Standard Time|Azores Standard Time|Bahia Standard Time|Bangladesh Standard Time|Belarus Standard Time|Canada Central Standard Time|Cape Verde Standard Time|Caucasus Standard Time|Cen. Australia Standard Time|Central|Central America Standard Time|Central Asia Standard Time|Central Brazilian Standard Time|Central Europe Standard Time|Central European Standard Time|Central Pacific Standard Time|Central Standard Time|Central Standard Time|China Standard Time|Beijing time|Shanghai time|Shenzhen time|Suzhou time|Tianjian time|Chengdu time|Guangzhou time|Wuxi time|Xiamen time|Chongqing time|Shenyang time|China Time|Dateline Standard Time|E. Africa Standard Time|E. Australia Standard Time|E. Europe Standard Time|E. South America Standard Time|Eastern Time|Eastern Standard Time|Eastern Standard Time|Egypt Standard Time|Ekaterinburg Standard Time|Fiji Standard Time|FLE Standard Time|Georgian Standard Time|GMT Standard Time|Greenland Standard Time|Greenwich Standard Time|GTB Standard Time|Hawaiian Standard Time|India Standard Time|Iran Standard Time|Israel Standard Time|Jordan Standard Time|Kaliningrad Standard Time|Kamchatka Standard Time|Korea Standard Time|Libya Standard Time|Line Islands Standard Time|Magadan Standard Time|Mauritius Standard Time|Mid-Atlantic Standard Time|Middle East Standard Time|Montevideo Standard Time|Morocco Standard Time|Mountain Standard Time|Mountain Standard Time|Myanmar Standard Time|N. Central Asia Standard Time|Namibia Standard Time|Nepal Standard Time|New Zealand Standard Time|Newfoundland Standard Time|North Asia East Standard Time|North Asia Standard Time|North Korea Standard Time|Pacific SA Standard Time|Pacific Standard Time|Redmond time|Seattle time|Bellevue time|Pacific Daylight Time|Pacific Time|Pacific Standard Time|Pakistan Standard Time|Paraguay Standard Time|Romance Standard Time|Russia Time Zone 10|Russia Time Zone 11|Russia Time Zone 3|Russian Standard Time|SA Eastern Standard Time|SA Pacific Standard Time|SA Western Standard Time|Samoa Standard Time|SE Asia Standard Time|Singapore\\s+(Standard\\s+)?Time|South Africa Standard Time|Sri Lanka Standard Time|Syria Standard Time|Taipei Standard Time|Tasmania Standard Time|Tokyo Standard Time|Tonga Standard Time|Turkey Standard Time|Ulaanbaatar Standard Time|US Eastern Standard Time|US Mountain Standard Time|Mountain|UTC|UTC+12|UTC-02|UTC-11|Venezuela Standard Time|Vladivostok Standard Time|W. Australia Standard Time|W. Central Africa Standard Time|W. Europe Standard Time|West Asia Standard Time|West Pacific Standard Time|Yakutsk Standard Time|Pacific Daylight Saving Time|Austrialian Western Daylight Time|Colombia Time|Hong Kong Time|Austrialian West Daylight Time|Pacific|Eastern)";

    public static final String LocationTimeSuffixRegex = "((\\s+|-)(timezone|time))";

    public static final List<String> AmbiguousTimezoneList = Arrays.asList("bit", "get", "art", "cast", "eat", "lint", "mat", "most", "west", "vet", "wet", "cot", "pt", "et", "eastern", "pacific", "central", "mountain");

    public static final ImmutableMap<String, Integer> AbbrToMinMapping = ImmutableMap.<String, Integer>builder()
        .put("aft", 270)
        .put("akst", -540)
        .put("akdt", -480)
        .put("abst", 180)
        .put("ast", -10000)
        .put("arbst", 180)
        .put("arst", 180)
        .put("art", -180)
        .put("adt", -10000)
        .put("acst", 570)
        .put("aest", 600)
        .put("aedt", 660)
        .put("azt", 240)
        .put("azst", 300)
        .put("azot", -60)
        .put("azost", 0)
        .put("bst", -10000)
        .put("ccst", -360)
        .put("ct", -360)
        .put("cvt", -60)
        .put("amt", -10000)
        .put("amst", -10000)
        .put("acdt", 630)
        .put("cast", 480)
        .put("cadt", -360)
        .put("btt", 360)
        .put("cbt", -240)
        .put("cbst", -240)
        .put("cet", 60)
        .put("cest", 120)
        .put("sbt", 660)
        .put("cst", -10000)
        .put("cdt", -10000)
        .put("cstm", -360)
        .put("cdtm", -360)
        .put("bit", -720)
        .put("et", -240)
        .put("eat", 180)
        .put("eet", 120)
        .put("esat", -180)
        .put("esast", -180)
        .put("est", -300)
        .put("edt", -240)
        .put("estm", -300)
        .put("edtm", -300)
        .put("egst", 0)
        .put("yekt", 300)
        .put("fjt", 720)
        .put("fjst", 780)
        .put("get", 240)
        .put("gmt", 0)
        .put("gnst", -180)
        .put("gndt", -180)
        .put("gst", -10000)
        .put("gtbst", 120)
        .put("hast", -600)
        .put("hst", -600)
        .put("hadt", -540)
        .put("hdt", -540)
        .put("ist", -10000)
        .put("irst", 210)
        .put("irdt", 270)
        .put("isst", 120)
        .put("isdt", 120)
        .put("jst", 540)
        .put("jdt", 120)
        .put("eest", 180)
        .put("pett", 720)
        .put("kst", -10000)
        .put("lint", 840)
        .put("magt", 660)
        .put("magst", 720)
        .put("mut", 240)
        .put("mat", -120)
        .put("mest", 120)
        .put("mvst", -180)
        .put("most", 0)
        .put("west", -10000)
        .put("mst", -420)
        .put("mdt", -360)
        .put("mstm", -420)
        .put("mdtm", -420)
        .put("myst", 390)
        .put("ncast", 420)
        .put("nmst", 60)
        .put("nmdt", 60)
        .put("npt", 345)
        .put("nzst", 720)
        .put("nzdt", 780)
        .put("nst", -210)
        .put("ndt", -150)
        .put("irkt", 480)
        .put("krat", 420)
        .put("psat", -240)
        .put("psast", -240)
        .put("pst", -480)
        .put("pdt", -420)
        .put("pstm", -480)
        .put("pdtm", -480)
        .put("pkt", 300)
        .put("pyt", -10000)
        .put("pyst", -10000)
        .put("rst", 60)
        .put("msk", 180)
        .put("saest", -180)
        .put("sapst", -300)
        .put("sawst", -240)
        .put("sst", -10000)
        .put("smst", 780)
        .put("tha", 420)
        .put("snst", 480)
        .put("sast", 120)
        .put("slt", 330)
        .put("sgt", 480)
        .put("tist", 480)
        .put("tast", 600)
        .put("tadt", 600)
        .put("tst", 540)
        .put("tot", 780)
        .put("tost", 840)
        .put("trt", 180)
        .put("ulat", 480)
        .put("utc", 0)
        .put("vet", -240)
        .put("vlat", 600)
        .put("awst", 480)
        .put("wat", -10000)
        .put("ect", -10000)
        .put("wast", 120)
        .put("wet", 0)
        .put("wpst", 600)
        .put("yakt", 540)
        .put("pt", -420)
        .put("pdst", -420)
        .put("awdt", 540)
        .put("cot", -300)
        .put("hkt", 480)
        .build();

    public static final ImmutableMap<String, Integer> FullToMinMapping = ImmutableMap.<String, Integer>builder()
        .put("beijing time", 480)
        .put("shanghai time", 480)
        .put("shenzhen time", 480)
        .put("suzhou time", 480)
        .put("tianjian time", 480)
        .put("chengdu time", 480)
        .put("guangzhou time", 480)
        .put("wuxi time", 480)
        .put("xiamen time", 480)
        .put("chongqing time", 480)
        .put("shenyang time", 480)
        .put("china time", 480)
        .put("redmond time", -480)
        .put("seattle time", -480)
        .put("bellevue time", -480)
        .put("pacific daylight time", -420)
        .put("pacific time", -480)
        .put("afghanistan standard time", 270)
        .put("alaskan standard time", -540)
        .put("arab standard time", 180)
        .put("arabian standard time", 180)
        .put("arabic standard time", 180)
        .put("argentina standard time", -180)
        .put("atlantic standard time", -240)
        .put("aus central standard time", 570)
        .put("aus eastern standard time", 600)
        .put("azerbaijan standard time", 240)
        .put("azores standard time", -60)
        .put("bahia standard time", -180)
        .put("bangladesh standard time", 360)
        .put("belarus standard time", 180)
        .put("canada central standard time", -360)
        .put("cape verde standard time", -60)
        .put("caucasus standard time", 240)
        .put("cen. australia standard time", 570)
        .put("central america standard time", -360)
        .put("central asia standard time", 360)
        .put("central brazilian standard time", -240)
        .put("central europe standard time", 60)
        .put("central european standard time", 60)
        .put("central pacific standard time", 660)
        .put("central standard time", -360)
        .put("central standard time (mexico)", -360)
        .put("china standard time", 480)
        .put("dateline standard time", -720)
        .put("e. africa standard time", 180)
        .put("e. australia standard time", 600)
        .put("e. europe standard time", 120)
        .put("e. south america standard time", -180)
        .put("central", -300)
        .put("eastern", -240)
        .put("eastern standard time", -300)
        .put("eastern standard time (mexico)", -300)
        .put("egypt standard time", 120)
        .put("ekaterinburg standard time", 300)
        .put("fiji standard time", 720)
        .put("fle standard time", 120)
        .put("georgian standard time", 240)
        .put("gmt standard time", 0)
        .put("greenland standard time", -180)
        .put("greenwich standard time", 0)
        .put("gtb standard time", 120)
        .put("hawaiian standard time", -600)
        .put("india standard time", 330)
        .put("iran standard time", 210)
        .put("israel standard time", 120)
        .put("jordan standard time", 120)
        .put("kaliningrad standard time", 120)
        .put("kamchatka standard time", 720)
        .put("korea standard time", 540)
        .put("libya standard time", 120)
        .put("line islands standard time", 840)
        .put("magadan standard time", 660)
        .put("mauritius standard time", 240)
        .put("mid-atlantic standard time", -120)
        .put("middle east standard time", 120)
        .put("montevideo standard time", -180)
        .put("morocco standard time", 0)
        .put("mountain", -360)
        .put("mountain standard time", -420)
        .put("mountain standard time (mexico)", -420)
        .put("myanmar standard time", 390)
        .put("n. central asia standard time", 420)
        .put("namibia standard time", 60)
        .put("nepal standard time", 345)
        .put("new zealand standard time", 720)
        .put("newfoundland standard time", -210)
        .put("north asia east standard time", 480)
        .put("north asia standard time", 420)
        .put("north korea standard time", 510)
        .put("pacific", -420)
        .put("pacific sa standard time", -240)
        .put("pacific standard time", -480)
        .put("pacific standard time (mexico)", -480)
        .put("pakistan standard time", 300)
        .put("paraguay standard time", -240)
        .put("romance standard time", 60)
        .put("russia time zone 10", 660)
        .put("russia time zone 11", 720)
        .put("russia time zone 3", 240)
        .put("russian standard time", 180)
        .put("sa eastern standard time", -180)
        .put("sa pacific standard time", -300)
        .put("sa western standard time", -240)
        .put("samoa standard time", -660)
        .put("se asia standard time", 420)
        .put("singapore standard time", 480)
        .put("singapore time", 480)
        .put("south africa standard time", 120)
        .put("sri lanka standard time", 330)
        .put("syria standard time", 120)
        .put("taipei standard time", 480)
        .put("tasmania standard time", 600)
        .put("tokyo standard time", 540)
        .put("tonga standard time", 780)
        .put("turkey standard time", 180)
        .put("ulaanbaatar standard time", 480)
        .put("us eastern standard time", -300)
        .put("us mountain standard time", -420)
        .put("venezuela standard time", -240)
        .put("vladivostok standard time", 600)
        .put("w. australia standard time", 480)
        .put("w. central africa standard time", 60)
        .put("w. europe standard time", 60)
        .put("west asia standard time", 300)
        .put("west pacific standard time", 600)
        .put("yakutsk standard time", 540)
        .put("pacific daylight saving time", -420)
        .put("austrialian western daylight time", 540)
        .put("austrialian west daylight time", 540)
        .put("colombia time", -300)
        .put("hong kong time", 480)
        .build();

    public static final List<String> MajorLocations = Arrays.asList("Aberdeen", "Adelaide", "Anaheim", "Atlanta", "Auckland", "Austin", "Baltimore", "Baton Rouge", "Beijing", "Belfast", "Birmingham", "Bolton", "Boston", "Bournemouth", "Bradford", "Brisbane", "Bristol", "Calgary", "Canberra", "Cardiff", "Charlotte", "Chicago", "Christchurch", "Colchester", "Colorado Springs", "Coventry", "Dallas", "Denver", "Derby", "Detroit", "Dublin", "Dudley", "Dunedin", "Edinburgh", "Edmonton", "El Paso", "Glasgow", "Gold Coast", "Hamilton", "Hialeah", "Houston", "Ipswich", "Jacksonville", "Jersey City", "Kansas City", "Kingston-upon-Hull", "Leeds", "Leicester", "Lexington", "Lincoln", "Liverpool", "London", "Long Beach", "Los Angeles", "Louisville", "Lubbock", "Luton", "Madison", "Manchester", "Mansfield", "Melbourne", "Memphis", "Mesa", "Miami", "Middlesbrough", "Milton Keynes", "Minneapolis", "Montréal", "Nashville", "New Orleans", "New York City", "Newark", "Newcastle-upon-Tyne", "Northampton", "Norwich", "Nottingham", "Oklahoma City", "Oldham", "Omaha", "Orlando", "Ottawa", "Perth", "Peterborough", "Philadelphia", "Phoenix", "Plymouth", "Portland", "Portsmouth", "Preston", "Québec City", "Raleigh", "Reading", "Richmond", "San Antonio", "San Diego", "San Francisco", "San José", "Santa Ana", "Seattle", "Sheffield", "Southampton", "Southend-on-Sea", "Spokane", "St Louis", "St Paul", "St Petersburg", "Stockton", "Stockton-on-Tees", "Stoke-on-Trent", "Sunderland", "Swansea", "Swindon", "Sydney", "Tampa", "Tauranga", "Telford", "Toronto", "Vancouver", "Virginia Beach", "Walsall", "Warrington", "Washington", "Wellington", "Wolverhampton", "York", "Abilene", "Akron", "Albuquerque", "Alexandria", "Allentown", "Amarillo", "Anchorage", "Ann Arbor", "Antioch", "Arlington", "Arvada", "Athens", "Augusta", "Aurora", "Bakersfield", "Beaumont", "Bellevue", "Berkeley", "Billings", "Boise", "Boulder", "Bridgeport", "Broken Arrow", "Brownsville", "Buffalo", "Burbank", "Cambridge", "Cape Coral", "Carlsbad", "Carrollton", "Cary", "Cedar Rapids", "Centennial", "Chandler", "Charleston", "Chattanooga", "Chesapeake", "Chula Vista", "Cincinnati", "Clarksville", "Clearwater", "Cleveland", "Clovis", "College Station", "Columbia", "Columbus", "Concord", "Coral Springs", "Corona", "Costa Mesa", "Daly City", "Davenport", "Dayton", "Denton", "Des Moines", "Downey", "Durham", "Edison", "El Cajon", "El Monte", "Elgin", "Elizabeth", "Elk Grove", "Erie", "Escondido", "Eugene", "Evansville", "Everett", "Fairfield", "Fargo", "Farmington Hills", "Fayetteville", "Fontana", "Fort Collins", "Fort Lauderdale", "Fort Wayne", "Fort Worth", "Fremont", "Fresno", "Frisco", "Fullerton", "Gainesville", "Garden Grove", "Garland", "Gilbert", "Glendale", "Grand Prairie", "Grand Rapids", "Green Bay", "Greensboro", "Gresham", "Hampton", "Hartford", "Hayward", "Henderson", "High Point", "Hollywood", "Honolulu", "Huntington Beach", "Huntsville", "Independence", "Indianapolis", "Inglewood", "Irvine", "Irving", "Jackson", "Joliet", "Kent", "Killeen", "Knoxville", "Lafayette", "Lakeland", "Lakewood", "Lancaster", "Lansing", "Laredo", "Las Cruces", "Las Vegas", "Lewisville", "Little Rock", "Lowell", "Macon", "McAllen", "McKinney", "Mesquite", "Miami Gardens", "Midland", "Milwaukee", "Miramar", "Mobile", "Modesto", "Montgomery", "Moreno Valley", "Murfreesboro", "Murrieta", "Naperville", "New Haven", "Newport News", "Norfolk", "Norman", "North Charleston", "North Las Vegas", "Norwalk", "Oakland", "Oceanside", "Odessa", "Olathe", "Ontario", "Orange", "Overland Park", "Oxnard", "Palm Bay", "Palmdale", "Pasadena", "Paterson", "Pearland", "Pembroke Pines", "Peoria", "Pittsburgh", "Plano", "Pomona", "Pompano Beach", "Providence", "Provo", "Pueblo", "Rancho Cucamonga", "Reno", "Rialto", "Richardson", "Riverside", "Rochester", "Rockford", "Roseville", "Round Rock", "Sacramento", "Saint Paul", "Salem", "Salinas", "Salt Lake City", "San Bernardino", "San Jose", "San Mateo", "Sandy Springs", "Santa Clara", "Santa Clarita", "Santa Maria", "Santa Rosa", "Savannah", "Scottsdale", "Shreveport", "Simi Valley", "Sioux Falls", "South Bend", "Springfield", "St. Louis", "Stamford", "Sterling Heights", "Sunnyvale", "Surprise", "Syracuse", "Tacoma", "Tallahassee", "Temecula", "Tempe", "Thornton", "Thousand Oaks", "Toledo", "Topeka", "Torrance", "Tucson", "Tulsa", "Tyler", "Vallejo", "Ventura", "Victorville", "Visalia", "Waco", "Warren", "Waterbury", "West Covina", "West Jordan", "West Palm Beach", "West Valley City", "Westminster", "Wichita", "Wichita Falls", "Wilmington", "Winston-Salem", "Worcester", "Yonkers", "Afghanistan", "AK", "AL", "Alabama", "Åland", "Åland Islands", "Alaska", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "AR", "Argentina", "Arizona", "Arkansas", "Armenia", "Aruba", "Australia", "Austria", "AZ", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bonaire", "Bosnia", "Bosnia and Herzegovina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "British Virgin Islands", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "CA", "Cabo Verde", "California", "Cambodia", "Cameroon", "Canada", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "CO", "Cocos Islands", "Colombia", "Colorado", "Comoros", "Congo", "Congo (DRC)", "Connecticut", "Cook Islands", "Costa Rica", "Côte d’Ivoire", "Croatia", "CT", "Cuba", "Curaçao", "Cyprus", "Czechia", "DE", "Delaware", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "eSwatini", "Ethiopia", "Falkland Islands", "Falklands", "Faroe Islands", "Fiji", "Finland", "FL", "Florida", "France", "French Guiana", "French Polynesia", "French Southern Territories", "FYROM", "GA", "Gabon", "Gambia", "Georgia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guernsey", "Guinea", "Guinea Bissau", "Guinea-Bissau", "Guyana", "Haiti", "Hawaii", "Herzegovina", "HI", "Honduras", "Hong Kong", "Hungary", "IA", "Iceland", "ID", "Idaho", "IL", "Illinois", "IN", "India", "Indiana", "Indonesia", "Iowa", "Iran", "Iraq", "Ireland", "Isle of Man", "Israel", "Italy", "Ivory Coast", "Jamaica", "Jan Mayen", "Japan", "Jersey", "Jordan", "Kansas", "Kazakhstan", "Keeling Islands", "Kentucky", "Kenya", "Kiribati", "Korea", "Kosovo", "KS", "Kuwait", "KY", "Kyrgyzstan", "LA", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Louisiana", "Luxembourg", "MA", "Macao", "Macedonia", "Madagascar", "Maine", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Maryland", "Massachusetts", "Mauritania", "Mauritius", "Mayotte", "MD", "ME", "Mexico", "MI", "Michigan", "Micronesia", "Minnesota", "Mississippi", "Missouri", "MN", "MO", "Moldova", "Monaco", "Mongolia", "Montana", "Montenegro", "Montserrat", "Morocco", "Mozambique", "MS", "MT", "Myanmar", "Namibia", "Nauru", "NC", "ND", "NE", "Nebraska", "Nepal", "Netherlands", "Nevada", "New Caledonia", "New Hampshire", "New Jersey", "New Mexico", "New York", "New Zealand", "NH", "Nicaragua", "Niger", "Nigeria", "Niue", "NJ", "NM", "Norfolk Island", "North Carolina", "North Dakota", "North Korea", "Northern Mariana Islands", "Norway", "NV", "NY", "OH", "Ohio", "OK", "Oklahoma", "Oman", "OR", "Oregon", "PA", "Pakistan", "Palau", "Palestinian Authority", "Panama", "Papua New Guinea", "Paraguay", "Pennsylvania", "Peru", "Philippines", "Pitcairn Islands", "Poland", "Portugal", "Puerto Rico", "Qatar", "Réunion", "Rhode Island", "RI", "Romania", "Russia", "Rwanda", "Saba", "Saint Barthélemy", "Saint Kitts and Nevis", "Saint Lucia", "Saint Martin", "Saint Pierre and Miquelon", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "São Tomé and Príncipe", "Saudi Arabia", "SC", "SD", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Sint Eustatius", "Sint Maarten", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Carolina", "South Dakota", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Svalbard", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Tennessee", "Texas", "Thailand", "Timor-Leste", "TN", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "TX", "U.S. Outlying Islands", "U.S. Virgin Islands", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "UT", "Utah", "Uzbekistan", "VA", "Vanuatu", "Vatican City", "Venezuela", "Vermont", "Vietnam", "Virginia", "VT", "WA", "Wallis and Futuna", "West Virginia", "WI", "Wisconsin", "WV", "WY", "Wyoming", "Yemen", "Zambia", "Zimbabwe");
}
