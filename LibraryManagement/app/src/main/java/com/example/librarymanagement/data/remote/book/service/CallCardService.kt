package com.example.librarymanagement.data.remote.book.service

import com.example.librarymanagement.data.remote.ApiPath
import com.example.librarymanagement.models.ApiResponse
import com.example.librarymanagement.models.CallCard
import retrofit2.Response
import retrofit2.http.GET

interface CallCardService {

    @GET(ApiPath.GET_ALL_CALL_CARDS)
    suspend fun getAllCallCards(): Response<ApiResponse<List<CallCard>>>
}