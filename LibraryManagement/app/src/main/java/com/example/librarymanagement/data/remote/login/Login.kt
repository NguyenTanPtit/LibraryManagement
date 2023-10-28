package com.example.librarymanagement.data.remote.login

import com.example.librarymanagement.models.User
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("message")
    val errorMsg: String?,
    @SerializedName("data")
    val user: User?,
    @SerializedName("httpStatus")
    val status: String?,
    val error:String?
) {
}