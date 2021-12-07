package filereader

import java.nio.file.Paths

class ResourcesFileReader {

    fun getStringValues(resourceFile: String): List<String> {
        return Paths.get(ResourcesFileReaderConstants.RESOURCES_FOLDER + resourceFile)
            .toFile()
            .readLines()
    }

    fun getIntValues(resourceFile: String): List<Int> {
        return getStringValues(resourceFile).map { string -> string.toInt() }
    }

}