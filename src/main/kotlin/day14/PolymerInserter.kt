package day14

class PolymerInserter(polymer: List<String>, private val rules: Map<String, String>) {

    private val occurences: MutableMap<String, Long> = mutableMapOf()
    private val combinationOccurences: MutableMap<String, Long> = mutableMapOf()

    init {
        val combinationIndices = 0 until polymer.lastIndex

        combinationIndices.map { polymer[it] + polymer[it + 1] }
            .forEach { combinationOccurences.increase(it, 1) }

        polymer.forEach { occurences.increase(it, 1) }
    }

    fun applyRulesTimes(times: Int): Long {
        repeat(times) {
            applyRules()
        }

        val most = occurences.maxByOrNull { it.value }!!.value
        val least = occurences.minByOrNull { it.value }!!.value

        return most - least
    }

    private fun applyRules() {
        combinationOccurences.toMap().forEach { applyRulesForCombination(it.key, it.value) }
    }

    private fun applyRulesForCombination(combination: String, amount: Long) {
        val newLetter = rules[combination]!!

        occurences.increase(newLetter, amount)

        combinationOccurences.increase(combination[0] + newLetter, amount)
        combinationOccurences.increase(newLetter + combination[1], amount)
        combinationOccurences.increase(combination, -amount)
    }

    private fun MutableMap<String, Long>.increase(key: String, amount: Long) =
        this.put(key, this.getOrDefault(key, 0) + amount)

}