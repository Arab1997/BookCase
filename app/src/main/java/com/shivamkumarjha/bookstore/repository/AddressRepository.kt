package com.shivamkumarjha.bookstore.repository

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shivamkumarjha.bookstore.model.Address
import java.io.File
import java.io.IOException
import java.io.OutputStreamWriter

class AddressRepository(private val file: File) {
    private val gson = Gson()
    private val commonFileRepository = CommonFileRepository(file)
    private val tag = "AddressRepository"

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
        if (!commonFileRepository.fileExists()) {
            return arrayListOf()
        }
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

    private fun getIndex(address: Address, addressList: ArrayList<Address>): Int {
        for (index in 0 until addressList.size) {
            if (addressList[index].addressId == address.addressId)
                return index
        }
        return -1
    }

    fun updateAddress(address: Address) {
        val addressList = getAddress()
        val index = getIndex(address, addressList)
        if (index != -1)
            addressList[index] = address
        else {
            addressList.removeAll { it.addressId == address.addressId }
            addressList.add(address)
        }
        writeAddress(addressList)
    }
}