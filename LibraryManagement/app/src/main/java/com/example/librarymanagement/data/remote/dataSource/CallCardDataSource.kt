package com.example.librarymanagement.data.remote.dataSource

import com.example.librarymanagement.base.BaseDataSource
import com.example.librarymanagement.data.remote.book.service.CallCardService
import com.example.librarymanagement.models.CallCardRequest
import javax.inject.Inject

class CallCardDataSource @Inject constructor(
    private val callCardService: CallCardService
): BaseDataSource() {

    suspend fun getAllCallCards() = getResultWithResponse {
        callCardService.getAllCallCards()
    }


    suspend fun updateCallCard(callCardRequest: CallCardRequest) = getResultWithResponse {
        callCardService.updateCallCard(callCardRequest)
    }

    suspend fun createCallCard(callCardRequest: CallCardRequest) = getResultWithResponse {
        callCardService.createCallCard(callCardRequest)
    }

    suspend fun getCallCardById(id: Long) = getResultWithResponse {
        callCardService.getCallCardById(id)
    }

    suspend fun getCallCardByBookId(id: Long) = getResultWithResponse {
        callCardService.getCallCardByBookId(id)
    }
}