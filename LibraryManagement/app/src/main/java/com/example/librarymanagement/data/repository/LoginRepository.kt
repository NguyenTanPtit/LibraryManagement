package com.example.librarymanagement.data.repository

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.librarymanagement.data.remote.LoginDataSource
import com.example.librarymanagement.models.User
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Inject

class LoginRepository @Inject constructor(val dataSource: LoginDataSource,
                                          private val sharedPreferences: SharedPreferences) {

    suspend fun login(username:String, password:String) = dataSource.login(username,password)

    fun saveLoginData(username: String, password: String, rememberMe: Boolean) {
        sharedPreferences.edit().apply {
            putString("username", username)
            putString("password", password)
            putBoolean("remember_me", rememberMe)
        }.apply()
    }

    fun getLoginData(): Triple<String, String, Boolean> {
        val username = sharedPreferences.getString("username", "") ?: ""
        val password = sharedPreferences.getString("password", "") ?: ""
        val rememberMe = sharedPreferences.getBoolean("remember_me", false)
        return Triple(username, password, rememberMe)
    }

    // clear login data
    fun clearLoginData() {
        sharedPreferences.edit().apply {
            putString("username", "")
            putString("password", "")
            putBoolean("remember_me", false)
        }.apply()
    }

}