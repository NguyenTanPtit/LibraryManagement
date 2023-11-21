package com.example.librarymanagement.ui.general.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.librarymanagement.R
import com.example.librarymanagement.base.BaseFragment
import com.example.librarymanagement.base.Resource
import com.example.librarymanagement.data.local.user.UserManager
import com.example.librarymanagement.databinding.FragmentNotificationBinding
import com.example.librarymanagement.models.Notification
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationFragment : BaseFragment() {

    val binding: FragmentNotificationBinding by lazy {
        FragmentNotificationBinding.inflate(layoutInflater)
    }
    private val viewModel: NotificationViewModel by viewModels()
    private val listNoti = mutableListOf<Notification>()
    private val adapter by lazy {
        NotificationAdapter(listNoti)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun initView(){
        viewModel.getAllNotifications(UserManager.user?.id?.toLong() ?: 0L)
        binding.apply {
            layoutHeader.title.text = "Notification"
            layoutHeader.btnBack.setOnClickListener {
                requireActivity().onBackPressed()
            }
            rvNotification.adapter = adapter
            rvNotification.setHasFixedSize(true)
            rvNotification.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            swipeToDelete(rvNotification)
        }
        initLiveData()
    }

    private fun swipeToDelete(recyclerView: RecyclerView) {
        val swipeToDelete= object : SwipeToDelete(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.absoluteAdapterPosition
                val noti = adapter.listNoti[position]
                viewModel.deleteNotification(noti.id ?: 0L)
                val snackbar = Snackbar.make(
                    requireView(),"Deleted", Snackbar.LENGTH_LONG
                ).addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>(){
                    override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                        super.onDismissed(transientBottomBar, event)
                    }

                    override fun onShown(transientBottomBar: Snackbar?) {
                        transientBottomBar?.setAction("Undo"){
                            viewModel.saveNotification(noti)
                        }

                        super.onShown(transientBottomBar)
                    }
                }).apply {
                    animationMode  = Snackbar.ANIMATION_MODE_FADE
                    anchorView = binding.rvNotification
                }

                snackbar.setActionTextColor(
                    ContextCompat.getColor(
                        requireContext(),R.color.state_borrowed
                    )
                )
                snackbar.show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDelete)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    fun initLiveData(){
        viewModel.notifications.observe(viewLifecycleOwner) {
            when(it){
                is Resource.Loading -> {
                    showLoading()
                }

                is Resource.Success -> {
                    hideLoading()
                    listNoti.clear()
                    listNoti.addAll(it.data?.data ?: listOf())
                    adapter.updateList(listNoti)
                }

                is Resource.Error -> {
                    hideLoading()
                    showError(it.error.message)
                }
            }
        }
        viewModel.saveNotification.observe(viewLifecycleOwner) {
            when(it){
                is Resource.Loading -> {
                    showLoading()
                }

                is Resource.Success -> {
                    hideLoading()
                    viewModel.getAllNotifications(UserManager.user?.id?.toLong() ?: 0L)
                }

                is Resource.Error -> {
                    hideLoading()
                    showError(it.error.message)
                }
            }
        }

        viewModel.deleteNotification.observe(viewLifecycleOwner) {
            when(it){
                is Resource.Loading -> {
                    showLoading()
                }

                is Resource.Success -> {
                    hideLoading()
//                    showAlert(it.data?.message ?: "")
                    viewModel.getAllNotifications(UserManager.user?.id?.toLong() ?: 0L)
                }

                is Resource.Error -> {
                    hideLoading()
                    showError(it.error.message)
                }
            }
        }
    }


}