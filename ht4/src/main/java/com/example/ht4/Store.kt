package com.example.ht4


class Store
private constructor() {
    private object Holder {
        val INSTANCE = Store()
    }

    companion object {
        val instance: Store by lazy { Holder.INSTANCE }
    }

    private val items: MutableList<Item> = mutableListOf()

    fun add(item: Item): Unit {
        this.items.add(item)
    }

    fun getStore(): Store = instance

    fun get(index: Int): Item = this.items[index]

    fun size(): Int = this.items.size

    fun getAll(): MutableList<Item> = this.items
}
