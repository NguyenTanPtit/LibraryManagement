package com.example.librarymanagement.base

interface IBaseView {
    fun showLoading(text: String? = null, isDialog: Boolean? = false)
    fun hideLoading()

    fun showError(message: String?, isToast: Boolean = true)

    fun showAlert(message: String?) {
        showError(message, false)
    }

    fun onRefresh()
    fun onLoadMore()
}