package com.example.librarymanagement.ui.login

import com.example.librarymanagement.data.remote.LoginDataSource
import javax.inject.Inject

class LoginRepository @Inject constructor(val dataSource: LoginDataSource) {

    suspend fun login(username:String, password:String) = dataSource.login(username,password)

}