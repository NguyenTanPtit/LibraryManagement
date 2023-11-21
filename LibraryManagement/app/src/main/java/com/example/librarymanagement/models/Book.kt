package com.example.librarymanagement.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Book(
    val id: String? = null,
    var title:String? = null,
    @SerializedName("image")
    var cover: String? = null,
    var state: String? = null,
    var description: String? = null,
    var author: Author? = null,
    var category: Category? = null,
    var price:String? = null,
    var pageNumber:String?= null
):Serializable

data class Author(
    val id:String? = null,
    val name:String? = null,
)

data class AuthorMain(
    val id:Long? = null,
    val name:String? = null,
)

data class Category(
    val id: Long? = null,
 val name:String? = null,)

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
                               var pageNumber:String?,
                               var isChecked : Boolean = false): Serializable
data class BookResponse (val message:String?, val data:List<BookDetailResponse>?)

data class ApiResponse<T>(
    val message: String?,
    val data: T?
)

data class UpdateBookResponse(
    val message: String?
)