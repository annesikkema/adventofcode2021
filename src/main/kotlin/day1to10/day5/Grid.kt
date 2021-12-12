package day1to10.day5

class Grid {

    private val grid = Array(MAX_GRID_SIZE) { IntArray(MAX_GRID_SIZE) }

    fun addVentLine(ventLine: VentLine) {
        val from = ventLine.from
        val to = ventLine.to

        if (!from.canMoveTo(to)) return
        val current = Coordinate(from.x, from.y)

        while (true) {
            grid[current.y][current.x]++
            if (current == to) break
            current.approach(to)
        }
    }

    fun getLocationsWithMultipleVents(): Int {
        return grid.map { it.toList() }
            .flatten()
            .count { it > 1 }
    }

}