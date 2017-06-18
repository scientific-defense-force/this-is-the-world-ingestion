# Ingestion for the 'this is the world' website

The [Credit Suisse](https://www.credit-suisse.com) organisation publishes a [yearly global wealth report](https://www.credit-suisse.com/au/en/about-us/research/research-institute/global-wealth-report.html). It is the only comprehensive report that attempts to estimate wealth for all countries.

This app is a tool to extract wealth data from the PDF 'databook' and transform it into the required data for the [thisistheworld.net](https://thisistheworld.net) - https://github.com/scientific-defense-force/this-is-the-world-ingestion

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
auto/dev-environment bash

sbt run
```

## Info on the data available in the PDF

[data-details.md](data-details.md)
