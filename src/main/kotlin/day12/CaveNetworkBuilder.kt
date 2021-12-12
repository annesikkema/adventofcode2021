package day12

import filereader.ResourcesFileReader

class CaveNetworkBuilder(private val fileName: String, private val delimiter: String, private val startName: String) {

    fun build(): Map<String, Cave> {
        val caveNetwork = mutableMapOf<String, Cave>()
        ResourcesFileReader().getStringValues(fileName)
            .map { it.split(delimiter) }
            .forEach { caveNetwork.addTwoWayConnection(it[0], it[1]) }
        return caveNetwork
    }

    private fun MutableMap<String, Cave>.addTwoWayConnection(fromCaveName: String, toCaveName: String) {
        val from = this.getOrPut(fromCaveName) { Cave(fromCaveName) }
        val to = this.getOrPut(toCaveName) { Cave(toCaveName) }
        addOneWayConnection(from, to)
        addOneWayConnection(to, from)
    }

    private fun addOneWayConnection(from: Cave, to: Cave) {
        if (startName != to.name) {
            from.connections.add(to)
        }
    }

}