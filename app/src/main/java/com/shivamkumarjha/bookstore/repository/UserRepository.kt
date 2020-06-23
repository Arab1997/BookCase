package com.shivamkumarjha.bookstore.repository

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shivamkumarjha.bookstore.model.User
import java.io.File
import java.io.IOException
import java.io.OutputStreamWriter

class UserRepository(private val file: File) {
    private val gson = Gson()
    private val commonFileRepository = CommonFileRepository(file)
    private val tag = "BookRepository"

    private fun writeUsers(users: ArrayList<User>) {
        val data = gson.toJson(users)
        try {
            val outputStreamWriter = OutputStreamWriter(file.outputStream())
            outputStreamWriter.write(data)
            outputStreamWriter.close()
        } catch (e: IOException) {
            Log.e(tag, "File write failed: $e")
        }
    }

    fun getUsers(): ArrayList<User> {
        if (!commonFileRepository.fileExists()) {
            return arrayListOf()
        }
        // read JSON file storing array of Details object
        val jsonString = commonFileRepository.readFromFile()
        val detailsTypeToken = object : TypeToken<List<User>>() {}.type
        return gson.fromJson(jsonString, detailsTypeToken)
    }

    fun addUser(user: User) {
        val users = getUsers()
        users.add(user)
        writeUsers(users)
    }
}