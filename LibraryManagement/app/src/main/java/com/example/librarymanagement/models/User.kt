package com.example.librarymanagement.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
open class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val userName: String,
    val avatar: String,
    val password: String,
    var fullName: String,
    var address: String,
    val email: String,
    val phoneNumber: String,
    val dateOfBirth: String,
    val role: String
)

