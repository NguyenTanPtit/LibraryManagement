package com.example.librarymanagement.ui.general.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarymanagement.base.Resource
import com.example.librarymanagement.base.onResult
import com.example.librarymanagement.data.repository.HomeRepository
import com.example.librarymanagement.models.CategoriesResponse
import com.example.librarymanagement.models.GetBookResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val repo: HomeRepository):ViewModel(){

    private var _listCategories= MutableLiveData<Resource<CategoriesResponse?>>()
    val listCategories: MutableLiveData<Resource<CategoriesResponse?>>
        get() = _listCategories

    private var _listBooksByCategory= MutableLiveData<Resource<GetBookResponse?>>()
    val listBooksByCategory: MutableLiveData<Resource<GetBookResponse?>>
        get() = _listBooksByCategory

    private var _addCategory= MutableLiveData<Resource<CategoriesResponse?>>()
    val addCategory: MutableLiveData<Resource<CategoriesResponse?>>
        get() = _addCategory
    fun getCategories() {
        _listCategories.value = Resource.Loading()
        viewModelScope.launch {
            repo.getCategories().onResult(
                {
                    _listCategories.value = Resource.Success(it)
                },{
                    _listCategories.value = Resource.Error(it)
                }
            )
        }

    }

    fun getBooksByCategory(categoryId: Long) {
        _listBooksByCategory.value = Resource.Loading()
        viewModelScope.launch {
            repo.getBooksByCategory(categoryId).onResult(
                {
                    _listBooksByCategory.value = Resource.Success(it)
                },{
                    _listBooksByCategory.value = Resource.Error(it)
                }
            )
        }

    }

    fun addCategory(name: String) {
        _addCategory.value = Resource.Loading()
        viewModelScope.launch {
            repo.addCategory(name).onResult(
                {
                    _addCategory.value = Resource.Success(it)
                },{
                    _addCategory.value = Resource.Error(it)
                }
            )
        }

    }
}