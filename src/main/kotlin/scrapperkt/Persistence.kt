package scrapperkt

import java.io.File

data class Result(val url: String, val html: String, val accessedAtEpochMillis: Long)


fun Result.fileName() : String {
    return Integer.toHexString(url.hashCode()) + ".html"
}

fun Result.appendToDiskFile(parentDir: File): Result {
    if (!parentDir.exists()) {
        parentDir.mkdirs()
    }
    assert(parentDir.isDirectory)
    val targetFile = File(parentDir.toPath().toString() + "/" + this.fileName())
    return targetFile.append(this)
}


fun File.append(result: Result) : Result{
    if (!this.exists()) {
        this.createNewFile()
    }
    if (this.isDirectory) {
        throw IllegalStateException()
    }

    //writes url and access time as top comment, ends with newline
    val str = "<!-- href=\"" + result.url + "\" accessEpochMillis=" + result.accessedAtEpochMillis + " -->\n\n" + result.html + "\n\n"
    this.appendText(str)
    return result
}