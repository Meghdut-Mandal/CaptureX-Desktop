import java.io.File
import java.util.*

class ConfigProperties(fileName: String) {

    private val configFile = File(fileName)
    private val properties = Properties()


    fun saveConfig() {
        configFile.delete()
        configFile.outputStream().use {
            properties.store(it, "${System.currentTimeMillis()}")
        }
    }

    fun loadProps() {

        if(!configFile.exists()){
            throw IllegalStateException("A capture.prop file is required in the home directory!")
        }

        configFile.inputStream().use {
            properties.load(it)
        }
    }

    operator fun get(key: String): String? = properties[key]?.toString()

    operator fun set(key: String, value: Any) {
        properties[key] = value.toString()
        saveConfig()
    }
}