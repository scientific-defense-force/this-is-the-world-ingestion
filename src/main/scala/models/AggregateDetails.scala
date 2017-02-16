package models

case class AggregateDetails(otherTotalWealth: Long,
                            wenaoTotalWealth: Long,
                            otherTotalPopulation: Long,
                            wenaoTotalPopulation: Long,
                            other10PercentWealth: Long,
                            other90PercentWealth: Long,
                            wenao1PercentWealth: Long,
                            wenao99PercentWealth: Long
                           )