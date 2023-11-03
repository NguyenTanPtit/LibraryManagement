package com.example.librarymanagement.data.repository

import com.example.librarymanagement.data.remote.dataSource.AuthorDataSource
import com.example.librarymanagement.data.remote.dataSource.BookDataSource
import com.example.librarymanagement.data.remote.dataSource.CategoriesDataSource
import javax.inject.Inject

class HomeRepository @Inject constructor(val dataSource: CategoriesDataSource, val authorDataSource: AuthorDataSource, val bookDataSource: BookDataSource){

    suspend fun getCategories() = dataSource.getCategories()

    suspend fun getBooksByCategory(categoryId: Long) = dataSource.getBooksByCategory(categoryId)

    suspend fun addCategory(name: String) = dataSource.addCategory(name)

    suspend fun getAuthors() = authorDataSource.getAll()

    suspend fun getBooks() = bookDataSource.getBooks()
}