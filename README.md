# Ingestion for the 'this is the world' website

The [Credit Suisse](https://www.credit-suisse.com) organisation publishes a [yearly global wealth report](https://www.credit-suisse.com/au/en/about-us/research/research-institute/global-wealth-report.html). It is the only comprehensive report that attempts to estimate wealth for all countries.

This app is a tool to extract wealth data from the PDF 'databook' and transform it into the required data for the [thisistheworld.net](https://thisistheworld.net) - https://github.com/open-socialism/this-is-the-world.github.io

## Requires

Recent docker and docker-compose

You need to download http://publications.credit-suisse.com/tasks/render/file/index.cfm?fileid=C26E3824-E868-56E0-CCA04D4BB9B9ADD5 and put the file in the root.

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

[data-details.md](Data details)