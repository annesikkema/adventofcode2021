package day6

import filereader.ResourcesFileReader

fun main() {
    println(calculateFishSize("day6-1.txt", 256))
}

private fun calculateFishSize(file: String, days: Int): Long {
    val fishList = getInitialFishList(file)
    repeat(days) { advanceDay(fishList) }
    return fishList.sum()
}

private fun getInitialFishList(fileName: String): LongArray {
    val fishList = LongArray(9)
    ResourcesFileReader().getStringValues(fileName)
        .map { it.split(",") }
        .flatten()
        .map { it.toInt() }
        .forEach { fishList[it]++ }
    return fishList
}

private fun advanceDay(fishList: LongArray) {
    val newFish = fishList[0]
    IntRange(0, fishList.lastIndex - 1).forEach { fishList[it] = fishList[it + 1] }
    fishList[6] += newFish
    fishList[8] = newFish
}