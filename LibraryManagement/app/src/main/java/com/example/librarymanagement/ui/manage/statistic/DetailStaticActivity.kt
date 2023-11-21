package com.example.librarymanagement.ui.manage.statistic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.librarymanagement.R
import com.example.librarymanagement.base.BaseActivity
import com.example.librarymanagement.base.Resource
import com.example.librarymanagement.databinding.ActivityDetailStaticBinding
import com.example.librarymanagement.models.Statistic
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailStaticActivity : BaseActivity() {

    private val binding: ActivityDetailStaticBinding by lazy {
        ActivityDetailStaticBinding.inflate(layoutInflater)
    }
    private val listStatic = mutableListOf<Statistic>()
    private val viewModel: StatisticViewModel by viewModels()
    private val adapter by lazy {
        DetailStatisticAdapter(listStatic)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun initViews() {
        val id = intent.getSerializableExtra("id") as Int
        viewModel.getAllCallCardsByBookId(id.toLong())
        binding.apply {
            layoutHeader.title.text = getString(R.string.statistic)
            layoutHeader.btnBack.setOnClickListener {
                onBackPressed()
            }
            recyclerViewStatistic.layoutManager = LinearLayoutManager(this@DetailStaticActivity,LinearLayoutManager.VERTICAL,false)
            recyclerViewStatistic.setHasFixedSize(true)
            recyclerViewStatistic.adapter = adapter
        }
        initLiveData()
    }

    fun initLiveData(){
        viewModel.callCards.observe(this){

            when(it){
                is Resource.Loading->{
                    showLoading()
                }
                is Resource.Success->{
                    hideLoading()
                    listStatic.clear()
                    for (item in it.data?.data?: mutableListOf()){
                        listStatic.add(Statistic(item.user,item.borrowDate,item.dueDate,item.state,item.note))
                    }
                    adapter.updateData(listStatic)
                }

                is Resource.Error->{
                    hideLoading()
                    showAlert(it.error.message)
                }
            }
        }
    }
}