package com.shivamkumarjha.bookstore.repository

import android.util.Log
import java.io.*

class CommonFileRepository(private val file: File) {

    private val tag = "CommonFileRepository"

    fun fileExists(): Boolean {
        if (!file.exists())
            return false
        return true
    }

    fun readFromFile(): String? {
        var jsonString: String? = null
        try {
            val inputStream: FileInputStream = file.inputStream()
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            jsonString = bufferedReader.use { it.readText() }
            inputStream.close()
        } catch (e: FileNotFoundException) {
            Log.e(tag, "File not found: $e")
        } catch (e: IOException) {
            Log.e(tag, "Can not read file: $e")
        }
        return jsonString
    }
}