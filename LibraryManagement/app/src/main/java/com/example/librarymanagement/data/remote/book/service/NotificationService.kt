package com.example.librarymanagement.data.remote.book.service


import com.example.librarymanagement.data.remote.ApiPath
import com.example.librarymanagement.models.ApiResponse
import com.example.librarymanagement.models.Notification
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface NotificationService {

    @POST(ApiPath.GET_ALL_NOTIFICATIONS)
    @FormUrlEncoded
    suspend fun getAllNotifications(@Field("id")id: Long): Response<ApiResponse<List<Notification>>>

    @POST(ApiPath.DELETE_NOTIFICATION)
    @FormUrlEncoded
    suspend fun deleteNotification(@Field("id")id: Long): Response<ApiResponse<Notification>>

    @POST(ApiPath.ADD_NOTIFICATION)
    suspend fun saveNotification(@Body notification: Notification): Response<ApiResponse<Notification>>
}