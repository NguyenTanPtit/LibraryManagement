package com.example.librarymanagement.data.remote.book.service

import com.example.librarymanagement.data.remote.ApiPath
import com.example.librarymanagement.models.ApiResponse
import com.example.librarymanagement.models.Author
import com.example.librarymanagement.models.AuthorMain
import com.example.librarymanagement.models.AuthorResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthorService {

    @POST(ApiPath.GET_ALL_AUTHORS)
    suspend fun getAll(): Response<AuthorResponse>

    @POST(ApiPath.CREATE_AUTHOR)
    @FormUrlEncoded
    suspend fun create(@Field("name") name: String): Response<ApiResponse<Author>>

    @POST(ApiPath.UPDATE_AUTHOR)
    suspend fun update(@Body author: AuthorMain): Response<ApiResponse<Author>>

    @POST(ApiPath.DELETE_AUTHOR)
    @FormUrlEncoded
    suspend fun delete(@Field("id") id:Long): Response<ApiResponse<Author>>
}