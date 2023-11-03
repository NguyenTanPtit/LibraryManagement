package com.example.librarymanagement.ui.books

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.net.toUri
import com.example.librarymanagement.base.BaseActivity
import com.example.librarymanagement.base.Resource
import com.example.librarymanagement.data.local.user.UserManager
import com.example.librarymanagement.databinding.ActivityBookDetailBinding
import com.example.librarymanagement.models.BookDetailResponse
import com.example.librarymanagement.ui.MainActivity
import com.example.librarymanagement.ui.books.viewmodel.BookDetailViewModel
import com.example.librarymanagement.ultils.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookDetailActivity : BaseActivity() {

    private val binding by lazy { ActivityBookDetailBinding.inflate(layoutInflater) }
    private val viewModel:BookDetailViewModel by viewModels ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
    private var book: BookDetailResponse? = null
    @SuppressLint("SetTextI18n")
    override fun initViews() {
        book = intent.getSerializableExtra("book") as BookDetailResponse?

        binding.apply {
            tvTitleBook.text = book?.title?:""
            tvAuthor.text = book?.authorName?:""
            bookCategory.text = book?.categoryName?:""
            bookDes.text = book?.description?:""
            bookPrice.text = "$ ${book?.price?:""}"
            bookState.text = book?.state?:""
            bookPage.text = book?.pageNumber.toString()
            img.loadImage((book?.cover?:"").toUri())

            if (UserManager.user?.role == "ADMIN") {
                llBtnEditDelete.visibility = View.VISIBLE
            } else {
                llBtnEditDelete.visibility = View.GONE
            }
        }
        initListeners()
        observerData()
    }

    fun initListeners() {
        binding.btnEdit.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("book", book)
            start(EditBookActivity::class.java, bundle)
        }

        binding.btnDelete.setOnClickListener {
            viewModel.deleteBook(book?.id?:"")
        }
    }
    fun observerData(){
        viewModel.deleteBookLiveData.observe(this){
            when(it){
                is Resource.Loading -> showLoading()
                is Resource.Success -> {
                    hideLoading()
                    showAlert(it.data?.message?:"")
                    start(MainActivity::class.java)
                }
                is Resource.Error -> {
                    hideLoading()
                    showAlert("Delete Failed")
                }
            }
        }
    }
}