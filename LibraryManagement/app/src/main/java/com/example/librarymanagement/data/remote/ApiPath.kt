package com.example.librarymanagement.data.remote

import retrofit2.http.DELETE

class ApiPath {
    companion object {
        const val LOGIN = "auth/login"

        const val GET_CATEGORIES = "categories/getAll"
        const val GET_BOOKS = "bookController/getAll"
        const val GET_BOOKS_BY_CATEGORY = "bookController/getAllByCategory"
        const val ADD_CATEGORY = "categories/create"

        const val GET_ALL_AUTHORS = "authors/getAll"

        const val DELETE_BOOK = "bookController/deleteById"

        const val GET_QUEUE_BY_BOOK_ID = "queue/getQueueByBookId"

        const val GET_FINE_BY_USER_ID = "fineController/getFineByUserId"

        const val JOIN_QUEUE = "queue/joinQueue"
    }

}