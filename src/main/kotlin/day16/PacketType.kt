package day16

enum class PacketType(val value: Int) {
    NULL(-1),

    SUM(0),
    PRODUCT(1),
    MINIMUM(2),
    MAXIMUM(3),
    LITERAL_VALUE(4),
    GREATER_THAN(5),
    LESS_THAN(6),
    EQUAL_TO(7);


    companion object {
        private val map = values().associateBy(PacketType::value)
        operator fun get(value: Int) = map[value] ?: NULL
    }

}