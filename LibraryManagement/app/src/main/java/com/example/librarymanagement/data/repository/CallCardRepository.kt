package com.example.librarymanagement.data.repository

import com.example.librarymanagement.data.remote.dataSource.CallCardDataSource
import javax.inject.Inject

class CallCardRepository @Inject constructor(
    private val callCardDataSource: CallCardDataSource
) {

    suspend fun getAllCallCards() = callCardDataSource.getAllCallCards()
}