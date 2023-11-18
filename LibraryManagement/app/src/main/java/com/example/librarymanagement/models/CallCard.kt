package com.example.librarymanagement.models

data class CallCard(
    val id: Int? = null,
    val books: List<BookDetailResponse>? = null,
    val user: User? = null,
    val borrowDate: String? = null,
    val dueDate: String? = null,
    val state: String? = null,
    val note: String? = null,
) {
}