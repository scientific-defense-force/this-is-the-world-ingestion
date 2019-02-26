# Ingestion for the 'this is the world' website

The [Credit Suisse](https://www.credit-suisse.com) organisation publishes a [yearly global wealth report](https://www.credit-suisse.com/au/en/about-us/research/research-institute/global-wealth-report.html). It is the only comprehensive report that attempts to estimate wealth for all countries.

This app is a tool to extract wealth data from the PDF 'databook' and transform it into the required data for the [thisistheworld.net](https://thisistheworld.net) - https://github.com/scientific-defense-force/this-is-the-world-ingestion

## Generates

```json
{
  "otherTotalWealth" : 61157000000000,
  "wenaoTotalWealth" : 194543000000000,
  "otherTotalPopulation" : 6324568000,
  "wenaoTotalPopulation" : 1016988000,
  "other10PercentWealth" : 54906716700000,
  "other90PercentWealth" : 6250283300000,
  "wenao1PercentWealth" : 111970007200000,
  "wenao99PercentWealth" : 82572992800000
}
```

Wenao = West Europe North America Oceania + Japan

## Requires

Recent docker and docker-compose

You will need internet access for first run to download the databook pdf.

## To run

```bash
auto/dev-environment
```

Json result is generated in dist

## Alternatively

```bash
auto/dev-environment sbt

run
```

## Code Quality

Written when was I was very new to Scala and functional programming. Lots of cleanup possible. May look to do this in the future. Slightly unsure of the future of this site.

## Info on the data available in the PDF

[data-details.md](data-details.md)

## Updating to a new year

This is the happy path for updating to a new year's databook. In some cases more work is required to update the regex used to match the data.

1. Update [RetrieveFileAsStream](src/main/scala/RetrieveFileAsStream.scala) with the download location / filename of the new worlds data book.
2. Update the pages `from` and `to` for [DocToCountries](src/main/scala/DocToCountries.scala).
3. Test this step (commenting out later steps in [Entry](src/main/scala/Entry.scala)).
3. Update the pages `from` and `to` for [PopulationProcessor](src/main/scala/processors/country/PopulationProcessor.scala).
4. Update the pages `from` and `to` for [WealthDetailsProcessor](src/main/scala/processors/country/WealthDetailsProcessor.scala)
5. Update the pages `from` and `to` for [WealthBracketsProcessor](src/main/scala/processors/WealthBracketsProcessor.scala)
6. Test a full run.

