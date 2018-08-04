package com.hpedrorodrigues.dagmodules.util

import java.io.File
import java.nio.file.Files

object FileUtil {

  fun getPath(parent: String, directoryName: String) =
    File(parent, directoryName)

  fun writeToFile(directory: File, fileName: String, content: String) {
    val file = File(directory.path, fileName)

    if (!directory.exists()) directory.mkdirs()

    Files.write(file.toPath(), content.toByteArray())
  }
}
