package com.example.librarymanagement.ui.books

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.core.net.toUri
import com.example.librarymanagement.R
import com.example.librarymanagement.base.BaseActivity
import com.example.librarymanagement.base.Resource
import com.example.librarymanagement.base.successData
import com.example.librarymanagement.data.local.user.UserManager.user
import com.example.librarymanagement.databinding.ActivityEditBookBinding
import com.example.librarymanagement.models.Author
import com.example.librarymanagement.models.Book
import com.example.librarymanagement.models.BookDetailResponse
import com.example.librarymanagement.models.Category
import com.example.librarymanagement.ui.general.home.HomeViewModel
import com.example.librarymanagement.ultils.gone
import com.example.librarymanagement.ultils.loadImageBookCover
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditBookActivity : BaseActivity() {

    private val binding by lazy {
        ActivityEditBookBinding.inflate(layoutInflater)
    }
    private val catList = mutableListOf("Category")
    private val authorList = mutableListOf("Author")
    private val stateList = mutableListOf("Available", "Borrowed")
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var bookUpdate: Book
    private var book: BookDetailResponse? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initDataObserve()
    }

    override fun initViews() {
        val bundle = intent.extras
        homeViewModel.getCategories()
        homeViewModel.getAuthors()
        binding.spinnerAddBook.adapter = ArrayAdapter(this@EditBookActivity, R.layout.item_spinner, catList)
        binding.spinnerState.adapter = ArrayAdapter(this@EditBookActivity, R.layout.item_spinner, stateList)
        binding.spinnerAuthor.adapter = ArrayAdapter(this@EditBookActivity, R.layout.item_spinner, authorList)
        binding.layoutHeader.btnBack.setOnClickListener {
            onBackPressed()
        }

        if(bundle!=null){
          book = (bundle.getSerializable("book") as? BookDetailResponse)
            if(book != null) {
                val author = Author(book!!.authorId.toString(), book!!.authorName?:"")
                val category = Category(book!!.categoryId?:0, book!!.categoryName?:"")
                bookUpdate = Book(
                    book!!.id?:"", book!!.title?:"", book!!.cover?:"",
                    book!!.state?:"", book!!.description?:"", author, category, book!!.price?:"", book!!.pageNumber?:"")
                binding.layoutHeader.title.text = "Edit Book"
                binding.apply {
                    addBookBtn.gone()
                    addBookImg.loadImageBookCover((book!!.cover?:"").toUri())
                    textInputAddBookName.setText(book!!.title)
                    spinnerAuthor.setSelection(authorList.indexOf(book!!.authorName))
                    textInputAddBookDes.setText(book!!.description)
                    textInputAddBookPrice.setText(book!!.price)
                    textInputAddBookPageNumber.setText(book!!.pageNumber)
                    spinnerAddBook.setSelection(catList.indexOf(book!!.categoryName))
                    spinnerState.setSelection(stateList.indexOf(book!!.state))
                }
            }

        }
        else{
            bookUpdate = Book()
            binding.layoutHeader.title.text = "Add Book"
            binding.updateBookBtn.gone()
        }

        binding.addBookImg.setOnClickListener{
            pickImage()
        }

        binding.updateBookBtn.setOnClickListener {
            if(validateUpdate()){
                getValueUpdateBook()
                homeViewModel.updateBook(bookUpdate)
            }

        }

        binding.addBookBtn.setOnClickListener {
            if(validateUpdate()){
                getValueUpdateBook()
                homeViewModel.addBook(bookUpdate)
            }
        }
    }

    private fun initDataObserve(){
        homeViewModel.listCategories.observe(this){
            when(it){
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    hideLoading()
                    it.data?.data?.forEach { category ->
                        catList.add(category.name?:"")
                    }
                    if(book != null)
                        binding.spinnerAddBook.setSelection(catList.indexOf(book!!.categoryName))
                }
                is Resource.Error -> {
                    hideLoading()
                    showAlert(it.error.message)
                }
            }
        }

        homeViewModel.listAuthors.observe(this){
            when(it){
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    hideLoading()
                    it.data?.data?.forEach { author ->
                        authorList.add(author.name?:"")

                    }
                    if (book != null)
                        binding.spinnerAuthor.setSelection(authorList.indexOf(book?.authorName))
                }
                is Resource.Error -> {
                    hideLoading()
                    showAlert(it.error.message)
                }
            }
        }
        homeViewModel.updateBook.observe(this){
            when(it){
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    hideLoading()
                    showAlert(it.data?.message)
                    onBackPressed()
                }
                is Resource.Error -> {
                    hideLoading()
                    showAlert(it.error.message)
                }
            }
        }

        homeViewModel.addBook.observe(this){
            when(it){
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    hideLoading()
                    showAlert(it.data?.message)
                    onBackPressed()
                }
                is Resource.Error -> {
                    hideLoading()
                    showAlert(it.error.message)
                }
            }
        }
    }

    private fun pickImage(){
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        showLoading()
        if(requestCode == 1 && resultCode == RESULT_OK && data != null){
            hideLoading()
            val imageUri = data.data
            val randomKey = (0..100000).random()
            val reference: StorageReference = FirebaseStorage.getInstance().reference
            if(imageUri != null) {
                val taskUpLoad = reference.child("book_cover")
                    .child(randomKey.toString()).putFile(imageUri)
                taskUpLoad.addOnSuccessListener {
                    taskUpLoad.continueWithTask { task ->
                        if (!task.isSuccessful) {
                            task.exception?.let {
                                throw it
                            }
                        }
                        reference.child("book_cover").child(randomKey.toString()).downloadUrl
                    }.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val downloadUri = task.result
                            if (downloadUri != null) {
                                bookUpdate.cover = downloadUri.toString()
                            }
                        } else {
                            showAlert("Error when load image")
                        }
                    }
                }
                binding.addBookImg.loadImageBookCover(imageUri)
                bookUpdate.cover = imageUri.toString()
            }
            else
                showAlert("Error when load image")
        }

    }

    fun getValueUpdateBook(){
        binding.apply {
            bookUpdate.title = textInputAddBookName.text.toString()
            bookUpdate.description = textInputAddBookDes.text.toString()
            bookUpdate.price = textInputAddBookPrice.text.toString()
            bookUpdate.pageNumber = textInputAddBookPageNumber.text.toString()
            bookUpdate.state = spinnerState.selectedItem.toString()
            val category = Category(homeViewModel.listCategories.value?.successData?.data?.get(spinnerAddBook.selectedItemPosition-1)?.id ?:0,
                homeViewModel.listCategories.value?.successData?.data?.get(spinnerAddBook.selectedItemPosition-1)?.name?:"")
            val author = Author(homeViewModel.listAuthors.value?.successData?.data?.get(spinnerAuthor.selectedItemPosition-1)?.id?:"",
                homeViewModel.listAuthors.value?.successData?.data?.get(spinnerAuthor.selectedItemPosition-1)?.name?:"")
            bookUpdate.category = category
            bookUpdate.author = author
            bookUpdate.state = spinnerState.selectedItem.toString()
        }
    }

    fun validateUpdate():Boolean{
        var check = true
        binding.apply {
            if(textInputAddBookName.text.toString().isEmpty()){
                edtAddBookName.error = "Please enter book name"
                check = false
            }
            if(textInputAddBookDes.text.toString().isEmpty()){
                edtAddBookDes.error = "Please enter book description"
                check = false
            }
            if(textInputAddBookPrice.text.toString().isEmpty()){
                edtAddBookPrice.error = "Please enter book price"
                check = false
            }
            if(textInputAddBookPageNumber.text.toString().isEmpty()){
                edtAddBookPageNumber.error = "Please enter book page number"
                check = false
            }
            if(spinnerAddBook.selectedItemPosition == 0){
                showAlert("Please choose category")
                check = false
            }
            if(spinnerAuthor.selectedItemPosition == 0){
                showAlert("Please choose author")
                check = false
            }
            if(spinnerState.selectedItemPosition == 0){
                showAlert("Please choose state")
                check = false
            }
        }
        return check
    }
}