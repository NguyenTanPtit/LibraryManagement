package com.example.librarymanagement.ui.general.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarymanagement.base.Resource
import com.example.librarymanagement.base.onResult
import com.example.librarymanagement.data.repository.CallCardRepository
import com.example.librarymanagement.models.ApiResponse
import com.example.librarymanagement.models.CallCard
import com.google.firebase.database.core.view.View
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(val callCardRepository: CallCardRepository) : ViewModel() {

    private val _callCards = MutableLiveData<Resource<ApiResponse<List<CallCard>>?>>()

    val callCards: MutableLiveData<Resource<ApiResponse<List<CallCard>>?>>
        get() = _callCards

    fun getAllCallCards(id :Long) {
        _callCards.value = Resource.Loading()
        viewModelScope.launch {
            callCardRepository.getCallCardById(id).onResult(
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