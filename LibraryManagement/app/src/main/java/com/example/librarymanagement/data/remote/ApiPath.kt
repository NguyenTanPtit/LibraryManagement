package com.example.librarymanagement.data.remote

class ApiPath {
    companion object {
        const val LOGIN = "auth/login"

        const val GET_CATEGORIES = "categories/getAll"
        const val GET_BOOKS_BY_CATEGORY = "bookController/getAllByCategory"
        const val ADD_CATEGORY = "categories/create"
    }

}