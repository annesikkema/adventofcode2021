package day7

import filereader.ResourcesFileReader
import kotlin.math.abs

const val FILE_NAME = "day7-1.txt"

fun main() {
    val crabPositions = getCrabPositions()
    val min = crabPositions.minOrNull()?:Int.MIN_VALUE
    val max = crabPositions.maxOrNull()?:Int.MAX_VALUE
    val fuel = IntRange(min, max).map { calculateFuel(crabPositions, it)  }.minOrNull()?:Int.MAX_VALUE
    println(fuel)
}

private fun calculateFuel(positions: List<Int>, destination: Int): Long {
    return positions.map { abs(it - destination) }
        .sumOf { (it * (it + 1)) / 2 }
        .toLong()
}

private fun getCrabPositions(): List<Int> {
    return ResourcesFileReader().getStringValues(FILE_NAME)
        .map { it.split(",") }
        .flatten()
        .map { it.toInt() }
}