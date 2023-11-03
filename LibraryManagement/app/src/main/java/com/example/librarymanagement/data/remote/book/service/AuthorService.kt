package com.example.librarymanagement.data.remote.book.service

import com.example.librarymanagement.data.remote.ApiPath
import com.example.librarymanagement.models.AuthorResponse
import retrofit2.Response
import retrofit2.http.POST

interface AuthorService {

    @POST(ApiPath.GET_ALL_AUTHORS)
    suspend fun getAll(): Response<AuthorResponse>
}