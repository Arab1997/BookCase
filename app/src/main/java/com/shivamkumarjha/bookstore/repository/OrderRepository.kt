package com.shivamkumarjha.bookstore.repository

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shivamkumarjha.bookstore.model.Order
import java.io.File
import java.io.IOException
import java.io.OutputStreamWriter

class OrderRepository(private val file: File) {
    private val gson = Gson()
    private val commonFileRepository = CommonFileRepository(file)
    private val tag = "OrderRepository"

    private fun writeOrders(order: ArrayList<Order>) {
        val data = gson.toJson(order)
        try {
            val outputStreamWriter = OutputStreamWriter(file.outputStream())
            outputStreamWriter.write(data)
            outputStreamWriter.close()
        } catch (e: IOException) {
            Log.e(tag, "File write failed: $e")
        }
    }

    fun getOrders(): ArrayList<Order> {
        if (!commonFileRepository.fileExists())
            return arrayListOf()
        val jsonString = commonFileRepository.readFromFile()
        val detailsTypeToken = object : TypeToken<List<Order>>() {}.type
        return gson.fromJson(jsonString, detailsTypeToken)
    }

    fun addOrder(order: Order) {
        val orderList = getOrders()
        orderList.add(order)
        writeOrders(orderList)
    }
}
