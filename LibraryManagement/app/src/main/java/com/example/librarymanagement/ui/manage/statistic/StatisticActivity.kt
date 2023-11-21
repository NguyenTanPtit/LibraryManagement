
package com.example.librarymanagement.ui.manage.statistic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.librarymanagement.R
import com.example.librarymanagement.base.BaseActivity
import com.example.librarymanagement.base.Resource
import com.example.librarymanagement.databinding.ActivityStatisticBinding
import com.example.librarymanagement.models.BookDetailResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatisticActivity : BaseActivity() {
    val binding: ActivityStatisticBinding by lazy {
        ActivityStatisticBinding.inflate(layoutInflater)
    }
    val viewModel: StatisticViewModel by viewModels()

    private val listAllBook = mutableListOf<BookDetailResponse>()

    private val adapter by lazy {
        StatisticAdapter(listAllBook){
            start(DetailStaticActivity::class.java, bundleOf("id" to it))
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun initViews() {
        viewModel.getBooks()
        binding.apply {
            layoutHeader.title.text = getString(R.string.statistic)
            layoutHeader.btnBack.setOnClickListener {
                onBackPressed()
            }
            recyclerViewBook.layoutManager = LinearLayoutManager(this@StatisticActivity,LinearLayoutManager.VERTICAL,false)
            recyclerViewBook.adapter = adapter
            recyclerViewBook.setHasFixedSize(true)
        }
        initLiveData()
    }

    fun initLiveData(){
        viewModel.listBooks.observe(this){
            when(it){
                is Resource.Loading->{
                    showLoading()
                }
                is Resource.Success->{
                    hideLoading()
                    listAllBook.clear()
                    listAllBook.addAll(it.data?.data?: mutableListOf())
                    adapter.updateData(listAllBook)
                }
                is Resource.Error->{
                    hideLoading()
                    showAlert(it.error.message)
                }
            }
        }
    }
}