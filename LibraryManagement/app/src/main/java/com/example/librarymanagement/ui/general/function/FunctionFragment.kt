package com.example.librarymanagement.ui.general.function

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.librarymanagement.base.BaseFragment
import com.example.librarymanagement.databinding.FragmentFunctionBinding
import com.example.librarymanagement.ui.books.ListBookAcitvity
import com.example.librarymanagement.ui.manage.author.ManageAuthorActivity
import com.example.librarymanagement.ui.manage.callcard.ManageCallCardActivity
import com.example.librarymanagement.ui.manage.category.ManageCategoryActivity
import com.example.librarymanagement.ui.manage.statistic.StatisticActivity
import com.example.librarymanagement.ui.manage.statistic.StatisticViewModel
import com.example.librarymanagement.ui.manage.student.ManageStudentActivity
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

        binding.manageStudent.setOnClickListener {
            start(ManageStudentActivity::class.java)
        }

        binding.manageCallCard.setOnClickListener {
            start(ManageCallCardActivity::class.java)
        }

        binding.manageCategory.setOnClickListener {
            start(ManageCategoryActivity::class.java)
        }

        binding.manageAuthor.setOnClickListener {
            start(ManageAuthorActivity::class.java)
        }
        binding.manageStatistic.setOnClickListener {
            start(StatisticActivity::class.java)
        }
    }

}