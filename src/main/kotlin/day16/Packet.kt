package day16

data class Packet(
    val version: Int, val packetType: PacketType, var subPackets: List<Packet> = emptyList(), var literal: Long = 0
) {

    fun getVersionSum(): Int {
        return version + subPackets.sumOf { it.getVersionSum() }
    }

    fun calculate(): Long {
        return when (packetType) {
            PacketType.SUM -> subPackets.sumOf { it.calculate() }
            PacketType.PRODUCT -> subPackets.map { it.calculate() }.reduce { acc, packet -> acc * packet }
            PacketType.MINIMUM -> subPackets.minOfOrNull { it.calculate() } ?: 0L
            PacketType.MAXIMUM -> subPackets.maxOfOrNull { it.calculate() } ?: 0L
            PacketType.LITERAL_VALUE -> literal
            PacketType.GREATER_THAN -> if (subPackets[0].calculate() > subPackets[1].calculate()) 1 else 0L
            PacketType.LESS_THAN -> if (subPackets[0].calculate() < subPackets[1].calculate()) 1 else 0L
            PacketType.EQUAL_TO -> if (subPackets[0].calculate() == subPackets[1].calculate()) 1 else 0L
            else -> throw IllegalArgumentException("Invalid PacketType")
        }
    }

}