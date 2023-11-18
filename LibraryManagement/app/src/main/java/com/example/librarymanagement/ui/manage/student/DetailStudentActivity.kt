package com.example.librarymanagement.ui.manage.student

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import com.example.librarymanagement.base.BaseActivity
import com.example.librarymanagement.base.Resource
import com.example.librarymanagement.databinding.ActivityDetailStudentBinding
import com.example.librarymanagement.models.User
import com.example.librarymanagement.ultils.gone
import com.example.librarymanagement.ultils.loadImageUser
import com.example.librarymanagement.ultils.visible
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailStudentActivity : BaseActivity() {

    private val binding: ActivityDetailStudentBinding by lazy {
        ActivityDetailStudentBinding.inflate(layoutInflater)
    }
    private val viewModel: ManageStudentViewModel by viewModels()
    private var isEdit = false
    private lateinit var student: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initViews() {
        if (intent.hasExtra("student")) {
            student = intent.getSerializableExtra("student") as User
            binding.apply {
                edtFullName.text = strToEditable(student.fullName)
                edtEmail.text = strToEditable(student.email)
                edtPhoneNum.text = strToEditable(student.phoneNumber)
                edtPassword.text = strToEditable(student.password)
                edtAddress.text = strToEditable(student.address)
                edtUsernameInput.text = strToEditable(student.userName)
                userAvatar.loadImageUser(student.avatar.toUri())
                disableEdit()
                if (isEdit) {
                    btnUpdate.gone()
                    btnSave.visible()
                } else {
                    btnUpdate.visible()
                    btnSave.gone()
                }
                btnAdd.gone()
            }
        }
        else{
            binding.apply {
                btnUpdate.gone()
                edtPasswordLayout.visible()
                btnSave.gone()
                btnAdd.visible()
            }
            student = User(null,"","","","","","","","","STUDENT")
        }

        binding.btnUpdate.setOnClickListener {
            enableEdit()
        }
        binding.addImgIcon.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 99999)
        }
        binding.tvCancel.setOnClickListener {
            disableEdit()
        }
        binding.btnSave.setOnClickListener {
            getStudent()
            viewModel.updateStudent(student)
        }
        binding.btnAdd.setOnClickListener {
            getStudent()
            viewModel.createStudent(student)
        }
        initObservers()
    }

    fun getStudent() {
        var  check = true
        binding.apply {
            if (edtFullName.text.isNullOrEmpty()) {
                edtFullName.error = "Full name is required"
                check = false
            }
            if (edtPhoneNum.text.isNullOrEmpty()) {
                edtPhoneNum.error = "Phone number is required"
                check = false
            }
            if (edtEmail.text.isNullOrEmpty()) {
                edtEmail.error = "Email is required"
                check = false
            }
            if (edtAddress.text.isNullOrEmpty()) {
                edtAddress.error = "Address is required"
                check = false
            }
            if (edtUsernameInput.text.isNullOrEmpty()) {
                edtUsernameInput.error = "Username is required"
                check = false
            }
            if (edtPassword.text.isNullOrEmpty()) {
                edtPassword.error = "Password is required"
                check = false
            }
            if (check) {
                student.fullName = edtFullName.text.toString()
                student.email = edtEmail.text.toString()
                student.phoneNumber = edtPhoneNum.text.toString()
                student.address = edtAddress.text.toString()
                student.userName = edtUsernameInput.text.toString()
                student.password = edtPassword.text.toString()
            }
        }

    }
    fun disableEdit() {
        binding.apply {
            isEdit = false
            edtFullName.isEnabled = false
            edtUsernameInput.isEnabled = false
            edtEmail.isEnabled = false
            edtPassword.isEnabled = false
            edtPhoneNum.isEnabled = false
            edtAddress.isEnabled = false
            addImgIcon.gone()
            tvCancel.gone()
            btnUpdate.visible()
            btnSave.gone()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun enableEdit() {
        binding.apply {
            isEdit = true
            edtFullName.isEnabled = true
            edtUsernameInput.isEnabled = true
            edtUsernameInput.isFocusable = true
            edtEmail.isEnabled = true
            edtPassword.isEnabled = true
            edtPhoneNum.isEnabled = true
            edtAddress.isEnabled = true
            addImgIcon.visible()
            tvCancel.visible()
            btnUpdate.gone()
            btnSave.visible()
        }
    }

    fun strToEditable(str: String): Editable {
        return Editable.Factory.getInstance().newEditable(str)
    }

    fun initObservers() {
        viewModel.updateStudent.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    showLoading()
                }

                is Resource.Success -> {
                    hideLoading()
                    if (it.data != null) {
                        showAlert(it.data.message)
                        start(ManageStudentActivity::class.java)
                    }
                    disableEdit()
                }

                is Resource.Error -> {
                    hideLoading()
                    showAlert(it.error.message)
                }
            }
        }

        viewModel.createStudent.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    showLoading()
                }

                is Resource.Success -> {
                    hideLoading()
                    if (it.data != null) {
                        showAlert(it.data.message)
                        start(ManageStudentActivity::class.java)
                    }
                    disableEdit()
                }

                is Resource.Error -> {
                    hideLoading()
                    showAlert(it.error.message)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        showLoading()
        if (requestCode == 99999 && resultCode == RESULT_OK && data != null) {
            val imageUri = data.data
            val reference: StorageReference = FirebaseStorage.getInstance().reference
            if (imageUri != null) {
                val taskUpLoad = reference.child("user_avatar")
                    .child(student.id.toString()).putFile(imageUri)
                taskUpLoad.addOnSuccessListener {
                    taskUpLoad.continueWithTask { task ->
                        if (!task.isSuccessful) {
                            task.exception?.let {
                                throw it
                            }
                        }
                        reference.child("user_avatar").child(student.id.toString()).downloadUrl
                    }.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val downloadUri = task.result
                            if (downloadUri != null) {
                                student.avatar = downloadUri.toString()
                            }
                            Toast.makeText(this, "Upload image successful!", Toast.LENGTH_SHORT).show()
                            hideLoading()
                        } else {
                            hideLoading()
                            showAlert("Error when load image")
                        }
                    }
                }
                binding.userAvatar.loadImageUser(imageUri)
            } else
                showAlert("Error when load image")
        }
    }
}