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
    }

}