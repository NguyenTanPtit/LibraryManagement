package com.example.librarymanagement.models

data class Book(
    val id: String,
    val title:String,
    val author: Author,
    var category: Category,
    val price:String,
    var pageNumber:String
) {
}

data class Author(
    val id:String,
    val name:String
)

data class Category(val id: String,
 val name:String)