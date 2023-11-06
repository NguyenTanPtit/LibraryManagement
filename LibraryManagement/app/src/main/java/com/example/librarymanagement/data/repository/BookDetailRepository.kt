package com.example.librarymanagement.data.repository

import com.example.librarymanagement.data.remote.dataSource.BookDataSource
import com.example.librarymanagement.data.remote.dataSource.FineDataSource
import com.example.librarymanagement.data.remote.dataSource.QueueDataSource
import javax.inject.Inject

class BookDetailRepository  @Inject constructor(val dataSource: BookDataSource, val queueDataSource: QueueDataSource, val fineDataSource: FineDataSource) {

    suspend fun deleteBook(id: String) = dataSource.deleteBook(id)

    suspend fun getQueueByBookId(id: Long) = queueDataSource.getQueueByBookId(id)

    suspend fun getFineByUserId(id: Long) = fineDataSource.getFineByUserId(id)

    suspend fun joinQueue(
        bookId: Long,
        userId: Long,
        dateBorrow: String,
        dateDue: String,
        position: Int,
    ) = queueDataSource.joinQueue(bookId, userId, dateBorrow, dateDue, position)
}