package day17

class ProbeLauncher(private val targetAreaRange: TargetAreaRange) {

    companion object {
        val STARTING_POSITION = Coordinate(0, 0)
    }

    fun getHighestPositionThatReachesTarget(): Int {
        var velocitiesThatReachTarget = 0
        for (y in -1000..1000) {
            for (x in -1000..1000) {
                val velocity = Coordinate(x, y)
                if (reachesTheTargetArea(velocity)) {
                    velocitiesThatReachTarget++
                }
            }
        }
        return velocitiesThatReachTarget
    }

    private fun reachesTheTargetArea(velocity: Coordinate): Boolean {
        var currentPosition = STARTING_POSITION
        var currentVelocity = velocity

        do {
            currentPosition = currentPosition.move(currentVelocity)
            currentVelocity = currentVelocity.drag()

            if (targetAreaRange.contains(currentPosition)) {
                return true
            }
        } while (targetCanBeReached(currentVelocity, currentPosition))

        return false
    }

    private fun targetCanBeReached(velocity: Coordinate, position: Coordinate) =
        xCanBeReached(velocity, position) && yCanBeReached(velocity, position)

    private fun xCanBeReached(velocity: Coordinate, position: Coordinate) =
        velocity.first != 0 || targetAreaRange.first.contains(position.first)

    private fun yCanBeReached(velocity: Coordinate, position: Coordinate) =
        velocity.second > 0 || targetAreaRange.second.minOf { it } < position.second

}