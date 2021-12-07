package day5

import filereader.ResourcesFileReader

class VentLineProvider {

    fun getVentLines(): List<VentLine> {
        val lines = ResourcesFileReader().getStringValues(FILE_LOCATION)
        return lines.map { stringToVentLine(it) }
    }

    private fun stringToVentLine(string: String): VentLine {
        val ventCoordinates = string.split(COORDINATE_DELIMITER)
            .map { stringToCoordinate(it) }
        return VentLine(ventCoordinates[0], ventCoordinates[1])
    }

    private fun stringToCoordinate(string: String): Coordinate {
        val coordinateStrings = string.split(",")
            .map { it.toInt() }
        return Coordinate(coordinateStrings[0], coordinateStrings[1])
    }

}