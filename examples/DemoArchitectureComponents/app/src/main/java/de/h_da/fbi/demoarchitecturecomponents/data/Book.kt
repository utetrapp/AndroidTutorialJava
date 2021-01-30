package de.h_da.fbi.demoarchitecturecomponents.data

import java.io.Serializable

data class Book(val id: String, val content: String, val details: String) : Serializable {
    override fun toString(): String = content
}