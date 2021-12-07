package day2

import filereader.ResourcesFileReader

class InstructionReader {

    fun getInstructions(): List<Instruction> {
        return ResourcesFileReader().getStringValues("day2-1.txt")
            .map { it.split(" ") }
            .map { Instruction(it[0], it[1].toInt()) }
    }

    data class Instruction(val direction: String, val distance: Int)

}