package day1to10.day10

import filereader.ResourcesFileReader

val openingBrackets = mapOf(")" to "(", "]" to "[", "}" to "{", ">" to "<")

fun main() {
    val code = ResourcesFileReader().getStringValues("day1to10/day10-1.txt")
    val syntaxErrorScore = code.sumOf { it.syntaxErrorScore() }
    val autocompleteScores = code.map { it.autocompleteScore() }
        .filter { it != 0L }
        .sorted()
    val autocompleteMiddleScore = autocompleteScores[autocompleteScores.lastIndex / 2]
    println(syntaxErrorScore)
    println(autocompleteMiddleScore)
}

private fun String.syntaxErrorScore(): Int {
    val openedBrackets = mutableListOf<String>()
    for (i in this.chunked(1)) {
        if (!openedBrackets.isValidSyntax(i)) {
            return when (i) {
                ")" -> 3
                "]" -> 57
                "}" -> 1197
                else -> 25137
            }
        }
        openedBrackets.trackSyntax(i)
    }
    return 0
}

private fun String.autocompleteScore(): Long {
    val openedBrackets = mutableListOf<String>()
    for (i in this.chunked(1)) {
        if (!openedBrackets.isValidSyntax(i)) {
            return 0
        }
        openedBrackets.trackSyntax(i)
    }
    return calculateScore(openedBrackets)
}

private fun calculateScore(openedBrackets: List<String>): Long {
    var score = 0L
    openedBrackets.reversed()
        .map { it.getAutocompleteScore() }
        .forEach { score = score * 5 + it }
    return score
}

private fun String.getAutocompleteScore() = when(this) {
    "(" -> 1
    "[" -> 2
    "{" -> 3
    "<" -> 4
    else -> 0
}

private fun MutableList<String>.trackSyntax(string: String) {
    if (openingBrackets.contains(string)) {
        this.removeLast()
    } else {
        this.add(string)
    }
}

private fun List<String>.isValidSyntax(string: String) = this.isEmpty() || this.last() == (openingBrackets[string] ?: this.last())