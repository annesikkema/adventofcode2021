package day15

import java.lang.Long.min

class CavernPathFinder(
    private val riskMap: Map<Pair<Int, Int>, Long>,
    private val start: Pair<Int, Int>,
    private val exit: Pair<Int, Int>,
) {

    private val allCoordinates = riskMap.getAllCoordinates()
    private val totalRiskMap = riskMap.getTotalRiskMap()

    fun findExit(): Long {
        totalRiskMap[exit] = 0

        var isCalculating = true

        while (isCalculating) {

            isCalculating = false

            for (it in allCoordinates) {
                val shortestRouteToExit = calculateShortestRoute(it)

                if (shortestRouteToExit < totalRiskMap[it]!!) {
                    totalRiskMap[it] = shortestRouteToExit
                    isCalculating = true
                }
            }

        }

        return totalRiskMap[start]!! + riskMap[exit]!! - 1
    }

    private fun calculateShortestRoute(coordinate: Pair<Int, Int>): Long {
        val neighbours = coordinate.getNeighbours()

        var min = Int.MAX_VALUE.toLong()
        for (neighbor in neighbours) {
            if (totalRiskMap.contains(neighbor)) {
                min = min(min, riskMap[coordinate]!! + totalRiskMap[neighbor]!!)
            }
        }

        return min
    }

    private fun Pair<Int, Int>.getNeighbours() = listOf(
        this.first - 1 to this.second,
        this.first + 1 to this.second,
        this.first to this.second - 1,
        this.first to this.second + 1
    )

    private fun Map<Pair<Int, Int>, Long>.getAllCoordinates() = this.keys
        .sortedWith(compareBy({ -it.second }, { -it.first }))
        .reversed()

    private fun Map<Pair<Int, Int>, Long>.getTotalRiskMap() = this.keys
        .associateWith { Int.MAX_VALUE.toLong() }
        .toMutableMap()

}