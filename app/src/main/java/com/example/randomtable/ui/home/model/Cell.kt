package com.example.randomtable.ui.home.model

import java.io.Serializable
import java.util.*

data class Cell(
    val id: String = UUID.randomUUID().toString(),
    val number: Int = (0..500).random()
): Serializable