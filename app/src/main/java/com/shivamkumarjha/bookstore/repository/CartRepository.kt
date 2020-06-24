package com.shivamkumarjha.bookstore.repository

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shivamkumarjha.bookstore.model.Cart
import java.io.File
import java.io.IOException
import java.io.OutputStreamWriter

class CartRepository(private val file: File) {
    private val gson = Gson()
    private val commonFileRepository = CommonFileRepository(file)
    private val tag = "CartRepository"

    private fun writeCart(cart: ArrayList<Cart>) {
        val data = gson.toJson(cart)
        try {
            val outputStreamWriter = OutputStreamWriter(file.outputStream())
            outputStreamWriter.write(data)
            outputStreamWriter.close()
        } catch (e: IOException) {
            Log.e(tag, "File write failed: $e")
        }
    }

    fun getCart(): ArrayList<Cart> {
        if (!commonFileRepository.fileExists()) {
            return arrayListOf()
        }
        val jsonString = commonFileRepository.readFromFile()
        val detailsTypeToken = object : TypeToken<List<Cart>>() {}.type
        return gson.fromJson(jsonString, detailsTypeToken)
    }

    fun addCart(cart: Cart) {
        val cartList = getCart()
        cartList.add(cart)
        writeCart(cartList)
    }

    fun removeCart(cart: Cart) {
        val cartList = getCart()
        cartList.removeAll { it.itemId == cart.itemId }
        writeCart(cartList)
    }

    fun updateCart(cart: Cart) {
        val cartList = getCart()
        cartList.removeAll { it.itemId == cart.itemId }
        cartList.add(cart)
        writeCart(cartList)
    }
}
