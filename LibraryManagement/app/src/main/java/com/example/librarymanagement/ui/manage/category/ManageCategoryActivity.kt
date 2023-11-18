package com.example.librarymanagement.ui.manage.category

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.librarymanagement.R
import com.example.librarymanagement.base.BaseActivity
import com.example.librarymanagement.base.Resource
import com.example.librarymanagement.databinding.ActivityManageCategoryBinding
import com.example.librarymanagement.databinding.DialogAddCategoryBinding
import com.example.librarymanagement.models.AuthorMain
import com.example.librarymanagement.models.Category
import com.example.librarymanagement.ui.manage.category.adapter.ManageCategoryAdapter
import com.example.librarymanagement.ultils.gone
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManageCategoryActivity : BaseActivity() {

    private val binding: ActivityManageCategoryBinding by lazy {
        ActivityManageCategoryBinding.inflate(layoutInflater)
    }
    private val viewModel: ManageCategoryViewModel by viewModels()
    private val listCategory = mutableListOf<Category>()
    private val adapter: ManageCategoryAdapter by lazy {
        ManageCategoryAdapter(listCategory,{
            //onDelete
            viewModel.deleteCategory(it.id!!.toLong())
        },{it1 ->
            //onUpdate
            val builder = AlertDialog.Builder(this)
            builder.setCancelable(true)
            val dialogBinding = DialogAddCategoryBinding.inflate(layoutInflater)
            builder.setView(dialogBinding.root)
            val dialog = builder.create()
            dialogBinding.tvTitle.text = "Update Category"
            dialogBinding.btnAdd.gone()
            dialogBinding.btnUpdate.setOnClickListener {
                val name = dialogBinding.edtContent.text.toString()
                if (name.isEmpty()) {
                    dialogBinding.edtContent.error = "Please enter author name"
                    return@setOnClickListener
                }
                viewModel.updateCategory(Category(it1.id?:0,name))

                dialog.dismiss()
            }

            dialog.window?.setBackgroundDrawableResource(R.drawable.bg_popup_dialog)
            dialog.show()
        })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    @SuppressLint("SetTextI18n")
    override fun initViews() {
        binding.addAuthorFab.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setCancelable(true)
            val dialogBinding = DialogAddCategoryBinding.inflate(layoutInflater)
            builder.setView(dialogBinding.root)
            val dialog = builder.create()
            dialogBinding.btnUpdate.gone()
            dialogBinding.btnAdd.setOnClickListener {
                dialogBinding.edtContent.hint = "Category name"
                val name = dialogBinding.edtContent.text.toString()
                if (name.isEmpty()) {
                    dialogBinding.edtContent.error = "Please enter category name"
                    return@setOnClickListener
                }
                viewModel.addCategory(name)

                dialog.dismiss()
            }

            dialog.window?.setBackgroundDrawableResource(R.drawable.bg_popup_dialog)
            dialog.show()
        }
        binding.manageAuthorToolbar.btnBack.setOnClickListener {
            onBackPressed()
        }
        binding.manageAuthorToolbar.title.text = "Manage Category"
        viewModel.getAll()
        initRecyclerView()
        observeData()
    }

    private fun initRecyclerView() {
        binding.manageCategoryRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.manageCategoryRecyclerView.setHasFixedSize(true)
        binding.manageCategoryRecyclerView.adapter = adapter
    }

    private fun observeData() {
        viewModel.categories.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    hideLoading()
                    listCategory.clear()
                    listCategory.addAll(it.data?.data?: listOf())
                    adapter.notifyDataSetChanged()
                }
                is Resource.Error -> {
                    hideLoading()
                    showError(it.error.message)
                }
            }
        }
        viewModel.addCategory.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    hideLoading()
                    showAlert("Add category successfully")
                    viewModel.getAll()
                }
                is Resource.Error -> {
                    hideLoading()
                    showError(it.error.message)
                }
            }
        }

        viewModel.updateCategory.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    hideLoading()
                    showAlert("Update category successfully")
                    viewModel.getAll()
                }
                is Resource.Error -> {
                    hideLoading()
                    showError(it.error.message)
                }
            }
        }

        viewModel.deleteCategory.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    hideLoading()
                    showAlert("Delete category successfully")
                    viewModel.getAll()
                }
                is Resource.Error -> {
                    hideLoading()
                    showError(it.error.message)
                }
            }
        }
    }
}