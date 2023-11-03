package com.example.librarymanagement.data.remote.dataSource

import com.example.librarymanagement.base.BaseDataSource
import com.example.librarymanagement.data.remote.book.service.AuthorService
import javax.inject.Inject

class AuthorDataSource @Inject constructor(val service: AuthorService): BaseDataSource() {

        suspend fun getAll() = getResultWithResponse {
            service.getAll()
        }
}