package com.example.counter

object ModelImpl : Model {

    private var items = mutableListOf<String>()

    init {
        val savedItems = MyApplication.getApp().getSaveData()
        if (savedItems.isNotEmpty()) {
            items.addAll(savedItems)
        }
    }

    override fun getItems(): List<String> = items

    override fun addItems() {
        items.add("Item ${items.size + 1}")
    }

    override fun removeItems() {
        if (items.isNotEmpty()) {
            items.removeLast()
//            items.removeAt(items.size - 1)
        }
    }
}