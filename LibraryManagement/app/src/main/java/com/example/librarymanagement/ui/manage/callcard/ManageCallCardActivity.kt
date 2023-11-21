package com.example.librarymanagement.ui.manage.callcard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.librarymanagement.R
import com.example.librarymanagement.base.BaseActivity
import com.example.librarymanagement.base.Resource
import com.example.librarymanagement.databinding.ActivityManageCallCardBinding
import com.example.librarymanagement.models.CallCard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManageCallCardActivity : BaseActivity() {

    private val binding: ActivityManageCallCardBinding by lazy {
        ActivityManageCallCardBinding.inflate(layoutInflater)
    }
    private val viewModel: CallCardManageViewModel by viewModels()
    private val listCallCard = mutableListOf<CallCard>()

    private val adapter = CallCardAdapter(this,listCallCard) {
        start(DetailCallCardActivity::class.java, bundleOf("callCard" to it))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun initViews() {
        viewModel.getAllCallCards()
        binding.toolbar.title.text = "Manage Call Card"
        binding.toolbar.btnBack.setOnClickListener {
            onBackPressed()
        }
        binding.rcvCallCard.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.rcvCallCard.adapter = adapter

        binding.fabAddCallCard.setOnClickListener {
            start(DetailCallCardActivity::class.java)
        }
        initObserve()
    }

    fun initObserve() {
        viewModel.callCards.observe(this) {
            when(it) {
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    hideLoading()
                    listCallCard.clear()
                    listCallCard.addAll(it.data?.data!!)
                    adapter.notifyDataSetChanged()
                }
                is Resource.Error -> {
                   hideLoading()
                    showAlert(it.error.message)
                }
            }
        }
    }
}