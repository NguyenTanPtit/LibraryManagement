package com.example.librarymanagement.data.repository

import com.example.librarymanagement.data.remote.dataSource.NotificationDataSource
import com.example.librarymanagement.models.Notification
import javax.inject.Inject

class NotificationRepository @Inject constructor(
    private val notificationDataSource: NotificationDataSource
) {

    suspend fun getAllNotifications(id: Long) = notificationDataSource.getAllNotifications(id)

    suspend fun deleteNotification(id: Long) = notificationDataSource.deleteNotification(id)

    suspend fun saveNotification(notification: Notification) = notificationDataSource.saveNotification(notification)
}