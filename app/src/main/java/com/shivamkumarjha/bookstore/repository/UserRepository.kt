package com.shivamkumarjha.bookstore.repository

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shivamkumarjha.bookstore.model.*
import java.io.File
import java.io.IOException
import java.io.OutputStreamWriter

class UserRepository(private val file: File) {
    private val gson = Gson()
    private val commonFileRepository = CommonFileRepository(file)
    private val tag = "UserRepository"

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

    private fun getUsers(): ArrayList<User> {
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

    fun isEmailNotRegistered(email: String): Boolean {
        if (getUsers().any { it.email == email })
            return false
        return true
    }

    fun login(email: String, password: String): LoggedInUserView? {
        getUsers().forEach {
            if (it.email == email && it.password == password)
                return LoggedInUserView(it.name, it.email)
        }
        return null
    }

    private fun writeAddress(address: ArrayList<Address>) {
        val data = gson.toJson(address)
        try {
            val outputStreamWriter = OutputStreamWriter(file.outputStream())
            outputStreamWriter.write(data)
            outputStreamWriter.close()
        } catch (e: IOException) {
            Log.e(tag, "File write failed: $e")
        }
    }

    fun getAddress(): ArrayList<Address> {
        if (!commonFileRepository.fileExists())
            return arrayListOf()
        val jsonString = commonFileRepository.readFromFile()
        val detailsTypeToken = object : TypeToken<List<Address>>() {}.type
        return gson.fromJson(jsonString, detailsTypeToken)
    }

    fun addAddress(address: Address) {
        val addressList = getAddress()
        addressList.add(address)
        writeAddress(addressList)
    }

    fun removeAddress(address: Address) {
        val addressList = getAddress()
        addressList.removeAll { it.addressId == address.addressId }
        writeAddress(addressList)
    }

    private fun getAddressIndex(address: Address, addressList: ArrayList<Address>): Int {
        for (index in 0 until addressList.size) {
            if (addressList[index].addressId == address.addressId)
                return index
        }
        return -1
    }

    fun updateAddress(address: Address) {
        val addressList = getAddress()
        val index = getAddressIndex(address, addressList)
        if (index != -1)
            addressList[index] = address
        else {
            addressList.removeAll { it.addressId == address.addressId }
            addressList.add(address)
        }
        writeAddress(addressList)
    }

    private fun writeCart(cartItem: ArrayList<CartItem>) {
        val data = gson.toJson(cartItem)
        try {
            val outputStreamWriter = OutputStreamWriter(file.outputStream())
            outputStreamWriter.write(data)
            outputStreamWriter.close()
        } catch (e: IOException) {
            Log.e(tag, "File write failed: $e")
        }
    }

    fun getCart(): ArrayList<CartItem> {
        val jsonString = commonFileRepository.readFromFile()
        val detailsTypeToken = object : TypeToken<List<User>>() {}.type
        val users = gson.fromJson<ArrayList<User>>(jsonString, detailsTypeToken)
        return users[0].cartItemList
    }

    fun addCart(cartItem: CartItem) {
        val cartList = getCart()
        cartList.add(cartItem)
        writeCart(cartList)
    }

    fun removeCart(cartItem: CartItem) {
        val cartList = getCart()
        cartList.removeAll { it.itemId == cartItem.itemId }
        writeCart(cartList)
    }

    private fun getCartIndex(cartItem: CartItem, cartItemList: ArrayList<CartItem>): Int {
        for (index in 0 until cartItemList.size) {
            if (cartItemList[index].itemId == cartItem.itemId)
                return index
        }
        return -1
    }

    fun updateCart(cartItem: CartItem) {
        val cartList = getCart()
        val index = getCartIndex(cartItem, cartList)
        if (index != -1)
            cartList[index] = cartItem
        else {
            cartList.removeAll { it.itemId == cartItem.itemId }
            cartList.add(cartItem)
        }
        writeCart(cartList)
    }

    fun isBookInCart(book: Book): Boolean {
        val cartList = getCart()
        cartList.forEach {
            if (it.book?.bookID == book.bookID)
                return true
        }
        return false
    }

    fun makeCartEmpty() {
        writeCart(arrayListOf())
    }

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