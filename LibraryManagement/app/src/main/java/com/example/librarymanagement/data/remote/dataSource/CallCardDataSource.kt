package com.example.librarymanagement.data.remote.dataSource

import com.example.librarymanagement.base.BaseDataSource
import com.example.librarymanagement.data.remote.book.service.CallCardService
import javax.inject.Inject

class CallCardDataSource @Inject constructor(
    private val callCardService: CallCardService
): BaseDataSource() {

    suspend fun getAllCallCards() = getResultWithResponse {
        callCardService.getAllCallCards()
    }
}