package com.example.librarymanagement.data.remote.book.service

import com.example.librarymanagement.data.remote.ApiPath
import com.example.librarymanagement.models.ApiResponse
import com.example.librarymanagement.models.Fine
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface FineService {

    @POST(ApiPath.GET_FINE_BY_USER_ID)
    @FormUrlEncoded
    suspend fun getFineByUserId(@Field("id")id:Long): Response<ApiResponse<Fine>>
}