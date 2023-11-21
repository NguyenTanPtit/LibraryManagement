package com.example.librarymanagement.ui.general.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.librarymanagement.R
import com.example.librarymanagement.base.BaseFragment
import com.example.librarymanagement.base.Resource
import com.example.librarymanagement.data.local.user.UserManager
import com.example.librarymanagement.databinding.FragmentHistoryBinding
import com.example.librarymanagement.models.HistoryCallCard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : BaseFragment() {

    private val binding: FragmentHistoryBinding by lazy {
        FragmentHistoryBinding.inflate(layoutInflater)
    }
    private var listHistory = mutableListOf<HistoryCallCard>()
    private val adapter: HistoryAdapter by lazy {
        HistoryAdapter(listHistory)
    }
    private val viewModel: HistoryViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
    }
    fun setUpView(){
        viewModel.getAllCallCards(UserManager.user?.id?.toLong()?:0L)
        binding.apply {
            header.title.text = "History"
            header.btnBack.setOnClickListener {
                requireActivity().onBackPressed()
            }
            historyRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            historyRecyclerView.adapter = adapter
            historyRecyclerView.setHasFixedSize(true)
        }
        initLiveData()
    }

    fun initLiveData(){
        viewModel.callCards.observe(viewLifecycleOwner) {
           when(it){
               is Resource.Loading -> {
                   showLoading()
               }
                is Resource.Success -> {
                     hideLoading()
                     it.data?.data?.let { it1 ->
                         for (i in it1){
                             for (j in i.books!!){
                                 listHistory.add(HistoryCallCard(j,i.borrowDate,i.dueDate,i.state,i.note   ))
                             }
                         }
                     }
                     adapter.updateData(listHistory)
                }
                is Resource.Error -> {
                    hideLoading()
                    showError(it.error.message)
                }
           }
        }
    }

}