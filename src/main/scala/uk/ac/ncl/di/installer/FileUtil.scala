package uk.ac.ncl.di.installer

import java.io.{IOException, FileOutputStream, File, InputStream}
import java.util.zip.ZipInputStream

import org.apache.commons.io.IOUtils

import scala.concurrent.{ExecutionContext, Future}

object FileUtil {
  def extractZip(inputStream: InputStream, outputPath: String)(implicit ec: ExecutionContext) = Future {
    val directory = new File(outputPath)

    if (!directory.exists)
      directory.mkdir()
    else if (!directory.isDirectory)
      throw new IOException("Destination path already exists and is not a directory")

    val zis = new ZipInputStream(inputStream)

    var ze = zis.getNextEntry()

    while (ze != null) {
      val fileName = ze.getName()

      val file = new File(outputPath + File.separator + fileName)

      file.getParentFile().mkdirs()

      if (ze.isDirectory) {
        if (file.exists() && !file.isDirectory)
          throw new IOException("Destination file already exists and is not a directory")
        file.mkdir()
      } else {
        val os = new FileOutputStream(file)
        IOUtils.copy(zis, os)
        os.close()
      }

      zis.closeEntry()
      ze = zis.getNextEntry()
    }

    zis.close()
  }
}
