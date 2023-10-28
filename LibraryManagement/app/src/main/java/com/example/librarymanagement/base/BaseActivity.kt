package com.example.librarymanagement.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Layout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import androidx.viewbinding.ViewBinding
import com.example.librarymanagement.R
import com.example.librarymanagement.databinding.LayoutAlertBinding

abstract  class BaseActivity:AppCompatActivity() , IBaseView{
    private var loadingDialog: AppCompatDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }




    //write method show loading
    @SuppressLint("ResourceType")
    protected fun showLoading(){
        //write method show loading with progress bar and blur background
        if (findViewById<View>(R.layout.layout_loading) != null) {
            findViewById<View>(R.layout.layout_loading).visibility = View.VISIBLE
        }else{
            // create dialog loading
            val view = layoutInflater.inflate(R.layout.layout_loading, null)
            val builder = android.app.AlertDialog.Builder(this)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
        }
    }

    override fun showLoading(text: String?, isDialog: Boolean?) {
//        TODO("Not yet implemented")
    }

    //write method hide loading
    @SuppressLint("ResourceType")
    override fun hideLoading() {
        val frameLoading = this.findViewById<View>(R.layout.layout_loading)
        if (frameLoading != null) {
            frameLoading.visibility = View.GONE
            if (loadingDialog != null){
                loadingDialog?.dismiss()
                loadingDialog = null
            }
        } else {
            loadingDialog?.dismiss()
            loadingDialog = null
        }

    }

    override fun showError(message: String?, isToast: Boolean) {
//        TODO("Not yet implemented")
    }

    override fun onRefresh() {
//        TODO("Not yet implemented")
    }

    override fun onLoadMore() {
//        TODO("Not yet implemented")
    }

    //write method show alert with message and button ok
    @SuppressLint("ResourceType")
    override fun showAlert(message: String?) {
        //write method show alert with message and button ok
        if (findViewById<View>(R.layout.layout_alert) != null) {
            findViewById<View>(R.layout.layout_alert).visibility = View.VISIBLE
        }else{
            val view = LayoutAlertBinding.inflate(layoutInflater)
            //center layout alert
            view.tvAlert.text = message
            val builder = android.app.AlertDialog.Builder(this)
            builder.setView(view.root)
            val dialog = builder.create()
            view.btnCloseAlert.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }
    }

    //write method go to activity with parameter is class name
    protected fun start(clazz: Class<*>, bundle: Bundle? = null) {
        val intent = Intent(this, clazz)
        bundle?.let { intent.putExtras(it) }
        startActivity(intent)
    }

    //write method hide keyboard
    protected fun hideKeyboard(){
        //write method hide keyboard
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken , 0)
        }
    }

}