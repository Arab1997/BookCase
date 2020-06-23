package com.shivamkumarjha.bookstore.repository

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shivamkumarjha.bookstore.model.LoggedInUserView
import java.io.File
import java.io.IOException
import java.io.OutputStreamWriter

class ProfileRepository(private val file: File, private val loggedInUserView: LoggedInUserView) {
    private val gson = Gson()
    private val commonFileRepository = CommonFileRepository(file)
    private val tag = "ProfileRepository"

    private fun writeLoggedInUser() {
        val data = gson.toJson(loggedInUserView)
        try {
            val outputStreamWriter = OutputStreamWriter(file.outputStream())
            outputStreamWriter.write(data)
            outputStreamWriter.close()
        } catch (e: IOException) {
            Log.e(tag, "File write failed: $e")
        }
    }

    fun getLoggedInUser(): LoggedInUserView {
        if (!commonFileRepository.fileExists()) {
            writeLoggedInUser()
        }
        val jsonString = commonFileRepository.readFromFile()
        val detailsTypeToken = object : TypeToken<LoggedInUserView>() {}.type
        return gson.fromJson(jsonString, detailsTypeToken)
    }
}