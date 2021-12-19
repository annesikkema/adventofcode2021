package day17

typealias Coordinate = Pair<Int, Int>

fun Coordinate.move(x: Int = 0, y: Int = 0) = this.copy(this.first + x, this.second + y)

fun Coordinate.move(coordinate: Coordinate) = this.move(coordinate.first, coordinate.second)

fun Coordinate.drag() = when {
    this.first < 0 -> this.move(1, -1)
    this.first > 0 -> this.move(-1, -1)
    else -> this.move(0, -1)
}