package com.example.librarymanagement.data.remote.book.service

import com.example.librarymanagement.data.remote.ApiPath
import com.example.librarymanagement.models.ApiResponse
import com.example.librarymanagement.models.CategoriesResponse
import com.example.librarymanagement.models.Category
import com.example.librarymanagement.models.GetBookResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface CategoriesService {

    @POST(ApiPath.GET_CATEGORIES)
    suspend fun getAll(
    ): Response<CategoriesResponse>

    @POST(ApiPath.GET_BOOKS_BY_CATEGORY)
    @FormUrlEncoded
    suspend fun getBooksByCategory(
        @Field("id") categoryId: Long
    ): Response<GetBookResponse>

    @POST(ApiPath.ADD_CATEGORY)
    @FormUrlEncoded
    suspend fun addCategory(
        @Field("name") name: String
    ): Response<ApiResponse<Category>>

    @POST("categories/updateCategory")
    suspend fun updateCategory(
       @Body category: Category
    ): Response<ApiResponse<Category>>

    @POST("categories/deleteCategory")
    @FormUrlEncoded
    suspend fun deleteCategory(
        @Field("id") id:Long
    ): Response<ApiResponse<Category>>

}