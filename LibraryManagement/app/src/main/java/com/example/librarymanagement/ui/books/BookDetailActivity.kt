package com.example.librarymanagement.ui.books

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import com.example.librarymanagement.base.BaseActivity
import com.example.librarymanagement.base.Resource
import com.example.librarymanagement.data.local.user.UserManager
import com.example.librarymanagement.databinding.ActivityBookDetailBinding
import com.example.librarymanagement.models.BookDetailResponse
import com.example.librarymanagement.models.QueueDetail
import com.example.librarymanagement.ui.MainActivity
import com.example.librarymanagement.ui.books.viewmodel.BookDetailViewModel
import com.example.librarymanagement.ultils.DateUtils
import com.example.librarymanagement.ultils.loadImageBookCover
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookDetailActivity : BaseActivity() {

    private val binding by lazy { ActivityBookDetailBinding.inflate(layoutInflater) }
    private val viewModel:BookDetailViewModel by viewModels ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
    private var book: BookDetailResponse? = null
    private var listQueue = mutableListOf<QueueDetail>()
    private var checkCondition = true
    private var dateBorrow = ""
    private var dateDue = ""
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun initViews() {
        book = intent.getSerializableExtra("book") as BookDetailResponse?

        binding.apply {
            tvTitleBook.text = book?.title?:""
            tvAuthor.text = book?.authorName?:""
            bookCategory.text = book?.categoryName?:""
            bookDes.text = book?.description?:""
            bookPrice.text = "$ ${book?.price?:""}"
            bookState.text = book?.state?:""
            bookPage.text = book?.pageNumber.toString()
            img.loadImageBookCover((book?.cover?:"").toUri())

            if (UserManager.user?.role == "ADMIN") {
                llBtnEditDelete.visibility = View.VISIBLE
                llStudent.visibility = View.GONE
            } else {
                llBtnEditDelete.visibility = View.GONE
                llStudent.visibility = View.VISIBLE
                edtDateBorrow.setText(DateUtils.getCurrentDate())
                edtDateDue.setText(DateUtils.getCurrentDate())
            }
        }
        viewModel.getQueueByBookId(book?.id!!.toLong())
        initListeners()
        observerData()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun initListeners() {
        binding.btnEdit.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("book", book)
            start(EditBookActivity::class.java, bundle)
        }

        binding.btnDelete.setOnClickListener {
            viewModel.deleteBook(book?.id?:"")
        }

        binding.btnJoinQueue.setOnClickListener {
            dateBorrow = binding.edtDateBorrow.text.toString()
            dateDue = binding.edtDateDue.text.toString()
            if(DateUtils.checkValidDateBorrow(dateBorrow, listQueue[listQueue.size-1].dateDue?:DateUtils.getCurrentDate())) {
                joinQueue()
            } else {
                showAlert("Someone will return this book on ${listQueue[listQueue.size-1].dateDue}. You can borrow this book after that date.")
            }
        }

        binding.backBtn.setOnClickListener {
            onBackPressed()
        }
        binding.edtDateBorrow.focusable = View.NOT_FOCUSABLE
        binding.edtDateDue.focusable = View.NOT_FOCUSABLE
        binding.edtDateBorrow.setOnClickListener {
            hideKeyboard(it)
            showDatePickerDialog(binding.edtDateBorrow)
        }

        binding.edtDateDue.setOnClickListener {
            hideKeyboard(it)
            showDatePickerDialog(binding.edtDateDue)
        }


    }
    @SuppressLint("SetTextI18n")
    fun observerData(){
        viewModel.deleteBookLiveData.observe(this){
            when(it){
                is Resource.Loading -> showLoading()
                is Resource.Success -> {
                    hideLoading()
                    showAlert(it.data?.message?:"")
                    start(MainActivity::class.java)
                }
                is Resource.Error -> {
                    hideLoading()
                    showAlert("Delete Failed")
                }
            }
        }

        viewModel.queueLiveData.observe(this) {
            when (it) {
                is Resource.Loading -> showLoading()
                is Resource.Success -> {
                    hideLoading()
                    if(it.data?.data!=null){
                        if(!it.data.data.borrowQueueDetailDtos.isNullOrEmpty()) {
                            listQueue = it.data.data.borrowQueueDetailDtos.toMutableList()
                            listQueue.sortBy { it.position }
                            for (i in listQueue.indices) {
                                if (listQueue[i].userId == UserManager.user?.id) {
                                    binding.tvBookQueue.text = "You are in position ${listQueue[i].position} of the queue. "
                                    binding.tvBookBorrowDate.text = "You can borrow this book at ${listQueue[i].dateBorrow}."
                                    checkCondition = false
                                }
                            }
                            if (checkCondition) {
                                binding.tvBookQueue.text = "You are not in the queue. You can join the queue to borrow this book."
                                binding.tvBookBorrowDate.text = "You can join the queue from ${listQueue[listQueue.size-1].dateDue}."
                            }
                        }
                    }
                }
                is Resource.Error -> {
                    hideLoading()
                    showAlert("Join Queue Failed")
                }
            }
        }

        viewModel.fineLiveData.observe(this) {
            when (it) {
                is Resource.Loading -> showLoading()
                is Resource.Success -> {
                    hideLoading()
                    if(it.data?.data!=null){
                        if(it.data.data.missingBorrow!=null && it.data.data.missingBorrow >= 3){
                            checkCondition = false
                        }
                        if (it.data.data.lateReturn!=null && it.data.data.lateReturn >= 3){
                            checkCondition = false
                        }
                        if (it.data.data.damageBook!=null && it.data.data.damageBook >= 3){
                            checkCondition = false
                        }
                    }
                }
                is Resource.Error -> {
                    hideLoading()
                    showAlert("Get Fine Failed")
                }
            }

            viewModel.joinQueueLiveData.observe(this) {
                when (it) {
                    is Resource.Loading -> showLoading()
                    is Resource.Success -> {
                        hideLoading()
                        if(it.data?.message=="Join queue success!"){
                            viewModel.getQueueByBookId(book?.id!!.toLong())
                            showAlert("Join Queue Successfully. You must be present at the library on $dateBorrow")
                        }else{
                            showAlert(it.data?.message?:"")
                        }
                    }
                    is Resource.Error -> {
                        hideLoading()
                        showAlert("Join Queue Failed")
                    }
                }
            }
        }
    }

    private fun joinQueue() {
        viewModel.getFineByUserId(UserManager.user?.id!!.toLong())

            viewModel.getQueueByBookId(book?.id!!.toLong())
            if (checkCondition) {
                if(listQueue.isNotEmpty()) {
                    viewModel.joinQueue(
                        book?.id!!.toLong(),
                        UserManager.user?.id!!.toLong(),
                        dateBorrow,
                        dateDue,
                        listQueue[listQueue.size - 1].position?.plus(1) ?: 1
                    )
                } else {
                    viewModel.joinQueue(
                        book?.id!!.toLong(),
                        UserManager.user?.id!!.toLong(),
                        dateBorrow,
                        dateBorrow,
                        1
                    )
                }

            } else {
                for (i in listQueue.indices){
                    if(listQueue[i].userId == UserManager.user?.id){
                        showAlert("You are already in position ${listQueue[i].position} of the queue!")
                        return@joinQueue
                    }
                }
                showAlert("You are not allowed to borrow this book!")
            }

    }

    @SuppressLint("SetTextI18n")
    private fun showDatePickerDialog(editText: EditText)  {
        val datePicker = DatePickerDialog(this)
        datePicker.setOnDateSetListener { _, year, month, dayOfMonth ->
            val date = "$dayOfMonth/${month + 1}/$year"
            editText.setText(date)
        }
        datePicker.show()
    }
    private fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}