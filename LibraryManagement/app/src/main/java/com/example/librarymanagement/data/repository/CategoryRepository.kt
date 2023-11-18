package com.example.librarymanagement.data.repository

import com.example.librarymanagement.data.remote.book.service.CategoriesService
import com.example.librarymanagement.data.remote.dataSource.CategoriesDataSource
import com.example.librarymanagement.models.Category
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val categoriesService: CategoriesDataSource
)    {
    suspend fun getAll() = categoriesService.getCategories()
    suspend fun getBooksByCategory(categoryId: Long) = categoriesService.getBooksByCategory(categoryId)
    suspend fun addCategory(name: String) = categoriesService.addCategory(name)
    suspend fun updateCategory(category: Category) = categoriesService.updateCategory(category)
    suspend fun deleteCategory(categoryId: Long) = categoriesService.deleteCategory(categoryId)
}