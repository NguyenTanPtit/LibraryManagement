package com.example.librarymanagement.data.remote.login

import com.example.librarymanagement.data.remote.ApiPath
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {

    @FormUrlEncoded
    @POST(ApiPath.LOGIN)
    suspend fun login(
        @Field("username") sso:String,
        @Field("password") password:String
    ):Response<LoginResponse>
}