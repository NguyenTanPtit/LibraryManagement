package com.example.librarymanagement.ui.general.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.librarymanagement.R
import com.example.librarymanagement.base.BaseFragment
import com.example.librarymanagement.base.Resource
import com.example.librarymanagement.data.local.user.UserManager
import com.example.librarymanagement.databinding.DialogAddCategoryBinding
import com.example.librarymanagement.databinding.FragmentHomeBinding
import com.example.librarymanagement.models.Category
import com.example.librarymanagement.ui.books.ListBookAcitvity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    private val binding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }
    private val viewModel:HomeViewModel by viewModels()
    var listCategories:MutableList<Category> = mutableListOf()
    private lateinit var onItemCategoryClick: (Int) -> Unit
    private val adapter:CategoriesAdapter by lazy {
        CategoriesAdapter(listCategories, onItemCategoryClick)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeData()
    }

    fun initViews() {
        if(UserManager.user?.role =="ADMIN"){
            binding.buttonAddCategory.visibility = View.VISIBLE
        }else{
            binding.buttonAddCategory.visibility = View.GONE
        }
        onItemCategoryClick = {
            val intent = Intent(requireContext(), ListBookAcitvity::class.java)
            intent.putExtra("categoryId", listCategories[it].id)
            startActivity(intent)
        }
        val linearManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvHomeCategory.layoutManager = linearManager
        binding.rvHomeCategory.adapter = adapter
        viewModel.getCategories()
        setOnClick()
    }

    fun observeData() {
        viewModel.listCategories.observe(viewLifecycleOwner) { resource ->
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
                                listCategories.clear()
                                listCategories = it.data.toMutableList()
                                adapter.submitList(listCategories)
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

        viewModel.addCategory.observe(viewLifecycleOwner) { resource ->
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
                                showAlert("Add Category Success")
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
    }

    @SuppressLint("SetTextI18n")
    private fun setOnClick(){
        binding.buttonAddCategory.setOnClickListener {
           val builder = AlertDialog.Builder(requireContext())
            builder.setCancelable(true)
            val dialogBinding = DialogAddCategoryBinding.inflate(layoutInflater)
            builder.setView(dialogBinding.root)
            dialogBinding.tvTitle.text = "Add Category2"
            dialogBinding.btnAdd.setOnClickListener {
                dialogBinding.edtContent.hint = "Category Name"
                val name = dialogBinding.edtContent.text.toString()
                if (name.isEmpty()) {
                    dialogBinding.edtContent.error = "Please enter category name"
                    return@setOnClickListener
                }
                viewModel.addCategory(name)
            }
            val dialog = builder.create()
            dialog.window?.setBackgroundDrawableResource(R.drawable.bg_popup_dialog)
            dialog.show()
        }
    }
}