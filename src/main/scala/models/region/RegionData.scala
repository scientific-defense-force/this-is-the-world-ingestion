package models.region

import models.WealthBracket

case class RegionData(
   region: String,
   wealthBracketDetails: Vector[WealthBracket]
)
