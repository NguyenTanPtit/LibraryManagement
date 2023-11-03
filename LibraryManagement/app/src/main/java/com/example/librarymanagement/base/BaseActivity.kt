package com.example.librarymanagement.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Layout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import androidx.appcompat.widget.ContentFrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewbinding.ViewBinding
import com.example.librarymanagement.R
import com.example.librarymanagement.databinding.LayoutAlertBinding
import com.example.librarymanagement.databinding.LayoutLoadingBinding

abstract  class BaseActivity:AppCompatActivity() , IBaseView{
    private var loadingView: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupLoadingView()
        initViews()
    }

    private fun setupLoadingView() {
        val inflater = LayoutInflater.from(this)
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



    //write method show loading
    @SuppressLint("ResourceType")
    protected fun showLoading(){
        if(findViewById<View>(R.layout.layout_loading) != null){
            findViewById<View>(R.layout.layout_loading).visibility = View.VISIBLE
        }else {
            loadingView?.let {
                if (it.parent == null) {
                    val rootView = findViewById<View>(android.R.id.content)
                    (rootView as ContentFrameLayout).addView(it)
                }
            }
        }
    }

    @SuppressLint("ResourceType")
    override fun showLoading(text: String?, isDialog: Boolean?) {
        if(findViewById<View>(R.layout.layout_loading) != null){
            findViewById<View>(R.layout.layout_loading).visibility = View.VISIBLE
        }else{
            loadingView?.let {
                if (it.parent == null) {
                    val rootView = findViewById<View>(android.R.id.content)
                    (rootView as ContentFrameLayout).addView(it)
                }
            }
        }
    }

    //write method hide loading
    @SuppressLint("ResourceType")
    override fun hideLoading() {
        if(findViewById<View>(R.layout.layout_loading) != null) {
            findViewById<View>(R.layout.layout_loading).visibility = View.GONE
        }else {
            loadingView?.let {
                val rootView = findViewById<View>(android.R.id.content)
                (rootView as ContentFrameLayout).removeView(it)
            }
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
                hideLoading()
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

    abstract fun initViews()

}