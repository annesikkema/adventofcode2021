package day15

import filereader.ResourcesFileReader

private const val FILE_NAME = "day15-1.txt"
private const val SIZE_MULTIPLIER = 5

fun main() {
    val grid = getGrid(FILE_NAME)

    val start = 0 to 0
    val exit = (grid[0].size * 5) - 1 to (grid.size * 5) - 1

    val fullMaze = MazeBuilder(grid, SIZE_MULTIPLIER).build()
    val pathFinder = CavernPathFinder(fullMaze, start, exit)
    println(pathFinder.findExit())
}

private fun getGrid(fileName: String) = ResourcesFileReader().getStringValues(fileName).map { it.chunkToInts() }

private fun String.chunkToInts() = this.chunked(1).map { string -> string.toInt() }