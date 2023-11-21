package com.example.librarymanagement.ui.login

import android.os.Bundle
import androidx.activity.viewModels
import com.example.librarymanagement.base.BaseActivity
import com.example.librarymanagement.base.Resource
import com.example.librarymanagement.data.local.user.UserManager
import com.example.librarymanagement.databinding.ActivityLoginBinding
import com.example.librarymanagement.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private val viewModel : LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        binding.btnLogin.setOnClickListener{
            val username = binding.username.text.toString()
            val password = binding.edtPassword.text.toString()
            viewModel.login(username,password)
        }


        viewModel.login.observe(this@LoginActivity){
            when(it){
                is Resource.Loading -> showLoading()

                is Resource.Success -> {
                    hideLoading()
                    if(it.data?.user==null){
                        showAlert("Login Failed")
                    }else{
                        UserManager.user = it.data.user
                        if (binding.checkRemember.isChecked) {
                            //save username and password to shared preference
                            viewModel.saveLoginData(binding.username.text.toString(), binding.edtPassword.text.toString(), true)
                        } else {
                            //clear shared preference
                            viewModel.saveLoginData("", "", false)
                        }
                        start(MainActivity::class.java)
                    }
                }

                is Resource.Error -> {
                    hideLoading()
                    showAlert("Login Failed")
                }
            }
        }

    }

    override fun initViews() {
//        TODO("Not yet implemented")
    }
}