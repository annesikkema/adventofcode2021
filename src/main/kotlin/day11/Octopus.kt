package day11

data class Octopus(var energyLevel: Int, var canFlash: Boolean = true) {

    fun reset() {
        this.energyLevel = 0
        canFlash = true
    }

}