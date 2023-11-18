package com.example.librarymanagement.data.remote.dataSource

import com.example.librarymanagement.base.BaseDataSource
import com.example.librarymanagement.data.remote.book.service.BookService
import com.example.librarymanagement.models.Book
import javax.inject.Inject

class BookDataSource @Inject constructor(val service: BookService): BaseDataSource() {

        suspend fun getBooks() = getResultWithResponse {
            service.getBooks()
        }

        suspend fun deleteBook(id: String) = getResultWithResponse {
            service.deleteBook(id)
        }

        suspend fun updateBook(book: Book) = getResultWithResponse {
            service.updateBook(book)
        }

        suspend fun addBook(book: Book) = getResultWithResponse {
            service.addBook(book)
        }
}