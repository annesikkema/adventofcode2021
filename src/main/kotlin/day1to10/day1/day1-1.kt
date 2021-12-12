package day1to10.day1

import filereader.ResourcesFileReader

fun main() {
    val values = ResourcesFileReader().getIntValues("day1to10/day1-1.txt")
    val amountOfIncreases = AmountIncreasesCalculator().getIntValues(values)
    println(amountOfIncreases)
}