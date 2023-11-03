package com.example.librarymanagement.data.repository

import com.example.librarymanagement.data.remote.dataSource.BookDataSource
import javax.inject.Inject

class BookDetailRepository  @Inject constructor(val dataSource: BookDataSource){

    suspend fun deleteBook(id: String) = dataSource.deleteBook(id)
}