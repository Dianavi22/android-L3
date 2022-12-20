package com.example.scalim

import io.paperdb.Paper

interface IPaperWrapper {
    fun write(key: String, value: List<Food>)
    fun read(key: String): List<Food>
}

class PaperWrapper : IPaperWrapper {
    override fun write(key: String, value: List<Food>) {
        Paper.book().write(key, value)
    }

    override fun read(key: String): List<Food> {
        return Paper.book().read<List<Food>>(key)?: listOf()
    }

}