package com.example.librarymanagement.ui.manage.statistic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarymanagement.base.Resource
import com.example.librarymanagement.base.onResult
import com.example.librarymanagement.data.repository.CallCardRepository
import com.example.librarymanagement.data.repository.HomeRepository
import com.example.librarymanagement.models.ApiResponse
import com.example.librarymanagement.models.BookResponse
import com.example.librarymanagement.models.CallCard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatisticViewModel @Inject constructor(
    val homeRepository: HomeRepository,
    val callCardRepository: CallCardRepository
) : ViewModel() {
    private var _listBooks= MutableLiveData<Resource<BookResponse?>>()
    val listBooks: MutableLiveData<Resource<BookResponse?>>
        get() = _listBooks

    private val _callCards = MutableLiveData<Resource<ApiResponse<List<CallCard>>?>>()
    val callCards: MutableLiveData<Resource<ApiResponse<List<CallCard>>?>>
        get() = _callCards

    fun getBooks() {
        _listBooks.value = Resource.Loading()
        viewModelScope.launch {
            homeRepository.getBooks().onResult(
                {
                    _listBooks.value = Resource.Success(it)
                }, {
                    _listBooks.value = Resource.Error(it)
                }
            )
        }
    }

    fun getAllCallCardsByBookId(id :Long) {
        _callCards.value = Resource.Loading()
        viewModelScope.launch {
            callCardRepository.getCallCardByBookId(id).onResult(
                {
                    _callCards.value = Resource.Success(it)
                },
                {
                    _callCards.value = Resource.Error(it)
                }
            )
        }
    }
}