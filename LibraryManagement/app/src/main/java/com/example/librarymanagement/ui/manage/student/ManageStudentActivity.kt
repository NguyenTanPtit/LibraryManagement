package com.example.librarymanagement.ui.manage.student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.librarymanagement.R
import com.example.librarymanagement.base.BaseActivity
import com.example.librarymanagement.base.Resource
import com.example.librarymanagement.databinding.ActivityManageStudentBinding
import com.example.librarymanagement.models.User
import com.example.librarymanagement.ui.manage.student.adapter.ManageStudentAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManageStudentActivity : BaseActivity() {

    private val binding: ActivityManageStudentBinding by lazy {
        ActivityManageStudentBinding.inflate(layoutInflater)
    }
    private val viewModel: ManageStudentViewModel by viewModels()

    private val listStudent = mutableListOf<User>()
    private val adapter: ManageStudentAdapter by lazy {
        ManageStudentAdapter(listStudent,{
            //onDelete
            viewModel.deleteStudent(it.id!!.toLong())
        },{it1 ->
            start(DetailStudentActivity::class.java, bundleOf("student" to it1))
        })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun initViews() {

        binding.toolbar.title.text = "Manage Student"
        binding.toolbar.btnBack.setOnClickListener {
            onBackPressed()
        }
        viewModel.getAllStudent()
        binding.rvStudent.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvStudent.adapter = adapter
        binding.fabAddStudent.setOnClickListener {
            start(DetailStudentActivity::class.java)
        }
        initObservers()
    }

    fun initObservers(){
        viewModel.getAllStudent.observe(this){
            when(it){
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    hideLoading()
                    if(it.data.isNullOrEmpty()) {
                        showAlert("No data found")
                    }else {
                        listStudent.clear()
                        listStudent.addAll(it.data)
                        adapter.notifyDataSetChanged()
                    }
                }
                is Resource.Error -> {
                    hideLoading()
                    showAlert(it.error.message)
                }
            }
        }

        viewModel.deleteStudent.observe(this){
            when(it){
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    hideLoading()
                    showAlert(it.data!!.message)
                    viewModel.getAllStudent()
                }
                is Resource.Error -> {
                    hideLoading()
                    showAlert(it.error.message)
                }
            }
        }

    }
}