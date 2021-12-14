package day14

const val FILE_NAME = "day14-1.txt"
const val NUMBER_OF_STEPS = 40

fun main() {
    val polymerScanner = PolymerScanner(FILE_NAME)

    val rules = polymerScanner.getRules()
    val template = polymerScanner.getTemplate()
    val polymerInserter = PolymerInserter(template, rules)

    val answer = polymerInserter.applyRulesTimes(NUMBER_OF_STEPS)

    println(answer)
}