package com.example.librarymanagement.ui.books

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.librarymanagement.R
import com.example.librarymanagement.base.BaseActivity
import com.example.librarymanagement.databinding.ActivityEditBookBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditBookActivity : BaseActivity() {

    private val binding by lazy {
        ActivityEditBookBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun initViews() {

    }


}