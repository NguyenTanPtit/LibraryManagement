package com.example.librarymanagement.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Book(
    val id: String,
    var title:String,
    var cover: String,
    var state: String,
    var description: String,
    var author: Author,
    var category: Category,
    var price:String,
    var pageNumber:String
):Serializable

data class Author(
    val id:String,
    val name:String
)

data class Category(val id: Long,
 val name:String)

data class GetBookResponse(
    val message: String?,
    val data: List<Book>?
) {
}
data class CategoriesResponse (val message:String?, val data:List<Category>?)
data class AuthorResponse (val message:String?, val data:List<Author>?)
data class BookDetailResponse (val id: String?,
                               var title:String?,
                               @SerializedName("image")
                               var cover: String?,
                               var state: String?,
                               var description: String?,
                               var authorId: Long?,
                               var categoryId: Long?,
                               val categoryName: String?,
                               val authorName: String?,
                               var category: Category?,
                               var price:String?,
                               var pageNumber:String?, ): Serializable
data class BookResponse (val message:String?, val data:List<BookDetailResponse>?)

data class ApiResponse<T>(
    val message: String?,
    val data: T?
)