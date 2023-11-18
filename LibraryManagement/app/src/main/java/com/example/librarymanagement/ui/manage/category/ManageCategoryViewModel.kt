package com.example.librarymanagement.ui.manage.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarymanagement.base.Resource
import com.example.librarymanagement.base.onResult
import com.example.librarymanagement.data.repository.CategoryRepository
import com.example.librarymanagement.models.ApiResponse
import com.example.librarymanagement.models.CategoriesResponse
import com.example.librarymanagement.models.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManageCategoryViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private var _categories = MutableLiveData<Resource<CategoriesResponse?>>()

    val categories: MutableLiveData<Resource<CategoriesResponse?>>
        get() = _categories

    private var _addCategory = MutableLiveData<Resource<ApiResponse<Category>?>>()

    val addCategory: MutableLiveData<Resource<ApiResponse<Category>?>>
        get() = _addCategory

    private var _updateCategory = MutableLiveData<Resource<ApiResponse<Category>?>>()

    val updateCategory: MutableLiveData<Resource<ApiResponse<Category>?>>
        get() = _updateCategory

    private var _deleteCategory = MutableLiveData<Resource<ApiResponse<Category>?>>()

    val deleteCategory: MutableLiveData<Resource<ApiResponse<Category>?>>
        get() = _deleteCategory

    fun getAll() {
        viewModelScope.launch {
            _categories.value = Resource.Loading()
            categoryRepository.getAll().onResult(
                {
                    _categories.value = Resource.Success(it)
                },
                {
                    _categories.value = Resource.Error(it)
                }
            )
        }
    }

    fun addCategory(name: String) {
        viewModelScope.launch {
            _addCategory.value = Resource.Loading()
            categoryRepository.addCategory(name).onResult(
                {
                    _addCategory.value = Resource.Success(it)
                },
                {
                    _addCategory.value = Resource.Error(it)
                }
            )
        }
    }

    fun updateCategory(category: Category) {
        viewModelScope.launch {
            _updateCategory.value = Resource.Loading()
            categoryRepository.updateCategory(category).onResult(
                {
                    _updateCategory.value = Resource.Success(it)
                },
                {
                    _updateCategory.value = Resource.Error(it)
                }
            )
        }
    }

    fun deleteCategory(categoryId: Long) {
        viewModelScope.launch {
            _deleteCategory.value = Resource.Loading()
            categoryRepository.deleteCategory(categoryId).onResult(
                {
                    _deleteCategory.value = Resource.Success(it)
                },
                {
                    _deleteCategory.value = Resource.Error(it)
                }
            )
        }
    }
}