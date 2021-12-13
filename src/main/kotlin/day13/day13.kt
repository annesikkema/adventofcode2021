package day13

private const val FILE_NAME = "day13-1.txt"

const val BLACK = "■"
const val WHITE = " "

fun main() {
    val thermalCameraReader = ThermalCameraReader(FILE_NAME)

    var coordinates = thermalCameraReader.getCoordinates()
    val folds = thermalCameraReader.getFolds()

    folds.forEach { coordinates = coordinates.foldCoordinates(it) }

    draw(coordinates)
}

private fun draw(coordinates: List<Pair<Int, Int>>) {
    val width = coordinates.maxOf { it.first }
    val height = coordinates.maxOf { it.second }

    (0..height).forEach { y ->
        (0..width).forEach { x ->
            val color = if (coordinates.contains(Pair(x, y))) BLACK else WHITE
            print(color)
        }
        println()
    }
}

private fun List<Pair<Int, Int>>.foldCoordinates(fold: Pair<Axis, Int>) = this.map { it.foldCoordinate(fold) }
    .distinct()

private fun Pair<Int, Int>.foldCoordinate(fold: Pair<Axis, Int>): Pair<Int, Int> {
    val coordinateToFold = this.flipIfTrue(fold.first == Axis.Y)
    val coordinateFolded = coordinateToFold.foldOnXAxis(fold.second)
    return coordinateFolded.flipIfTrue(fold.first == Axis.Y)
}

private fun Pair<Int, Int>.foldOnXAxis(foldLine: Int): Pair<Int, Int> {
    val distanceFromFold = this.first - foldLine
    return if (distanceFromFold < 0) this else Pair(this.first - (2 * distanceFromFold), this.second)
}

private fun Pair<Int, Int>.flipIfTrue(boolean: Boolean) = if (boolean) Pair(this.second, this.first) else this

enum class Axis { X, Y }