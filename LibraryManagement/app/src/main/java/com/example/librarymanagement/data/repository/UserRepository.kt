package com.example.librarymanagement.data.repository

import com.example.librarymanagement.data.remote.dataSource.UserDataSource
import com.example.librarymanagement.models.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDataSource: UserDataSource
) {
    suspend fun getAll() = userDataSource.getAll()
    suspend fun create(user: User) = userDataSource.create(user)
    suspend fun update(user: User) = userDataSource.update(user)
    suspend fun delete(id: Int) = userDataSource.delete(id)
}