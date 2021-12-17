package day15

class MazeBuilder(private val grid: List<List<Int>>, sizeMultiplier: Int) {

    private val width = grid[0].size
    private val height = grid.size

    private val multiplyRange = (0 until sizeMultiplier)

    fun build(): MutableMap<Pair<Int, Int>, Long> {

        val fullMaze = mutableMapOf<Pair<Int, Int>, Long>()

        multiplyRange.map { xPart ->
            multiplyRange.map { yPart ->
                coordinatesFor(
                    width * xPart to height * yPart,
                    width,
                    height
                ).associateWith { (grid[it.first % width][it.second % height] + xPart + yPart).toLong().toNonary() }
            }.forEach { fullMaze.putAll(it) }
        }

        return fullMaze
    }

    private fun coordinatesFor(from: Pair<Int, Int>, width: Int, height: Int): List<Pair<Int, Int>> {
        return (from.first until from.first + width).map { x ->
            (from.second until from.second + height).map { y -> x to y }
        }.flatten()
    }

    private fun Long.toNonary() = this % 10 + this / 10

}