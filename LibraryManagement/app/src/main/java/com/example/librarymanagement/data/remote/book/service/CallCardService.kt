package com.example.librarymanagement.data.remote.book.service

import com.example.librarymanagement.data.remote.ApiPath
import com.example.librarymanagement.models.ApiResponse
import com.example.librarymanagement.models.CallCard
import com.example.librarymanagement.models.CallCardRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface CallCardService {

    @GET(ApiPath.GET_ALL_CALL_CARDS)
    suspend fun getAllCallCards(): Response<ApiResponse<List<CallCard>>>

    @POST(ApiPath.UPDATE_CALL_CARD)
    suspend fun updateCallCard(@Body callCard: CallCardRequest): Response<ApiResponse<CallCard>>

    @POST(ApiPath.CREATE_CALL_CARD)
    suspend fun createCallCard(@Body callCard: CallCardRequest): Response<ApiResponse<CallCard>>

    @POST(ApiPath.GET_ALL_BY_USER_ID)
    @FormUrlEncoded
    suspend fun getCallCardById(@Field("id")id: Long): Response<ApiResponse<List<CallCard>>>

    @POST(ApiPath.GET_ALL_BY_BOOK_ID)
    @FormUrlEncoded
    suspend fun getCallCardByBookId(@Field("id")id: Long): Response<ApiResponse<List<CallCard>>>
}