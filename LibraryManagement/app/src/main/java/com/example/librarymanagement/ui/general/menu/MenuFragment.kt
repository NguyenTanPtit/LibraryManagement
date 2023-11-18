package com.example.librarymanagement.ui.general.menu

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import com.example.librarymanagement.R
import com.example.librarymanagement.data.local.user.UserManager
import com.example.librarymanagement.base.BaseFragment
import com.example.librarymanagement.databinding.FragmentMenuBinding
import com.example.librarymanagement.models.User
import com.example.librarymanagement.ui.login.LoginActivity
import com.example.librarymanagement.ultils.loadImageUser
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : BaseFragment() {

    companion object {
        fun newInstance() = MenuFragment()
    }

    private val binding: FragmentMenuBinding by lazy {
        FragmentMenuBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }
    private fun initViews() {
        binding.logout.setOnClickListener {
            UserManager.user = null
            start(LoginActivity::class.java)
        }

        binding.userAvatar.loadImageUser((UserManager.user?.avatar?:"").toUri())
        binding.userEmail.text = UserManager.user?.email?: ""
        binding.userFullName.text = UserManager.user?.fullName?: ""
    }

}