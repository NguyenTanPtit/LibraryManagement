package com.example.librarymanagement.data.remote.dataSource

import com.example.librarymanagement.base.BaseDataSource
import com.example.librarymanagement.data.remote.book.service.CategoriesService
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
}