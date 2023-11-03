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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BookDetailViewModel @Inject constructor(val repo: BookDetailRepository): ViewModel(){

    private var _deleteBookLiveData = MutableLiveData<Resource<ApiResponse<BookDetailResponse>?>>()
    val deleteBookLiveData get() = _deleteBookLiveData
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

}