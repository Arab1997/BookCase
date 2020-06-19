package com.shivamkumarjha.bookstore.utility

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shivamkumarjha.bookstore.model.Book
import java.io.*

private const val TAG = "JSONUtility"

class JsonUtility(private val file: File) {
    private val gson = Gson()

    init {
        val populateBooks = PopulateBooks()
        populateBooks.populate()
        writeToFile(populateBooks.getBooks())
    }

    private fun readFromFile(): String? {
        var jsonString: String? = null
        try {
            val inputStream: FileInputStream = file.inputStream()
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            jsonString = bufferedReader.use { it.readText() }
            inputStream.close()
        } catch (e: FileNotFoundException) {
            Log.e(TAG, "File not found: $e")
        } catch (e: IOException) {
            Log.e(TAG, "Can not read file: $e")
        }
        return jsonString
    }

    private fun writeToFile(books: ArrayList<Book>) {
        val data = gson.toJson(books)
        try {
            val outputStreamWriter = OutputStreamWriter(file.outputStream())
            outputStreamWriter.write(data)
            outputStreamWriter.close()
        } catch (e: IOException) {
            Log.e(TAG, "File write failed: $e")
        }
    }

    fun getBooks(): ArrayList<Book> {
        val jsonString = readFromFile()
        val detailsTypeToken = object : TypeToken<List<Book>>() {}.type
        return gson.fromJson(jsonString, detailsTypeToken)
    }
}