package com.example.librarymanagement.ui.login

import android.os.Bundle
import androidx.activity.viewModels
import com.example.librarymanagement.base.BaseActivity
import com.example.librarymanagement.base.Resource
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
                    if(it.data?.errorMsg!=null){
                        showAlert("Login Failed")
                    }else{
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
}