package com.example.librarymanagement.ui.manage.callcard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarymanagement.base.Resource
import com.example.librarymanagement.base.onResult
import com.example.librarymanagement.data.repository.CallCardRepository
import com.example.librarymanagement.models.ApiResponse
import com.example.librarymanagement.models.CallCard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CallCardManageViewModel @Inject constructor(
    private val callCardRepository: CallCardRepository
) : ViewModel() {

    private val _callCards = MutableLiveData<Resource<ApiResponse<List<CallCard>>?>>()
    val callCards: MutableLiveData<Resource<ApiResponse<List<CallCard>>?>>
        get() = _callCards

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
}