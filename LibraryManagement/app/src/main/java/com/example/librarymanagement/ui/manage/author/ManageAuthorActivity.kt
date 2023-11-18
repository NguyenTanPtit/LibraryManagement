package com.example.librarymanagement.ui.manage.author

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.librarymanagement.R
import com.example.librarymanagement.base.BaseActivity
import com.example.librarymanagement.base.Resource
import com.example.librarymanagement.databinding.ActivityManageAuthorBinding
import com.example.librarymanagement.databinding.DialogAddCategoryBinding
import com.example.librarymanagement.models.Author
import com.example.librarymanagement.models.AuthorMain
import com.example.librarymanagement.ui.manage.author.adpater.AuthorAdapter
import com.example.librarymanagement.ultils.gone
import com.example.librarymanagement.ultils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManageAuthorActivity : BaseActivity() {

    private val binding: ActivityManageAuthorBinding by lazy {
        ActivityManageAuthorBinding.inflate(layoutInflater)
    }
    private val viewModel: AuthorManageViewModel by viewModels()
    private val listAuthor = mutableListOf<Author>()
    private val adapter: AuthorAdapter by lazy {
        AuthorAdapter(listAuthor,{
            //onDelete
            viewModel.deleteAuthor(it.id!!.toLong())
            viewModel.getAuthors()
        },{it1 ->
            val builder = AlertDialog.Builder(this)
            builder.setCancelable(true)
            val dialogBinding = DialogAddCategoryBinding.inflate(layoutInflater)
            builder.setView(dialogBinding.root)
            val dialog = builder.create()
            dialogBinding.tvTitle.text = "Update Author"
            dialogBinding.btnAdd.gone()
            dialogBinding.btnUpdate.setOnClickListener {
                val name = dialogBinding.edtContent.text.toString()
                if (name.isEmpty()) {
                    dialogBinding.edtContent.error = "Please enter author name"
                    return@setOnClickListener
                }
                viewModel.updateAuthor(AuthorMain((it1.id?:"0").toLong(),name))

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

    override fun initViews() {
        binding.manageAuthorToolbar.title.text = getString(R.string.manage_author)

        binding.manageAuthorToolbar.btnBack.setOnClickListener {
            onBackPressed()
        }
        viewModel.getAuthors()
        binding.manageAuthorRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.manageAuthorRecyclerView.adapter = adapter

        binding.addAuthorFab.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setCancelable(true)
            val dialogBinding = DialogAddCategoryBinding.inflate(layoutInflater)
            builder.setView(dialogBinding.root)
            dialogBinding.tvTitle.text = "Add Author"
            dialogBinding.btnUpdate.gone()
            dialogBinding.btnAdd.setOnClickListener {
                dialogBinding.edtContent.hint = "Author Name"
                val name = dialogBinding.edtContent.text.toString()
                if (name.isEmpty()) {
                    dialogBinding.edtContent.error = "Please enter author name"
                    return@setOnClickListener
                }
                viewModel.addAuthor(name)
            }
            val dialog = builder.create()
            dialog.window?.setBackgroundDrawableResource(R.drawable.bg_popup_dialog)
            dialog.show()
        }
        observeData()
    }

    private fun observeData(){
        viewModel.listAuthors.observe(this){ resource ->
            when(resource){
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
                                adapter.submitList(it.data)
                            }
                        }
                    }
                }
                is Resource.Error -> {
                    hideLoading()
                    showAlert("Error When Get Data!")
                }
            }
        }

        viewModel.addAuthor.observe(this){ resource ->
            when(resource){
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    hideLoading()
                    resource.data?.let {
                        if(it.message != null) {
                            showAlert(it.message)
                            viewModel.getAuthors()
                        }
                    }
                }
                is Resource.Error -> {
                    hideLoading()
                    showAlert("Error When Add Data!")
                }
            }
        }

        viewModel.deleteAuthor.observe(this){ resource ->
            when(resource){
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    hideLoading()
                    resource.data?.let {
                        if(it.message != null) {
                            showAlert(it.message)
                            viewModel.getAuthors()
                        }
                    }
                }
                is Resource.Error -> {
                    hideLoading()
                    showAlert("Error When Delete Data!")
                }
            }
        }

        viewModel.updateAuthor.observe(this){ resource ->
            when(resource){
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    hideLoading()
                    resource.data?.let {
                        if(it.message != null) {
                            showAlert(it.message)
                            viewModel.getAuthors()
                        }
                    }
                }
                is Resource.Error -> {
                    hideLoading()
                    showAlert("Error When Update Data!")
                }
            }
        }
    }
}