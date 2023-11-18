package com.example.librarymanagement.ui.manage.author

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarymanagement.base.Resource
import com.example.librarymanagement.base.onResult
import com.example.librarymanagement.data.repository.AuthorRepository
import com.example.librarymanagement.models.ApiResponse
import com.example.librarymanagement.models.Author
import com.example.librarymanagement.models.AuthorMain
import com.example.librarymanagement.models.AuthorResponse
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorManageViewModel @Inject constructor(val repo: AuthorRepository) :ViewModel() {

    private var _listAuthors = MutableLiveData<Resource<AuthorResponse?>>()
    val listAuthors: MutableLiveData<Resource<AuthorResponse?>>
        get() = _listAuthors

    private var _addAuthor = MutableLiveData<Resource<ApiResponse<Author>?>>()
    val addAuthor: MutableLiveData<Resource<ApiResponse<Author>?>>
        get() = _addAuthor

    private var _updateAuthor = MutableLiveData<Resource<ApiResponse<Author>?>>()
    val updateAuthor: MutableLiveData<Resource<ApiResponse<Author>?>>
        get() = _updateAuthor

    private var _deleteAuthor = MutableLiveData<Resource<ApiResponse<Author>?>>()
    val deleteAuthor: MutableLiveData<Resource<ApiResponse<Author>?>>
        get() = _deleteAuthor

    fun getAuthors() {
        viewModelScope.launch {
            _listAuthors.value = Resource.Loading()
            repo.getAll().onResult(
                {
                    _listAuthors.value = Resource.Success(it)
                }, {
                    _listAuthors.value = Resource.Error(it)
                }
            )
        }
    }

    fun addAuthor(author: String) {
        viewModelScope.launch {
            _addAuthor.value = Resource.Loading()
            repo.create(author).onResult(
                {
                    _addAuthor.value = Resource.Success(it)
                }, {
                    _addAuthor.value = Resource.Error(it)
                }
            )
        }
    }

    fun updateAuthor(author: AuthorMain) {
        viewModelScope.launch {
            _updateAuthor.value = Resource.Loading()
            repo.update(author).onResult(
                {
                    _updateAuthor.value = Resource.Success(it)
                }, {
                    _updateAuthor.value = Resource.Error(it)
                }
            )
        }
    }

    fun deleteAuthor(id: Long) {
        viewModelScope.launch {
            _deleteAuthor.value = Resource.Loading()
            repo.delete(id).onResult(
                {
                    _deleteAuthor.value = Resource.Success(it)
                }, {
                    _deleteAuthor.value = Resource.Error(it)
                }
            )
        }
    }

}