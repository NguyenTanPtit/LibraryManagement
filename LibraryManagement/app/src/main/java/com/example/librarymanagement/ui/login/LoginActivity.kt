package com.example.librarymanagement.ui.login

import android.os.Bundle
import androidx.activity.viewModels
import com.example.librarymanagement.base.BaseActivity
import com.example.librarymanagement.base.Resource
import com.example.librarymanagement.databinding.ActivityLoginBinding
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

        val username = binding.username.text.toString()
        val password = binding.edtPassword.text.toString()

        binding.btnLogin.setOnClickListener{

            viewModel.login(username,password)

        }


        viewModel.login.observe(this@LoginActivity){
            when(it){
                is Resource.Loading -> showLoading()

                is Resource.Success -> {
                    if(it.data?.errorMsg!=null){
                        showAlert()
                    }else{
                        gotoActivity()
                    }
                }

                is Resource.Error -> showAlert()
            }
        }
    }
}