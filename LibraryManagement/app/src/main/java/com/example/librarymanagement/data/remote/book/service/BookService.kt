package com.example.librarymanagement.data.remote.book.service

import com.example.librarymanagement.data.remote.ApiPath
import com.example.librarymanagement.models.ApiResponse
import com.example.librarymanagement.models.BookDetailResponse
import com.example.librarymanagement.models.BookResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface BookService {

    @POST(ApiPath.GET_BOOKS)
    suspend fun getBooks(): Response<BookResponse>

    @POST(ApiPath.DELETE_BOOK)
    @FormUrlEncoded
    suspend fun deleteBook(@Field("id") id: String): Response<ApiResponse<BookDetailResponse>>
}