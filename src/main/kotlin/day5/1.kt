package day5

const val FILE_LOCATION = "day5-1.txt"
const val MAX_GRID_SIZE = 999
const val COORDINATE_DELIMITER = " -> "

fun main() {
    val grid = Grid()
    val ventLines = VentLineProvider().getVentLines()

    ventLines.forEach { grid.addVentLine(it) }
    val locationsWithMultipleVents = grid.getLocationsWithMultipleVents()

    println(locationsWithMultipleVents)
}