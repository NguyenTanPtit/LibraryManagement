package com.example.librarymanagement.ui.general.notification


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarymanagement.base.Resource
import com.example.librarymanagement.base.onResult
import com.example.librarymanagement.data.repository.NotificationRepository
import com.example.librarymanagement.models.ApiResponse
import com.example.librarymanagement.models.Notification
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(val repo :NotificationRepository): ViewModel()  {

    private var _notifications = MutableLiveData<Resource<ApiResponse<List<Notification>>?>>()
    val notifications: MutableLiveData<Resource<ApiResponse<List<Notification>>?>>
        get() = _notifications

    private var _deleteNotification = MutableLiveData<Resource<ApiResponse<Notification>?>>()
    val deleteNotification: MutableLiveData<Resource<ApiResponse<Notification>?>>
        get() = _deleteNotification


    private var _saveNotification = MutableLiveData<Resource<ApiResponse<Notification>?>>()
    val saveNotification: MutableLiveData<Resource<ApiResponse<Notification>?>>
        get() = _saveNotification

    fun getAllNotifications(id: Long) {
        _notifications.value = Resource.Loading()

        viewModelScope.launch {
            repo.getAllNotifications(id).onResult(
                onSuccess = {
                    _notifications.value = Resource.Success(it)
                },
                onError = {
                    _notifications.value = Resource.Error(it)
                }
            )
        }

    }

    fun deleteNotification(id: Long) {
        _deleteNotification.value = Resource.Loading()

        viewModelScope.launch {
            repo.deleteNotification(id).onResult(
                onSuccess = {
                    _deleteNotification.value = Resource.Success(it)
                },
                onError = {
                    _deleteNotification.value = Resource.Error(it)
                }
            )
        }

    }

    fun saveNotification(notification: Notification) {
        _saveNotification.value = Resource.Loading()

        viewModelScope.launch {
            repo.saveNotification(notification).onResult(
                onSuccess = {
                    _saveNotification.value = Resource.Success(it)
                },
                onError = {
                    _saveNotification.value = Resource.Error(it)
                }
            )
        }

    }
}