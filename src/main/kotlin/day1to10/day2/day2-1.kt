package day1to10.day2

private var position = 0
private var depth = 0

fun main() {
    InstructionReader().getInstructions().forEach { countDistance(it) }
    println(position * depth)
}

fun countDistance(instruction: InstructionReader.Instruction) {
    when (instruction.direction) {
        "up" -> depth -= instruction.distance
        "down" -> depth += instruction.distance
        "forward" -> position += instruction.distance
    }
}