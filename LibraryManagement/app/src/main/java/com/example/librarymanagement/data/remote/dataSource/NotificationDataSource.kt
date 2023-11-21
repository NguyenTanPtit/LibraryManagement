package com.example.librarymanagement.data.remote.dataSource

import com.example.librarymanagement.base.BaseDataSource
import com.example.librarymanagement.data.remote.book.service.NotificationService
import com.example.librarymanagement.models.Notification
import javax.inject.Inject

class NotificationDataSource @Inject constructor(val service: NotificationService) : BaseDataSource() {

    suspend fun getAllNotifications(id: Long) = getResultWithResponse {
        service.getAllNotifications(id)
    }

    suspend fun deleteNotification(id: Long) = getResultWithResponse {
        service.deleteNotification(id)
    }

    suspend fun saveNotification(notification: Notification) = getResultWithResponse {
        service.saveNotification(notification)
    }
}