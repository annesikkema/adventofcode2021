package day2

import filereader.ResourcesFileReader

private var position = 0
private var depth = 0

fun main() {
    getInstructions().forEach { countDistance(it) }
    println(position * depth)
}

private fun getInstructions(): List<Instruction> {
    return ResourcesFileReader().getStringValues("day2-1.txt")
        .map { it.split(" ") }
        .map { Instruction(it[0], it[1].toInt()) }
}

fun countDistance(instruction: Instruction) {
    when (instruction.direction) {
        "up" -> depth -= instruction.distance
        "down" -> depth += instruction.distance
        "forward" -> position += instruction.distance
    }
}

data class Instruction(val direction: String, val distance: Int)