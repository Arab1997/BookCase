package com.shivamkumarjha.bookstore.repository

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shivamkumarjha.bookstore.model.*
import java.io.File
import java.io.IOException
import java.io.OutputStreamWriter

class UserRepository(private val file: File, private val email: String?) {
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
        if (!commonFileRepository.fileExists())
            return arrayListOf()
        val jsonString = commonFileRepository.readFromFile()
        val detailsTypeToken = object : TypeToken<ArrayList<User>>() {}.type
        return gson.fromJson<ArrayList<User>>(jsonString, detailsTypeToken)
    }

    private fun getLoggedInUserIndex(): Int {
        val users = getUsers()
        for (index in 0 until users.size) {
            if (users[index].email == email)
                return index
        }
        return -1
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
        val users = getUsers()
        users[getLoggedInUserIndex()].addresses = address
        val data = gson.toJson(users)
        try {
            val outputStreamWriter = OutputStreamWriter(file.outputStream())
            outputStreamWriter.write(data)
            outputStreamWriter.close()
        } catch (e: IOException) {
            Log.e(tag, "File write failed: $e")
        }
    }

    fun getAddress(): ArrayList<Address> {
        val users = getUsers()
        return users[getLoggedInUserIndex()].addresses
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

    private fun writeCartItems(cartItems: ArrayList<CartItem>) {
        val users = getUsers()
        users[getLoggedInUserIndex()].cartItems = cartItems
        val data = gson.toJson(users)
        try {
            val outputStreamWriter = OutputStreamWriter(file.outputStream())
            outputStreamWriter.write(data)
            outputStreamWriter.close()
        } catch (e: IOException) {
            Log.e(tag, "File write failed: $e")
        }
    }

    fun getCartItems(): ArrayList<CartItem> {
        val users = getUsers()
        return users[getLoggedInUserIndex()].cartItems
    }

    fun addCartItem(cartItem: CartItem) {
        val cartList = getCartItems()
        cartList.add(cartItem)
        writeCartItems(cartList)
    }

    fun removeCartItem(cartItem: CartItem) {
        val cartList = getCartItems()
        cartList.removeAll { it.itemId == cartItem.itemId }
        writeCartItems(cartList)
    }

    private fun getCartItemIndex(cartItem: CartItem, cartItemList: ArrayList<CartItem>): Int {
        for (index in 0 until cartItemList.size) {
            if (cartItemList[index].itemId == cartItem.itemId)
                return index
        }
        return -1
    }

    fun updateCartItem(cartItem: CartItem) {
        val cartList = getCartItems()
        val index = getCartItemIndex(cartItem, cartList)
        if (index != -1)
            cartList[index] = cartItem
        else {
            cartList.removeAll { it.itemId == cartItem.itemId }
            cartList.add(cartItem)
        }
        writeCartItems(cartList)
    }

    fun isBookInCartItems(book: Book): Boolean {
        val cartList = getCartItems()
        cartList.forEach {
            if (it.book.bookID == book.bookID)
                return true
        }
        return false
    }

    fun makeCartItemsEmpty() {
        writeCartItems(arrayListOf())
    }

    private fun writeOrders(orders: ArrayList<Order>) {
        val users = getUsers()
        users[getLoggedInUserIndex()].orders = orders
        val data = gson.toJson(users)
        try {
            val outputStreamWriter = OutputStreamWriter(file.outputStream())
            outputStreamWriter.write(data)
            outputStreamWriter.close()
        } catch (e: IOException) {
            Log.e(tag, "File write failed: $e")
        }
    }

    fun getOrders(): ArrayList<Order> {
        val users = getUsers()
        return users[getLoggedInUserIndex()].orders
    }

    fun addOrder(order: Order) {
        val orderList = getOrders()
        orderList.add(order)
        writeOrders(orderList)
    }

    private fun writeWishItems(wishItems: ArrayList<WishItem>) {
        val users = getUsers()
        users[getLoggedInUserIndex()].wishItems = wishItems
        val data = gson.toJson(users)
        try {
            val outputStreamWriter = OutputStreamWriter(file.outputStream())
            outputStreamWriter.write(data)
            outputStreamWriter.close()
        } catch (e: IOException) {
            Log.e(tag, "File write failed: $e")
        }
    }

    fun getWishItems(): ArrayList<WishItem> {
        val users = getUsers()
        return users[getLoggedInUserIndex()].wishItems
    }

    fun addWishItem(wishItem: WishItem) {
        val wishList = getWishItems()
        wishList.add(wishItem)
        writeWishItems(wishList)
    }

    fun removeWishItem(wishItem: WishItem) {
        val wishList = getWishItems()
        wishList.removeAll { it.wishId == wishItem.wishId }
        writeWishItems(wishList)
    }

    private fun getWishItemIndex(wishItem: WishItem, wishItemList: ArrayList<WishItem>): Int {
        for (index in 0 until wishItemList.size) {
            if (wishItemList[index].wishId == wishItem.wishId)
                return index
        }
        return -1
    }

    fun updateWishItem(wishItem: WishItem) {
        val wishList = getWishItems()
        val index = getWishItemIndex(wishItem, wishList)
        if (index != -1)
            wishList[index] = wishItem
        else {
            wishList.removeAll { it.wishId == wishItem.wishId }
            wishList.add(wishItem)
        }
        writeWishItems(wishList)
    }

    fun isBookInWishItems(book: Book): Boolean {
        val wishList = getWishItems()
        wishList.forEach {
            if (it.book.bookID == book.bookID)
                return true
        }
        return false
    }
}