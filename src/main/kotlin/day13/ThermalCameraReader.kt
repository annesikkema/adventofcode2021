package day13

import filereader.ResourcesFileReader

class ThermalCameraReader(fileName: String) {

    companion object {
        private const val COORDINATE_DELIMITER = ","
        private const val COORDINATE_REGEX = "[0-9]+$COORDINATE_DELIMITER[0-9]+"

        private const val FOLD_PREFIX = "fold along "
        private const val FOLD_DELIMITER = "="
    }

    private val lines = ResourcesFileReader().getStringValues(fileName)

    fun getCoordinates(): List<Pair<Int, Int>> {
        val coordinateRegex = COORDINATE_REGEX.toRegex()
        return lines.filter { coordinateRegex.matches(it) }
            .map { it.split(COORDINATE_DELIMITER) }
            .map { it[0].toInt() to it[1].toInt() }
    }

    fun getFolds(): List<Pair<Axis, Int>> {
        return lines.filter { it.startsWith(FOLD_PREFIX) }
            .map { it.substringAfter(FOLD_PREFIX) }
            .map { it.split(FOLD_DELIMITER) }
            .map { Axis.valueOf(it[0].uppercase()) to it[1].toInt() }
    }

}