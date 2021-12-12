package day11

class Cavern(private var octopusPlacement: Map<Pair<Int, Int>, Octopus>) {

    var flashes = 0L

    fun getSimultaneousFlashStep(): Int {
        var step = 0
        while (true) {
            step++
            proceedToNextStepWithoutReset()
            if (octopusPlacement.values.all { !it.canFlash }) {
                return step
            }
            octopusPlacement.values.filter { !it.canFlash }.forEach { it.reset() }
        }
    }

    private fun proceedToNextStepWithoutReset() {
        val octopuses = octopusPlacement.values
        octopuses.forEach { it.energyLevel++ }
        var flashingOctoPuses = octopusPlacement.filterCanFlash()

        while (flashingOctoPuses.isNotEmpty()) {
            flashingOctoPuses.forEach {
                it.value.canFlash = false
                flashes++
                val flashingCoordinates = getCoordinateAndAdjacentCoordinates(it.key)
                flashingCoordinates.mapNotNull { flashingCoordinate -> octopusPlacement[flashingCoordinate] }
                    .forEach { octopus -> octopus.energyLevel++ }
            }
            flashingOctoPuses = octopusPlacement.filterCanFlash()
        }
    }

    fun proceedToNextStep() {
        proceedToNextStepWithoutReset()
        octopusPlacement.values.filter { !it.canFlash }.forEach { it.reset() }
    }

    private fun getCoordinateAndAdjacentCoordinates(coordinate: Pair<Int, Int>): List<Pair<Int, Int>> {
        val xRange = IntRange(coordinate.first - 1, coordinate.first + 1)
        val yRange = IntRange(coordinate.second - 1, coordinate.second + 1)
        return xRange.map { x -> yRange.map { y -> Pair(x, y) } }.flatten()
    }

    private fun Map<Pair<Int, Int>, Octopus>.filterCanFlash() = this.filter { it.component2().canFlash }
        .filter { it.component2().energyLevel > 9 }

}