package com.example.librarymanagement.data.repository

import com.example.librarymanagement.data.remote.dataSource.CallCardDataSource
import com.example.librarymanagement.models.CallCardRequest
import javax.inject.Inject

class CallCardRepository @Inject constructor(
    private val callCardDataSource: CallCardDataSource
) {

    suspend fun getAllCallCards() = callCardDataSource.getAllCallCards()

    suspend fun updateCallCard(callCardRequest: CallCardRequest) = callCardDataSource.updateCallCard(callCardRequest)

    suspend fun createCallCard(callCardRequest: CallCardRequest) = callCardDataSource.createCallCard(callCardRequest)

    suspend fun getCallCardById(id: Long) = callCardDataSource.getCallCardById(id)

    suspend fun getCallCardByBookId(id: Long) = callCardDataSource.getCallCardByBookId(id)
}