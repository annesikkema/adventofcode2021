package day3

import filereader.ResourcesFileReader
import kotlin.math.roundToInt

fun main() {
    val rows = getRows()
    val columns = getColumns(rows)
    val middle = getMidPoint(columns.get(0).size)
    val commons = columns.map { it[middle] }
    val uncommons = commons.map { if (it == 0) 1 else 0 }
    val gammaBinary = joinToString(commons)
    val gamma = binaryToDecimal(gammaBinary)
    val epsilonBinary = joinToString(uncommons)
    val epsilon = binaryToDecimal(epsilonBinary)
    val product = gamma * epsilon
    println(gamma)
    println(epsilon)
    println(product)
}

private fun getRows(): List<List<Int>> {
    return ResourcesFileReader().getStringValues("day3-1.txt")
        .map { stringToIntList(it) }
}

private fun stringToIntList(string: String): List<Int> {
    return string.toCharArray()
        .map { it.toString() }
        .map { it.toInt() }
}

private fun getColumns(rows: List<List<Int>>): List<List<Int>> {
    return rows[0].indices
        .map { index -> getColumn(index, rows) }
        .toList()
}

private fun getColumn(index: Int, rows: List<List<Int>>): List<Int> {
    return rows.map { row -> row[index] }
        .sorted()
}

private fun getMidPoint(int: Int): Int {
    return int.toDouble()
        .div(2)
        .toInt()
}

private fun joinToString(intList: List<Int>): String {
    return intList.joinToString(separator = "")
}

private fun binaryToDecimal(binary: String): Int {
    return Integer.parseInt(binary, 2)
}