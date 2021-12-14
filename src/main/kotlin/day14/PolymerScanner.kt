package day14

import filereader.ResourcesFileReader

class PolymerScanner(fileName: String) {

    companion object {
        private const val RULE_DELIMITER = " -> "
        private const val ANY_LETTERS_REGEX = "[A-Z]+"
        private const val RULE_REGEX = "$ANY_LETTERS_REGEX$RULE_DELIMITER$ANY_LETTERS_REGEX"
    }

    private val lines = ResourcesFileReader().getStringValues(fileName)

    fun getTemplate(): List<String> {
        return lines.first()
            .chunked(1)
    }

    fun getRules(): Map<String, String> {
        val coordinateRegex = RULE_REGEX.toRegex()
        return lines.filter { coordinateRegex.matches(it) }
            .map { it.split(RULE_DELIMITER) }
            .associate { it[0] to it[1] }
    }

}