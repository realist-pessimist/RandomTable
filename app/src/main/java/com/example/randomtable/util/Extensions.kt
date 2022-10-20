package com.example.randomtable.util

fun <T> swap(list: MutableList<T>, i: Int, j: Int) {
    val temp = list[i]
    list[i] = list[j]
    list[j] = temp
}

fun <T> List<T>.replace(newValue: T, block: (T) -> Boolean): List<T> {
    return map {
        if (block(it)) newValue else it
    }
}

fun <T> MutableList<T>.shuffled(): List<T>{
    val firstShuffleIndex = (0 until this.size).random()
    val secondShuffleIndex = ((0 until this.size).filter { it != firstShuffleIndex }.random())
    swap(this, firstShuffleIndex, secondShuffleIndex)
    return this
}