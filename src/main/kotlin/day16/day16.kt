package day16

import filereader.ResourcesFileReader

const val DICTIONARY_FILE_NAME = "day16-1.txt"
const val CODE_FILE_NAME = "day16-2.txt"

fun main() {
    val hexToBinaryDictionary = getDictionary(DICTIONARY_FILE_NAME)
    val binaryToHexDictionary = hexToBinaryDictionary.reverseMap()
    val code = ResourcesFileReader().getStringValue(CODE_FILE_NAME)
    val binary = code.translateChars(hexToBinaryDictionary)
    val decoder = BITSDecoder(binary, binaryToHexDictionary)
    val calculation = decoder.readPackets().calculate()
    println(calculation)
}

fun String.translateChars(dictionary: Map<String, String>) =
    this.map { dictionary[it.toString()] ?: "" }.joinToString("")

private fun getDictionary(fileName: String) = ResourcesFileReader().getStringValues(fileName).toMap()

private fun List<String>.toMap() = this.map { it.split(" = ") }.associate { x -> x[0] to x[1] }

private fun <K, V> Map<K, V>.reverseMap() = this.entries.associate { (key, value) -> value to key }

