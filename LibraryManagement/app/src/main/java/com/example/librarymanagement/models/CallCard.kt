package com.example.librarymanagement.models

import java.io.Serializable

data class CallCard(
    val id: Int? = null,
    var books: List<BookDetailResponse>? = null,
    val user: User? = null,
    var borrowDate: String? = null,
    var dueDate: String? = null,
    var state: String? = null,
    var note: String? = null,
):Serializable{
}

class CallCardRequest {
     var id: Long? = null
     var borrowDate: String? = null
     var dueDate: String? = null
     var state: String? = null
    var note: String? = null
     var userId: Long? = null
     var books: List<BookDetailResponse>? = null
}

data class HistoryCallCard(
    var books: BookDetailResponse? = null,
    var borrowDate: String? = null,
    var dueDate: String? = null,
    var state: String? = null,
    var note: String? = null,
):Serializable{
}

data class Statistic(
    var user: User? = null,
    var borrowDate: String? = null,
    var dueDate: String? = null,
    var state: String? = null,
    var note: String? = null,
):Serializable{
}