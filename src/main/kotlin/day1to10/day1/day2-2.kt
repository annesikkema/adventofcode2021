package day1to10.day1

import filereader.ResourcesFileReader

fun main() {
    val values = ResourcesFileReader().getIntValues("day1to10/day1-1.txt")
    val combinedValues = combineValues(values)
    val amountOfIncreases = AmountIncreasesCalculator().getIntValues(combinedValues)
    println(amountOfIncreases)
}

private fun combineValues(values: List<Int>): List<Int> {
    val listOfCombinedValues = mutableListOf<Int>()

    for (i in 2 until values.size) {
        val d = values[i-2] + values[i-1] + values[i]
        listOfCombinedValues.add(d)
    }

    return listOfCombinedValues
}