import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class PropertiesFile(private val file: File, private val header: String) : Properties() {

  init {
    if (file.exists()) {
      var fis: FileInputStream? = null

      try {
        fis = FileInputStream(file)
        this.load(fis)
      } catch (var6: Exception) {
        // Message.warn("exception occurred while reading properties file $file", var6)
      }

      try {
        fis?.close()
      } catch (var5: IOException) {
      }

    }

  }

  fun save() {
    var fos: FileOutputStream? = null

    try {
      if (this.file.parentFile != null && !this.file.parentFile.exists()) {
        this.file.parentFile.mkdirs()
      }

      fos = FileOutputStream(this.file)
      this.store(fos, this.header)
    } catch (var4: Exception) {
      // Message.warn("exception occurred while writing properties file " + this.file, var4)
    }

    try {
      fos?.close()
    } catch (var3: IOException) {
    }

  }
}