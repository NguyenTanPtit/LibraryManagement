package com.example.librarymanagement.data.repository

import com.example.librarymanagement.data.remote.dataSource.FineDataSource
import javax.inject.Inject

class FineRepository @Inject constructor(val dataSource: FineDataSource) {

        suspend fun getFineByUserId(id: Long) = dataSource.getFineByUserId(id)
}