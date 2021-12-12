package day1to10.day9

import filereader.ResourcesFileReader

fun main() {
    val file = ResourcesFileReader().getIntArrays("day1to10/day9-1.txt")
    var risk = 0
    for (y in file.indices) {
        for (x in file[y].indices) {
            val current = file[y][x]
            val surrounding = mutableListOf<Int>()

            val rowAbove = file.getOrNull(y-1) ?: emptyList()
            val rowBelow = file.getOrNull(y+1) ?: emptyList()
            surrounding += rowAbove.getOrNull(x) ?: 9
            surrounding += rowBelow.getOrNull(x) ?: 9
            surrounding += file[y].getOrNull(x-1) ?: 9
            surrounding += file[y].getOrNull(x+1) ?: 9

            if (surrounding.all { it > current }) risk+= current + 1
        }
    }
    println(risk)
}