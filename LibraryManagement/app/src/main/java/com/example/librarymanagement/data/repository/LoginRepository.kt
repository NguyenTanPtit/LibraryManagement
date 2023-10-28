package com.example.librarymanagement.data.repository

import com.example.librarymanagement.data.remote.LoginDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Inject

class LoginRepository @Inject constructor(val dataSource: LoginDataSource) {

    suspend fun login(username:String, password:String) = dataSource.login(username,password)

}