package com.example.librarymanagement.data.remote.book.service

import com.example.librarymanagement.data.remote.ApiPath
import com.example.librarymanagement.models.ApiResponse
import com.example.librarymanagement.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {

    @GET(ApiPath.GET_ALL_USERS)
    suspend fun getAll(): Response<List<User>>

    @POST(ApiPath.CREATE_USER)
    suspend fun create(@Body user: User): Response<ApiResponse<User>>


    @POST(ApiPath.UPDATE_USER)
    suspend fun update(@Body user: User): Response<ApiResponse<User>>

    @POST(ApiPath.DELETE_USER)
    @FormUrlEncoded
    suspend fun delete(@Field("id") id: Int): Response<ApiResponse<User>>


}