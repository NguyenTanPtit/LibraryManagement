package com.example.librarymanagement.models

import androidx.room.Entity

@Entity
data class Function(
    val id:String,
    val name: String
) {
}