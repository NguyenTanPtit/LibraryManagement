package com.example.librarymanagement.models

data class Queue(
    val id: Int,
    val bookId: Int?,
    val borrowQueueDetailDtos: List<QueueDetail>?
)


data class QueueDetail(
    val id: Int?,
    val userId: Int?,
    val position:Int?,
    val dateBorrow: String?,
    val dateDue: String?
)

data class QueueResponse(
    val message: String?,
    val data: Queue
)

data class Fine(
    val fineId: Int?,
    val username: String?,
    val userId: Int?,
    val missingBorrow:Int?,
    val lateReturn :Int?,
    val damageBook:Int?
)