package com.example.librarymanagement.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
open class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @SerializedName("username")
    var userName: String,
    var avatar: String,
    var password: String,
    var fullName: String,
    var address: String,
    var email: String,
    var phoneNumber: String,
    val dateOfBirth: String,
    val role: String
): Serializable

