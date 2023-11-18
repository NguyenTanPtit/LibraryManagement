package com.example.librarymanagement.data.repository

import com.example.librarymanagement.data.remote.dataSource.AuthorDataSource
import javax.inject.Inject

class AuthorRepository @Inject constructor(val dataSource: AuthorDataSource) {

    suspend fun getAll() = dataSource.getAll()


    suspend fun create(author: String) = dataSource.create(author)

    suspend fun update(author: com.example.librarymanagement.models.AuthorMain) = dataSource.update(author)

    suspend fun delete(id:Long) = dataSource.delete(id)
}