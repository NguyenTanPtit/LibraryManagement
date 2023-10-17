package com.example.librarymanagement.base

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.librarymanagement.R

open class BaseFragment:Fragment() {

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("ResourceType")
    fun setTitle(title:String){
        val layoutHeader = view?.findViewById<View>(R.layout.layout_header)
        if(layoutHeader!=null){
            layoutHeader.findViewById<TextView>(R.id.title).text = title
        }
    }
}