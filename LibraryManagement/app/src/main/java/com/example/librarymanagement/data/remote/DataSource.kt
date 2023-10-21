package com.example.librarymanagement.data.remote

import com.example.librarymanagement.BuildConfig
import com.example.librarymanagement.data.remote.login.LoginService
import com.google.gson.internal.GsonBuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataSource {
    private var retrofit: Retrofit? = null

    fun getLogin(): LoginService?{
        if(retrofit ==null){
            retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
        return retrofit?.create(LoginService::class.java)
    }


}