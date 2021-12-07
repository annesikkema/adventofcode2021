package day2

import filereader.ResourcesFileReader

private var position = 0
private var depth = 0
private var aim = 0

fun main() {
    getInstructions().forEach { countAim(it) }
    println(position * depth)
}

private fun getInstructions(): List<Instruction> {
    return ResourcesFileReader().getStringValues("day2-1.txt")
        .map { it.split(" ") }
        .map { Instruction(it[0], it[1].toInt()) }
}

fun countAim(instruction: Instruction) {
    when (instruction.direction) {
        "up" -> aim -= instruction.distance
        "down" -> aim += instruction.distance
        "forward" -> {
            position += instruction.distance
            depth += aim * instruction.distance
        }
    }
}