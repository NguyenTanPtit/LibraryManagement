package com.example.librarymanagement.data.remote.dataSource

import com.example.librarymanagement.base.BaseDataSource
import com.example.librarymanagement.data.remote.book.service.UserService
import com.example.librarymanagement.models.User
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val userService: UserService
): BaseDataSource() {
    suspend fun getAll() = getResultWithResponse {
        userService.getAll()
    }

    suspend fun create(user: User) = getResultWithResponse {
        userService.create(user)
    }

    suspend fun update(user: User) = getResultWithResponse {
        userService.update(user)
    }

    suspend fun delete(id: Int) = getResultWithResponse {
        userService.delete(id)
    }
}