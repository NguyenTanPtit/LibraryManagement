package com.example.librarymanagement.data.remote.dataSource

import com.example.librarymanagement.base.BaseDataSource
import com.example.librarymanagement.data.remote.book.service.CategoriesService
import com.example.librarymanagement.models.Category
import javax.inject.Inject

class CategoriesDataSource @Inject constructor(val service: CategoriesService): BaseDataSource(){

    suspend fun getCategories() = getResultWithResponse {
        service.getAll()
    }

    suspend fun getBooksByCategory(categoryId: Long) = getResultWithResponse {
        service.getBooksByCategory(categoryId)
    }

    suspend fun addCategory(name: String) = getResultWithResponse {
        service.addCategory(name)
    }

    suspend fun updateCategory(category: Category) = getResultWithResponse {
        service.updateCategory(category)
    }

    suspend fun deleteCategory(categoryId: Long) = getResultWithResponse {
        service.deleteCategory(categoryId)
    }
}