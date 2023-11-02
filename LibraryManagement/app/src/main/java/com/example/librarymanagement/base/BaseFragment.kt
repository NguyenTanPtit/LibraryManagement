package com.example.librarymanagement.base

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import androidx.appcompat.widget.ContentFrameLayout
import androidx.fragment.app.Fragment
import com.example.librarymanagement.R
import com.example.librarymanagement.databinding.LayoutAlertBinding

open class BaseFragment:Fragment(),IBaseView {
    private var loadingView: View? = null
    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLoadingView()
    }
    @SuppressLint("ResourceType")
    fun setTitle(title:String){
        val layoutHeader = view?.findViewById<View>(R.layout.layout_header)
        if(layoutHeader!=null){
            layoutHeader.findViewById<TextView>(R.id.title).text = title
        }
    }
    private fun setupLoadingView() {
        val inflater = LayoutInflater.from(requireContext())
        loadingView = inflater.inflate(R.layout.layout_loading, null)
        val bgColor: Int = Color.TRANSPARENT
        val background: Drawable = ColorDrawable(bgColor)
        loadingView?.background = background

        val progressBar = loadingView?.findViewById<ProgressBar>(R.id.progressBar)
        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        params.addRule(RelativeLayout.CENTER_IN_PARENT)
        progressBar?.layoutParams = params
    }




    override fun showLoading(text: String?, isDialog: Boolean?) {
        if (isDialog == true) {
            val dialog = AppCompatDialog(requireContext())
            dialog.setCancelable(false)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setContentView(R.layout.layout_loading)
            dialog.show()
        } else {
            loadingView?.let {
                if (it.parent == null) {
                    (view?.parent as ViewGroup).addView(loadingView)
                }
            }
        }
    }

    override fun hideLoading() {
        loadingView?.let {
            (view?.parent as ViewGroup).removeView(loadingView)
        }
    }

    @SuppressLint("ResourceType")
    override fun showError(message: String?, isToast: Boolean) {
        if (isToast) {
            showToast(requireContext(), message.toString())
        } else {
            if (view?.findViewById<View>(R.layout.layout_alert) != null) {
                view?.findViewById<View>(R.layout.layout_alert)!!.visibility = View.VISIBLE
            }else{
                val view = LayoutAlertBinding.inflate(layoutInflater)
                view.tvAlert.text = message
                val builder = android.app.AlertDialog.Builder(requireContext())
                builder.setView(view.root)
                val dialog = builder.create()
                view.btnCloseAlert.setOnClickListener {
                    dialog.dismiss()
                    hideLoading()
                }
                dialog.show()
            }

        }
    }

    override fun onRefresh() {
        TODO("Not yet implemented")
    }

    override fun onLoadMore() {
        TODO("Not yet implemented")
    }
}