package com.shivamkumarjha.bookstore.repository

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shivamkumarjha.bookstore.model.Book
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

    private fun getIndex(cart: Cart, cartList: ArrayList<Cart>): Int {
        for (index in 0 until cartList.size) {
            if (cartList[index].itemId == cart.itemId)
                return index
        }
        return -1
    }

    fun updateCart(cart: Cart) {
        val cartList = getCart()
        val index = getIndex(cart, cartList)
        if (index != -1)
            cartList[index] = cart
        else {
            cartList.removeAll { it.itemId == cart.itemId }
            cartList.add(cart)
        }
        writeCart(cartList)
    }

    fun isBookInCart(book: Book): Boolean {
        val cartList = getCart()
        cartList.forEach {
            if (it.book.bookID == book.bookID)
                return true
        }
        return false
    }
}
