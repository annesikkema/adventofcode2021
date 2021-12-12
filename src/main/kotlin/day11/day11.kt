package day11

import filereader.ResourcesFileReader

private const val FILE_NAME = "day11-2.txt"
private const val STEPS = 100

fun main() {
    star2()
}

fun star2() {
    val cavern = getCavern()
    val simultaneousFlashStep = cavern.getSimultaneousFlashStep()
    println(simultaneousFlashStep)
}

fun star1() {
    val cavern = getCavern()
    repeat(STEPS) {
        cavern.proceedToNextStep()
    }
    println(cavern.flashes)
}

private fun getCavern(): Cavern {
    val grid = ResourcesFileReader().getStringValues(FILE_NAME)
        .map { it.chunked(1).map { string -> string.toInt() } }
    val octopusPlacement = mutableMapOf<Pair<Int, Int>, Octopus>()
    for (y in grid.indices) {
        for (x in grid[y].indices) {
            val coordinate = Pair(x, y)
            val octopus = Octopus(grid[y][x])
            octopusPlacement[coordinate] = octopus
        }
    }
    return Cavern(octopusPlacement)
}