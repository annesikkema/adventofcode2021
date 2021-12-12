package day1to10.day9

import filereader.ResourcesFileReader

fun main() {
    val grid = ResourcesFileReader().getIntArrays("day1to10/day9-1.txt")
    val basins = mutableListOf<Int>()

    for (y in IntRange(0, grid.lastIndex)) {
        for (x in IntRange(0, grid[y].lastIndex)) {
            basins.add(count(grid, Pair(y, x)))
        }
    }

    var result = 1
    basins.sorted()
        .takeLast(3)
        .forEach { result *= it }
    println(result)
}

private fun count(grid: List<MutableList<Int>>, coordinates: Pair<Int, Int>): Int {
    var count = 0

    if (getDepth(grid, coordinates) == 9) {
        return count
    }
    grid[coordinates.first][coordinates.second] = 9

    for (adjacentCoordinate in getAdjacentCoordinates(coordinates)) {
        count += count(grid, adjacentCoordinate)
    }
    return count + 1
}

private fun getAdjacentCoordinates(coordinates: Pair<Int, Int>): List<Pair<Int, Int>> {
    return mutableListOf(
        Pair(coordinates.first - 1, coordinates.second),
        Pair(coordinates.first + 1, coordinates.second),
        Pair(coordinates.first, coordinates.second - 1),
        Pair(coordinates.first, coordinates.second + 1)
    )
}

private fun getDepth(grid: List<List<Int>>, coordinates: Pair<Int, Int>): Int {
    return grid.getOrElse(coordinates.first) { emptyList() }
        .getOrElse(coordinates.second) { 9 }
}