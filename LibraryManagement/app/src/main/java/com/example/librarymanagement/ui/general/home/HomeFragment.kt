package com.example.librarymanagement.ui.general.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.librarymanagement.R
import com.example.librarymanagement.base.BaseFragment
import com.example.librarymanagement.base.Resource
import com.example.librarymanagement.data.local.user.UserManager
import com.example.librarymanagement.databinding.DialogAddCategoryBinding
import com.example.librarymanagement.databinding.FragmentHomeBinding
import com.example.librarymanagement.models.Author
import com.example.librarymanagement.models.BookDetailResponse
import com.example.librarymanagement.models.Category
import com.example.librarymanagement.ui.books.BookDetailActivity
import com.example.librarymanagement.ui.books.ListBookAcitvity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    private val binding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }
    private val viewModel:HomeViewModel by viewModels()
    var listCategories:MutableList<Category> = mutableListOf()
    var listAthor:MutableList<Author> = mutableListOf()
    var listBook:MutableList<BookDetailResponse> = mutableListOf()
    private lateinit var onItemCategoryClick: (Int) -> Unit
    private lateinit var onItemAuthorClick: (Int) -> Unit
    private lateinit var onItemBookClick: (Int) -> Unit
    private val authorAdapter:AuthorAdapter by lazy {
        AuthorAdapter(listAthor, onItemAuthorClick)
    }
    private val catAdapter:CategoriesAdapter by lazy {
        CategoriesAdapter(listCategories, onItemCategoryClick)
    }
    private val bookAdapter:BookAdapter by lazy {
        BookAdapter(requireContext(), listBook,onItemBookClick)
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

        onItemCategoryClick = {
            val intent = Intent(requireContext(), ListBookAcitvity::class.java)
            intent.putExtra("categoryId", listCategories[it].id)
            startActivity(intent)
        }
        onItemAuthorClick = {
            val intent = Intent(requireContext(), ListBookAcitvity::class.java)
            intent.putExtra("authorId", listAthor[it].id)
            startActivity(intent)
        }
        onItemBookClick = {
            val intent = Intent(requireContext(), BookDetailActivity::class.java)
            intent.putExtra("book", listBook[it])
            startActivity(intent)
        }
        val linearManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvHomeCategory.apply {
            layoutManager = linearManager
            adapter = catAdapter
            setHasFixedSize(true)
        }
        binding.rvHomeAuthor.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = authorAdapter
            setHasFixedSize(true)
        }
        binding.rvHomeBook.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = bookAdapter
            setHasFixedSize(true)
        }
        viewModel.getCategories()
        viewModel.getAuthors()
        viewModel.getBooks()
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
                                catAdapter.submitList(listCategories)
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

        viewModel.listBooks.observe(viewLifecycleOwner) { resource ->
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
        viewModel.listAuthors.observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    hideLoading()
                    it.data?.let {
                        if(it.message != null) {
                            showAlert(it.message)
                        }else{
                            if (it.data!=null) {
                                listAthor.clear()
                                listAthor = it.data.toMutableList()
                                authorAdapter.submitList(listAthor)
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
//        binding.buttonAddCategory.setOnClickListener {
//           val builder = AlertDialog.Builder(requireContext())
//            builder.setCancelable(true)
//            val dialogBinding = DialogAddCategoryBinding.inflate(layoutInflater)
//            builder.setView(dialogBinding.root)
//            dialogBinding.tvTitle.text = "Add Category2"
//            dialogBinding.btnAdd.setOnClickListener {
//                dialogBinding.edtContent.hint = "Category Name"
//                val name = dialogBinding.edtContent.text.toString()
//                if (name.isEmpty()) {
//                    dialogBinding.edtContent.error = "Please enter category name"
//                    return@setOnClickListener
//                }
//                viewModel.addCategory(name)
//            }
//            val dialog = builder.create()
//            dialog.window?.setBackgroundDrawableResource(R.drawable.bg_popup_dialog)
//            dialog.show()
//        }
    }
}