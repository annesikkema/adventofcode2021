package day12

class RouteCalculator(private val caveNetwork: Map<String, Cave>, private val start: String, private val end: String) {

    fun calculateRoutes(route: List<String> = listOf(start)): Long {
        val currentCaveName = route.last()

        if (end == currentCaveName) {
            return 1
        }

        return caveNetwork.getOrDefault(currentCaveName, Cave())
            .connections
            .filter { !route.hasLowercaseDuplicates() || !route.contains(it.name.lowercase()) }
            .map { route + it.name }
            .sumOf { calculateRoutes(it) }
    }

    private fun List<String>.hasLowercaseDuplicates(): Boolean {
        val lowerCases = this.filter { it == it.lowercase() }
        return lowerCases.distinct().size < lowerCases.size
    }

}