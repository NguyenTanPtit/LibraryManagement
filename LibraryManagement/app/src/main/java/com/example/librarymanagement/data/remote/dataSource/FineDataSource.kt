package com.example.librarymanagement.data.remote.dataSource

import com.example.librarymanagement.base.BaseDataSource
import com.example.librarymanagement.data.remote.book.service.FineService
import javax.inject.Inject

class FineDataSource@Inject constructor(val service: FineService): BaseDataSource() {

            suspend fun getFineByUserId(id: Long) = getResultWithResponse {
                service.getFineByUserId(id)
            }
}