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

        const val UPDATE_BOOK = "bookController/updateBook"

        const val ADD_BOOK = "bookController/addBook"

        const val CREATE_AUTHOR = "authors/addAuthor"

        const val UPDATE_AUTHOR = "authors/updateAuthor"

        const val DELETE_AUTHOR = "authors/deleteAuthor"

        const val GET_ALL_USERS = "student/getAll"

        const val CREATE_USER = "student/add"

        const val UPDATE_USER = "student/update"

        const val DELETE_USER = "student/delete"

        const val GET_ALL_CALL_CARDS = "card/getAll"

        const val GET_CALL_CARD_BY_ID = "card/getById"

        const val UPDATE_CALL_CARD = "card/update"

        const val CREATE_CALL_CARD = "card/add"

        const val DELETE_CALL_CARD = "card/delete"

        const val GET_ALL_BY_USER_ID = "card/getByUserId"
        const val GET_ALL_BY_BOOK_ID = "card/getByBookId"

        const val GET_ALL_NOTIFICATIONS = "notification/getAllByUserId"

        const val DELETE_NOTIFICATION = "notification/delete"

        const val ADD_NOTIFICATION = "notification/add"
    }

}