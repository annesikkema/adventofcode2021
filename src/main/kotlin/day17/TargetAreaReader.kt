package day17

import filereader.ResourcesFileReader

typealias TargetAreaRange = Pair<IntRange, IntRange>

private const val RANGE_DELIMITER = ".."
private const val ANY_NUMBER_REGEX = "-?[0-9]+"

private val TARGET_AREA_REGEX = "$ANY_NUMBER_REGEX$RANGE_DELIMITER$ANY_NUMBER_REGEX".toRegex()

class TargetAreaReader(private val fileName: String) {

    private val input = ResourcesFileReader()

    fun getTargetAreaRange(): TargetAreaRange {
        val input = input.getStringValue(fileName)
        val ranges = TARGET_AREA_REGEX.findAll(input)
            .map { it.value }
            .map { it.substringBefore(RANGE_DELIMITER).toInt()..it.substringAfter(RANGE_DELIMITER).toInt() }
            .toList()
        return ranges[0] to ranges[1]
    }

}

fun TargetAreaRange.contains(coordinate: Coordinate) =
    this.first.contains(coordinate.first) && this.second.contains(coordinate.second)