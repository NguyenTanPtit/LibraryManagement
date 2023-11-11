package com.example.librarymanagement.ui.general.function

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.librarymanagement.base.BaseFragment
import com.example.librarymanagement.databinding.FragmentFunctionBinding
import com.example.librarymanagement.ui.books.ListBookAcitvity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FunctionFragment : BaseFragment() {

    val binding: FragmentFunctionBinding by lazy {
        FragmentFunctionBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.manageBook.setOnClickListener {
            start(ListBookAcitvity::class.java)
        }
    }

}