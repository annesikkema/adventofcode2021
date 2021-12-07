package day2

private var position = 0
private var depth = 0
private var aim = 0

fun main() {
    InstructionReader().getInstructions().forEach { countAim(it) }
    println(position * depth)
}

fun countAim(instruction: InstructionReader.Instruction) {
    when (instruction.direction) {
        "up" -> aim -= instruction.distance
        "down" -> aim += instruction.distance
        "forward" -> {
            position += instruction.distance
            depth += aim * instruction.distance
        }
    }
}