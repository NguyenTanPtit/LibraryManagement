package com.example.librarymanagement.data.remote.dataSource

import com.example.librarymanagement.base.BaseDataSource
import com.example.librarymanagement.data.remote.login.LoginService
import javax.inject.Inject


class LoginDataSource @Inject constructor(val loginService: LoginService) : BaseDataSource(){

    suspend fun login(username:String, password:String) = getResultWithResponse {
        loginService.login(username,password)
    }

}