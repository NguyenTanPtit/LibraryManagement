package com.example.librarymanagement.data.remote.dataSource

import com.example.librarymanagement.base.BaseDataSource
import com.example.librarymanagement.data.remote.book.service.QueueService
import javax.inject.Inject

class QueueDataSource @Inject constructor(val service: QueueService) : BaseDataSource() {

    suspend fun getQueueByBookId(id: Long) = getResultWithResponse {
        service.getQueueByBookId(id)
    }

    suspend fun joinQueue(
        bookId: Long,
        userId: Long,
        dateBorrow: String,
        dateDue: String,
        position: Int,
    ) = getResultWithResponse {
        service.joinQueue(bookId, userId, dateBorrow, dateDue, position)
    }
}