package day16

class BITSDecoder(private val binary: String, private val binaryToHexDictionary: Map<String, String>) {

    companion object {
        const val HEADER_LENGTH = 3
    }

    fun readPackets(): Packet {
        val index = Index(0)
        return readPackets(index, maxPackets = 1)[0]
    }

    private fun readPackets(
        index: Index,
        maxPackets: Long = Int.MAX_VALUE.toLong(),
        maxBits: Long = Int.MAX_VALUE.toLong()
    ): List<Packet> {

        val packets = mutableListOf<Packet>()
        var numberOfPackets = 0
        val maxIndex = index.value + maxBits

        while (numberOfPackets < maxPackets && index.value < maxIndex) {

            numberOfPackets++

            val version = takeHeaderFromBinary(index)
            val type = takeHeaderFromBinary(index)
            val packet = Packet(version, PacketType[type])

            if (packet.packetType == PacketType.LITERAL_VALUE) {
                packet.literal = getLiteral(index)
            } else {
                packet.subPackets = getSubPackets(index)
            }

            packets.add(packet)

        }

        return packets
    }

    private fun getLiteral(index: Index): Long {
        var binary = ""

        do {
            val hasAnother = takeFromBinary(index).toInt() == 1
            binary += takeFromBinary(index, 4)
        } while (hasAnother)

        return binary.binaryToDecimal()
    }

    private fun getSubPackets(index: Index): List<Packet> {
        val isMaxBitsType = takeFromBinary(index).toInt() == 0

        return if (isMaxBitsType) {
            val maxBits = takeFromBinary(index, 15).binaryToDecimal()
            readPackets(index, maxBits = maxBits)
        } else {
            val maxPackets = takeFromBinary(index, 11).binaryToDecimal()
            readPackets(index, maxPackets = maxPackets)
        }
    }

    private fun takeHeaderFromBinary(index: Index) = takeFromBinary(index, HEADER_LENGTH).translateToHex().toInt()

    private fun takeFromBinary(index: Index, amount: Int = 1): String {
        val taken = binary.substring(index.value until index.value + amount)
        index.value += amount
        return taken
    }

    private fun String.translateToHex() =
        binaryToHexDictionary[if (this.length == HEADER_LENGTH) "0$this" else this] ?: ""

    private fun String.binaryToDecimal() = this.toLong(2)

}