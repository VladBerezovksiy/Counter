package com.example.counter

interface Model {

    fun getItems(): List<String>
    fun addItems()
    fun removeItems()

}