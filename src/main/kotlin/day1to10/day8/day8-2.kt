package day1to10.day8

import filereader.ResourcesFileReader

private const val FILE_NAME = "day1to10/day8-1.txt"

fun main() {
    val answer = ResourcesFileReader().getStringValues(FILE_NAME)
        .map { Entry(it) }
        .sumOf { it.calculateValue() }
    println(answer)
}