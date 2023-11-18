package com.example.librarymanagement.data.remote.dataSource

import com.example.librarymanagement.base.BaseDataSource
import com.example.librarymanagement.data.remote.book.service.AuthorService
import com.example.librarymanagement.models.Author
import com.example.librarymanagement.models.AuthorMain
import javax.inject.Inject

class AuthorDataSource @Inject constructor(val service: AuthorService): BaseDataSource() {

        suspend fun getAll() = getResultWithResponse {
            service.getAll()
        }


        suspend fun create(author: String) = getResultWithResponse {
            service.create(author)
        }

    suspend fun update(author: AuthorMain) = getResultWithResponse {
        service.update(author)
    }

    suspend fun delete(id:Long) = getResultWithResponse {
        service.delete(id)
    }
}