package day12

const val FILE_NAME = "day12-4.txt"
const val DELIMITER = "-"
const val START = "start"
const val END = "end"

fun main() {
    val network = CaveNetworkBuilder(FILE_NAME, DELIMITER, START).build()
    val routes = RouteCalculator(network, START, END).calculateRoutes()
    println(routes)
}