package day8

class Entry(string: String) {

    object Constants {
        const val PARTS_DELIMITER = " | "
        const val PART_DELIMITER = " "
    }

    private val signalPatterns: MutableList<String>
    private val outputValue: List<String>
    private val translationMap = mutableMapOf<String, Int>()

    init {
        val parts = string.split(Constants.PARTS_DELIMITER)
        signalPatterns = parts[0].split(Constants.PART_DELIMITER).map { it.sort() }.toMutableList()
        outputValue = parts[1].split(Constants.PART_DELIMITER).map { it.sort() }
    }

    fun calculateValue(): Int {
        fillTranslationMap()
        return outputValue.map { translationMap[it] ?: throw NullPointerException() }
            .joinToString("")
            .toInt()
    }

    private fun fillTranslationMap() {
        translateByLength(1, 2)
        val code4 = translateByLength(4, 4)
        val code7 = translateByLength(7, 3)
        val code8 = translateByLength(8, 7)
        val code9 = translateByLengthAndChars(9, 6, code4)
        translateByLengthAndChars(0, 6, code7)
        translateByLengthAndChars(3, 5, code7)
        translateByLength(6, 6)
        translateCode2(code8, code9)
        addTranslation(5, signalPatterns.first())
    }

    private fun translateByLength(value: Int, length: Int): String {
        val pattern = signalPatterns.first { it.length == length }
        return addTranslation(value, pattern)
    }

    private fun translateByLengthAndChars(value: Int, length: Int, chars: String): String {
        val pattern = signalPatterns.first { it.length == length && chars.all { char -> it.contains(char) } }
        return addTranslation(value, pattern)
    }

    private fun translateCode2(code8: String, code9: String): String {
        val pattern = signalPatterns.first { it.contains(code8.filter { symbol -> !code9.contains(symbol) }) }
        return addTranslation(2, pattern)
    }

    private fun addTranslation(value: Int, pattern: String): String {
        translationMap[pattern.sort()] = value
        signalPatterns.remove(pattern)
        return pattern
    }

    private fun String.sort() = String(toCharArray().apply { sort() })

}