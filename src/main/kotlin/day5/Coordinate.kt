package day5

import kotlin.math.abs

class Coordinate(var x: Int, var y: Int) {

    fun approach(other: Coordinate) {
        if (x < other.x) this.x++ else if (x > other.x) x--
        if (y < other.y) this.y++ else if (y > other.y) y--
    }

    fun canMoveTo(other: Coordinate): Boolean {
        if (this == other) return false
        if (this.x == other.x || this.y == other.y) return true
        return abs(x - other.x) == abs(y - other.y)
    }

    override fun equals(other: Any?): Boolean {
        other as Coordinate
        if (x != other.x) return false
        if (y != other.y) return false
        return true
    }

    override fun hashCode(): Int {
        return 31 * x + y
    }

}