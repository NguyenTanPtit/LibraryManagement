package com.example.librarymanagement.ui.books.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarymanagement.base.Resource
import com.example.librarymanagement.base.onResult
import com.example.librarymanagement.data.repository.BookDetailRepository
import com.example.librarymanagement.data.repository.HomeRepository
import com.example.librarymanagement.models.ApiResponse
import com.example.librarymanagement.models.BookDetailResponse
import com.example.librarymanagement.models.Fine
import com.example.librarymanagement.models.QueueResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BookDetailViewModel @Inject constructor(val repo: BookDetailRepository): ViewModel(){

    private var _deleteBookLiveData = MutableLiveData<Resource<ApiResponse<BookDetailResponse>?>>()
    val deleteBookLiveData get() = _deleteBookLiveData

    private var _queueLiveData = MutableLiveData<Resource<QueueResponse?>>()
    val queueLiveData get() = _queueLiveData
    fun deleteBook(id: String) =
        viewModelScope.launch {
            repo.deleteBook(id).onResult(
                onSuccess = {
                    _deleteBookLiveData.postValue(Resource.Success(it))
                },
                onError = {
                    _deleteBookLiveData.postValue(Resource.Error(it))
                })
        }

    fun getQueueByBookId(id: Long) {
        viewModelScope.launch {
            repo.getQueueByBookId(id).onResult(
                onSuccess = {
                    _queueLiveData.postValue(Resource.Success(it))
                },
                onError = {
                    _queueLiveData.postValue(Resource.Error(it))
                })
        }
    }

    private var _fineLiveData = MutableLiveData<Resource<ApiResponse<Fine>?>>()
    val fineLiveData get() = _fineLiveData

    fun getFineByUserId(id: Long) {
        viewModelScope.launch {
            repo.getFineByUserId(id).onResult(
                onSuccess = {
                    _fineLiveData.postValue(Resource.Success(it))
                },
                onError = {
                    _fineLiveData.postValue(Resource.Error(it))
                })
        }
    }

    private var _joinQueueLiveData = MutableLiveData<Resource<QueueResponse?>>()
    val joinQueueLiveData get() = _joinQueueLiveData

    fun joinQueue(
        bookId: Long,
        userId: Long,
        dateBorrow: String,
        dateDue: String,
        position: Int,
    ) {
        viewModelScope.launch {
            repo.joinQueue(bookId, userId, dateBorrow, dateDue, position).onResult(
                onSuccess = {
                    _joinQueueLiveData.postValue(Resource.Success(it))
                },
                onError = {
                    _joinQueueLiveData.postValue(Resource.Error(it))
                })
        }
    }

}