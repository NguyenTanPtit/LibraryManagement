package com.example.librarymanagement.data.remote.book.service

import com.example.librarymanagement.data.remote.ApiPath
import com.example.librarymanagement.models.QueueResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface QueueService {

    @POST(ApiPath.GET_QUEUE_BY_BOOK_ID)
    @FormUrlEncoded
    suspend fun getQueueByBookId(@Field("id") id: Long): Response<QueueResponse>

    @POST(ApiPath.JOIN_QUEUE)
    @FormUrlEncoded
    suspend fun joinQueue(
        @Field("bookId") bookId: Long,
        @Field("userId") userId: Long,
        @Field("dateBorrow") dateBorrow: String,
        @Field("dateDue") dateDue: String,
        @Field("position") position: Int,
    ): Response<QueueResponse>
}