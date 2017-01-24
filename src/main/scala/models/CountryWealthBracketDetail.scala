package models

case class WealthBracket(bracket: Double, value: Double)

case class CountryWealthBracketDetail(name: String,
                                      brackets: Vector[WealthBracket]
                        )