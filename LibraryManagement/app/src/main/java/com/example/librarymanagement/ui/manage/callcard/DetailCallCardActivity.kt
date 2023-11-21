package com.example.librarymanagement.ui.manage.callcard

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.librarymanagement.R
import com.example.librarymanagement.base.BaseActivity
import com.example.librarymanagement.base.Resource
import com.example.librarymanagement.databinding.ActivityDetailCallCardBinding
import com.example.librarymanagement.databinding.DialogAddBookCallCardBinding
import com.example.librarymanagement.models.Book
import com.example.librarymanagement.models.BookDetailResponse
import com.example.librarymanagement.models.CallCard
import com.example.librarymanagement.models.CallCardRequest
import com.example.librarymanagement.models.User
import com.example.librarymanagement.ui.general.home.BookAdapter
import com.example.librarymanagement.ultils.gone
import com.example.librarymanagement.ultils.toEditable
import com.example.librarymanagement.ultils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailCallCardActivity : BaseActivity() {

    private val binding: ActivityDetailCallCardBinding by lazy {
        ActivityDetailCallCardBinding.inflate(layoutInflater)
    }
    private val stateList = mutableListOf("Borrowed", "Returned")
    private var listBook = mutableListOf<BookDetailResponse>()
    private val bookAdapter by lazy {
        BookAdapter(this,listBook){}
    }
    private val listAllBook = mutableListOf<BookDetailResponse>()
    private val bookAdapter2 by lazy {
        AddBookAdapter(listAllBook)
    }
    private val listStudent = mutableListOf<User>()
    private val callCardUpdate: CallCardRequest = CallCardRequest()
    private var callCard: CallCard? = null
    private val viewModel: CallCardManageViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initViews() {
        callCard = intent.getSerializableExtra("callCard") as CallCard?
        binding.toolbar.btnBack.setOnClickListener {
            onBackPressed()
        }
        if(callCard!=null) {
            binding.updateCallcardBtn.visible()
            binding.addCallcardBtn.gone()
            binding.toolbar.title.text = "Detail Call Card"
            binding.llUser.gone()
        }else {
            binding.updateCallcardBtn.gone()
            binding.addCallcardBtn.visible()
            binding.toolbar.title.text = "Add Call Card"
            binding.llUser.visible()
            viewModel.getAllStudent()
        }
        binding.apply {
            tvNameStudent.text = callCard?.user?.fullName?:""
            tvUserEmail.text = callCard?.user?.email?:""
            edtNote.text = callCard?.note?.toEditable()?:"".toEditable()
            edtDateBorrow.text = callCard?.borrowDate?.toEditable()?:"".toEditable()
            edtDateDue.text = callCard?.dueDate?.toEditable()?:"".toEditable()
            edtDateBorrow.focusable = View.NOT_FOCUSABLE
            edtDateDue.focusable = View.NOT_FOCUSABLE
            spinnerState.adapter = ArrayAdapter(this@DetailCallCardActivity, R.layout.item_spinner, stateList)
            spinnerState.setSelection(stateList.indexOf(callCard?.state))
            listBook = callCard?.books?.toMutableList() ?: mutableListOf()
            rvListBook.layoutManager = LinearLayoutManager(this@DetailCallCardActivity, LinearLayoutManager.HORIZONTAL, false)
            rvListBook.adapter = bookAdapter

            spinnerUser.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    tvNameStudent.text = listStudent[position].fullName
                    tvUserEmail.text = listStudent[position].email
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }
        binding.fabAddBook.setOnClickListener {
            viewModel.getBooks()
            val builder = AlertDialog.Builder(this)
            builder.setCancelable(true)
            val dialogBinding = DialogAddBookCallCardBinding.inflate(layoutInflater)
            builder.setView(dialogBinding.root)
            val dialog = builder.create()
            dialogBinding.apply {
                dialogAddBookCallCardRec.layoutManager = LinearLayoutManager(this@DetailCallCardActivity, LinearLayoutManager.VERTICAL, false)
                dialogAddBookCallCardRec.adapter = bookAdapter2
                dialogAddBookCallCardRec.setHasFixedSize(true)
                btnAdd.setOnClickListener {
                    Log.d("list book 2", listBook.toString())
                    for (book in bookAdapter2.getSelectedBooks()) {
                        listBook.add(book)
                    }
                    bookAdapter.updateData(listBook)
                    dialog.dismiss()
                }
            }
            dialog.show()
        }

        binding.edtDateBorrow.setOnClickListener {
            hideKeyboard(it)
            showDatePickerDialog(binding.edtDateBorrow)
        }

        binding.edtDateDue.setOnClickListener {
            hideKeyboard(it)
            showDatePickerDialog(binding.edtDateDue)
        }

        binding.addCallcardBtn.setOnClickListener {
            if (checkValidate()) {
                viewModel.createCallCard(getCallCard())
            }
        }

        binding.updateCallcardBtn.setOnClickListener {
            if (checkValidate()) {
                viewModel.updateCallCard(getCallCard())
            }
        }
        initLiveDataObserve()
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

    private fun initLiveDataObserve(){
        viewModel.updateCallCard.observe(this) {
            when(it) {
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    hideLoading()
                    showAlert(it.data?.message)
                    start(ManageCallCardActivity::class.java)
                }
                is Resource.Error -> {
                    hideLoading()
                    showAlert(it.error.message)
                }
            }
        }

        viewModel.createCallCard.observe(this) {
            when(it) {
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    hideLoading()
                    showAlert(it.data?.message)
                }
                is Resource.Error -> {
                    hideLoading()
                    showAlert(it.error.message)
                }
            }
        }

        viewModel.listBooks.observe(this){
            when(it){
                is Resource.Loading -> {
                    showLoading()
                }

                is Resource.Success -> {
                    hideLoading()
                    listAllBook.clear()
                    listAllBook.addAll(it.data?.data ?: mutableListOf())
                    listAllBook.removeAll(listBook)
                    bookAdapter2.submitList(listAllBook)
                }

                is Resource.Error -> {
                    hideLoading()
                    showAlert(it.error.message)
                }
            }
        }

        viewModel.getAllStudent.observe(this){
            when(it){
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    hideLoading()
                    listStudent.clear()
                    listStudent.addAll(it.data ?: mutableListOf())
                    binding.spinnerUser.adapter = ArrayAdapter(this@DetailCallCardActivity, R.layout.item_spinner, listStudent.map { it.fullName })
                }
                is Resource.Error -> {
                    hideLoading()
                    showAlert(it.error.message)
                }
            }
        }
    }
    private fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun getCallCard(): CallCardRequest {
        callCardUpdate.borrowDate = binding.edtDateBorrow.text.toString()
        callCardUpdate.dueDate = binding.edtDateDue.text.toString()
        callCardUpdate.note = binding.edtNote.text.toString()
        callCardUpdate.state = binding.spinnerState.selectedItem.toString()
        callCardUpdate.books = listBook
        callCardUpdate.id = callCard?.id?.toLong()
        callCardUpdate.userId = callCard?.user?.id?.toLong()?:listStudent[binding.spinnerUser.selectedItemPosition].id?.toLong()
        return callCardUpdate
    }

    fun checkValidate(): Boolean {
        if(binding.edtDateBorrow.text.toString().isEmpty()) {
            showAlert("Date borrow is empty")
            return false
        }
        if(binding.edtDateDue.text.toString().isEmpty()) {
            showAlert("Date due is empty")
            return false
        }
        if(binding.edtNote.text.toString().isEmpty()) {
            showAlert("Note is empty")
            return false
        }
        if(listBook.isEmpty()) {
            showAlert("List book is empty")
            return false
        }
        return true
    }
}