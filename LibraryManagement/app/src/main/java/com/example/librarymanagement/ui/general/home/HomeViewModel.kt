package com.example.librarymanagement.ui.general.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarymanagement.base.Resource
import com.example.librarymanagement.base.onResult
import com.example.librarymanagement.data.repository.HomeRepository
import com.example.librarymanagement.models.AuthorResponse
import com.example.librarymanagement.models.Book
import com.example.librarymanagement.models.BookResponse
import com.example.librarymanagement.models.CategoriesResponse
import com.example.librarymanagement.models.GetBookResponse
import com.example.librarymanagement.models.UpdateBookResponse
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

    private var _listAuthors= MutableLiveData<Resource<AuthorResponse?>>()
    val listAuthors: MutableLiveData<Resource<AuthorResponse?>>
        get() = _listAuthors

    private var _listBooks= MutableLiveData<Resource<BookResponse?>>()
    val listBooks: MutableLiveData<Resource<BookResponse?>>
        get() = _listBooks

    private var _updateBook= MutableLiveData<Resource<UpdateBookResponse?>>()
    val updateBook: MutableLiveData<Resource<UpdateBookResponse?>>
        get() = _updateBook

    private var _addBook= MutableLiveData<Resource<UpdateBookResponse?>>()
    val addBook: MutableLiveData<Resource<UpdateBookResponse?>>
        get() = _addBook
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



    fun getAuthors() {
        _listAuthors.value = Resource.Loading()
        viewModelScope.launch {
            repo.getAuthors().onResult(
                {
                    _listAuthors.value = Resource.Success(it)
                },{
                    _listAuthors.value = Resource.Error(it)
                }
            )
        }

    }

    fun getBooks() {
        _listBooks.value = Resource.Loading()
        viewModelScope.launch {
            repo.getBooks().onResult(
                {
                    _listBooks.value = Resource.Success(it)
                },{
                    _listBooks.value = Resource.Error(it)
                }
            )
        }

    }

    fun updateBook(book:Book){
        _updateBook.value = Resource.Loading()
        viewModelScope.launch {
            repo.updateBook(book).onResult(
                {
                    _updateBook.value = Resource.Success(it)
                },{
                    _updateBook.value = Resource.Error(it)
                }
            )
        }
    }

    fun addBook(book:Book){
        _addBook.value = Resource.Loading()
        viewModelScope.launch {
            repo.addBook(book).onResult(
                {
                    _addBook.value = Resource.Success(it)
                },{
                    _addBook.value = Resource.Error(it)
                }
            )
        }
    }

}