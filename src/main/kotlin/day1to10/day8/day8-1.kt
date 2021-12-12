package day1to10.day8

import filereader.ResourcesFileReader

private const val FILE_NAME = "day1to10/day8-1.txt"

fun main() {
    val lines = ResourcesFileReader().getStringValues(FILE_NAME)
    val numbersThatAre1478 = lines.map { it.substringAfter("| ") }
        .flatMap { it.split(" ") }
        .map { it.length }
        .count { is1478(it) }
    println(numbersThatAre1478)
}

private fun is1478(int: Int): Boolean {
    return when (int) {
        2,3,4,7 -> true
        else -> false
    }
}