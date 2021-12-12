package day1to10.day3

import filereader.ResourcesFileReader

fun main() {
    val rows = getRows()
    val oxygen = scan(rows, true)
    val co2 = scan(rows, false)
    println(oxygen * co2)

}

// SCAN
private fun scan(rows: List<List<Int>>, isOxygen: Boolean): Int {
    var filteredRows = rows
    var binary = ""

    val columnIndices = getColumnIndices(rows)

    for (columnIndex in columnIndices) {
        val column = getColumn(filteredRows, columnIndex)
        val extremeInt = if (isOxygen) getMostCommonIntForColumn(column) else getLeastCommonIntForColumn(column)
        filteredRows = filteredRows.filter { it[columnIndex] == extremeInt }
        binary += extremeInt
    }
    return binaryToDecimal(binary)
}

// READ FILES
private fun getRows(): List<List<Int>> {
    return ResourcesFileReader().getStringValues("day1to10/day3-1.txt")
        .map { stringToIntList(it) }
}

private fun stringToIntList(string: String): List<Int> {
    return string.toCharArray()
        .map { it.toString() }
        .map { it.toInt() }
}

// COLUMN INDEX
private fun getColumnIndices(rows: List<List<Int>>): IntRange {
    return rows.map { it.indices }
        .maxByOrNull { it.last }
        ?: IntRange.EMPTY
}

// GET COLUMN
private fun getColumn(rows: List<List<Int>>, column: Int): List<Int> {
    return rows.filter { it.size >= column + 1 }
        .map { it[column] }
}

// GET EXTREME
private fun getMostCommonIntForColumn(column: List<Int>): Int? {
    return column.sortedDescending()
        .groupingBy { it }
        .eachCount()
        .maxByOrNull { it.value }?.key
}

private fun getLeastCommonIntForColumn(column: List<Int>): Int? {
    return column.sorted()
        .groupingBy { it }
        .eachCount()
        .minByOrNull { it.value }?.key
}

// BINARY
private fun binaryToDecimal(binary: String): Int {
    return Integer.parseInt(binary, 2)
}