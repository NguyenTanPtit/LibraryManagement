package com.example.librarymanagement.ui.manage.callcard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarymanagement.base.Resource
import com.example.librarymanagement.base.onResult
import com.example.librarymanagement.data.repository.BookDetailRepository
import com.example.librarymanagement.data.repository.CallCardRepository
import com.example.librarymanagement.data.repository.HomeRepository
import com.example.librarymanagement.data.repository.UserRepository
import com.example.librarymanagement.models.ApiResponse
import com.example.librarymanagement.models.BookResponse
import com.example.librarymanagement.models.CallCard
import com.example.librarymanagement.models.CallCardRequest
import com.example.librarymanagement.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CallCardManageViewModel @Inject constructor(
    private val callCardRepository: CallCardRepository,
    private val homeRepository: HomeRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _callCards = MutableLiveData<Resource<ApiResponse<List<CallCard>>?>>()
    val callCards: MutableLiveData<Resource<ApiResponse<List<CallCard>>?>>
        get() = _callCards

    private val _updateCallCard = MutableLiveData<Resource<ApiResponse<CallCard>?>>()
    val updateCallCard: MutableLiveData<Resource<ApiResponse<CallCard>?>> get() = _updateCallCard

    private val _createCallCard = MutableLiveData<Resource<ApiResponse<CallCard>?>>()
    val createCallCard: MutableLiveData<Resource<ApiResponse<CallCard>?>> get() = _createCallCard
    private var _listBooks= MutableLiveData<Resource<BookResponse?>>()
    val listBooks: MutableLiveData<Resource<BookResponse?>>
        get() = _listBooks

    private var _getAllStudent = MutableLiveData<Resource<List<User>?>>()

    val getAllStudent: MutableLiveData<Resource<List<User>?>>
        get() = _getAllStudent

    fun getAllCallCards() {
        _callCards.value = Resource.Loading()
        viewModelScope.launch {
            callCardRepository.getAllCallCards().onResult(
             {
                    _callCards.value = Resource.Success(it)
                },
                {
                    _callCards.value = Resource.Error(it)
                }
            )
        }
    }
    fun getBooks() {
        _listBooks.value = Resource.Loading()
        viewModelScope.launch {
            homeRepository.getBooks().onResult(
                {
                    _listBooks.value = Resource.Success(it)
                },
                {
                    _listBooks.value = Resource.Error(it)
                }
            )
        }
    }
    fun updateCallCard(callCard: CallCardRequest) {
        _updateCallCard.value = Resource.Loading()
        viewModelScope.launch {
            callCardRepository.updateCallCard(callCard).onResult(
                {
                    _updateCallCard.value = Resource.Success(it)
                },
                {
                    _updateCallCard.value = Resource.Error(it)
                }
            )
        }
    }

    fun createCallCard(callCard: CallCardRequest) {
        _createCallCard.value = Resource.Loading()
        viewModelScope.launch {
            callCardRepository.createCallCard(callCard).onResult(
                {
                    _createCallCard.value = Resource.Success(it)
                },
                {
                    _createCallCard.value = Resource.Error(it)
                }
            )
        }
    }

    fun getAllStudent(){
        _getAllStudent.value = Resource.Loading()
        viewModelScope.launch {
            userRepository.getAll().onResult(
                onSuccess = {
                    _getAllStudent.value = Resource.Success(it)
                },
                onError = {
                    _getAllStudent.value = Resource.Error(it)
                }
            )
        }
    }
}