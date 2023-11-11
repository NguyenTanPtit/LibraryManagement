package com.example.librarymanagement.ui.books

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.librarymanagement.R
import com.example.librarymanagement.base.BaseActivity
import com.example.librarymanagement.base.Resource
import com.example.librarymanagement.databinding.ActivityListBookAcitvityBinding
import com.example.librarymanagement.models.BookDetailResponse
import com.example.librarymanagement.ui.general.home.BookAdapter
import com.example.librarymanagement.ui.general.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListBookAcitvity : BaseActivity() {

    val binding: ActivityListBookAcitvityBinding by lazy {
        ActivityListBookAcitvityBinding.inflate(layoutInflater)
    }
    val viewModel:HomeViewModel by viewModels()
    var listBook:MutableList<BookDetailResponse> = mutableListOf()
    private lateinit var onItemBookClick: (Int) -> Unit
    private val bookAdapter: BookAdapter by lazy {
        BookAdapter(this, listBook,onItemBookClick)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun initViews() {
        onItemBookClick = {
            val intent = Intent(this, BookDetailActivity::class.java)
            intent.putExtra("book", listBook[it])
            startActivity(intent)
        }
        binding.rvBook.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = bookAdapter
            setHasFixedSize(true)
        }
        viewModel.getBooks()
        viewModel.listBooks.observe(this) { resource ->
            when(resource) {
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    hideLoading()
                    resource.data?.let {
                        if(it.message != null) {
                            showAlert(it.message)
                        }else{
                            if (it.data!=null) {
                                listBook.clear()
                                listBook = it.data.toMutableList()
                                bookAdapter.updateData(listBook)
                            }
                        }
                    }
                }
                is Resource.Error -> {
                    hideLoading()
                    showAlert("Error")
                }
            }
        }

        binding.appCompatButton.setOnClickListener {
            start(EditBookActivity::class.java)
        }
    }
}