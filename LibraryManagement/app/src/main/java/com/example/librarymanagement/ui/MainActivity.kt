package com.example.librarymanagement.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.viewbinding.ViewBinding
import com.example.librarymanagement.R
import com.example.librarymanagement.base.BaseActivity
import com.example.librarymanagement.data.local.user.UserManager
import com.example.librarymanagement.databinding.ActivityMainBinding
import com.example.librarymanagement.ui.general.function.FunctionFragment
import com.example.librarymanagement.ui.general.home.HomeFragment
import com.example.librarymanagement.ui.general.menu.MenuFragment
import com.example.librarymanagement.ui.general.notification.NotificationFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        changeFragment()
    }

    override fun initViews() {
//        TODO("Not yet implemented")
    }

    // create function to change fragment when click on bottom navigation
    private fun changeFragment() {
        binding.bottomNavigationView.menu.findItem(R.id.function).isVisible = UserManager.user?.role == "ADMIN"
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            val selectedFragment: Fragment? = when (it.itemId) {
                R.id.home -> HomeFragment()
                R.id.function -> FunctionFragment()
                R.id.notifications -> NotificationFragment()
                R.id.menu -> MenuFragment()
                else -> null
            }

            // Load the selected fragment
            loadFragment(selectedFragment)

            true
        }
        binding.bottomNavigationView.selectedItemId = R.id.home
    }
    private fun loadFragment(fragment: Fragment?) {
        fragment?.let {
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameLayout, it)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}