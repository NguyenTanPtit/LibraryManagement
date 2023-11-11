//package com.example.librarymanagement.ui.customview
//
//import android.content.Context
//import android.util.AttributeSet
//import android.widget.FrameLayout
//import androidx.annotation.StringRes
//import android.view.MotionEvent
//import android.view.View
//import android.widget.ArrayAdapter
//import android.widget.SpinnerAdapter
//import androidx.appcompat.widget.AppCompatSpinner
//import androidx.constraintlayout.widget.ConstraintLayout
//import androidx.core.content.res.ResourcesCompat
//import androidx.core.view.isVisible
//import com.example.librarymanagement.R
//import com.example.librarymanagement.base.Resource
//import com.example.librarymanagement.databinding.LayoutSpinnerComponentBinding
//import com.example.librarymanagement.ultils.gone
//import com.example.librarymanagement.ultils.visible
//
//class SpinnerComponent @JvmOverloads constructor(
//    context: Context,
//    attrs: AttributeSet? = null,
//    defStyle: Int = 0
//) : FrameLayout(context, attrs, defStyle) {
//
//    var binding: LayoutSpinnerComponentBinding =
//        LayoutSpinnerComponentBinding.inflate(context.layoutInflater, this, true)
//
//    private var title: String? = null
//    private var isRequired: Boolean = false
//
//    var onItemSelectedListener: SpinnerItemSelectListener? = null
//        set(value) {
//            binding.spinner.spinner.onItemSelectedListener = value
//            field = value
//        }
//
//    val spinner: AppCompatSpinner
//        get() = binding.spinner.spinner
//
//    val itemSelected: Int
//        get() {
//            return spinner.selectedItemPosition
//        }
//
//    val selectedData: Any?
//        get() {
//            val position = itemSelected
//            if (position < 0 || spinner.adapter.count <= position) return null
//            return spinner.adapter.getItem(itemSelected)
//        }
//
//    var isEnable: Boolean = false
//        get() = isEnabled
//        set(value) {
//            field = value
//            spinner.isEnabled = value
//            isEnabled = value
//            if (value) {
//                binding.tvTitle.setTextColor(ResourcesCompat.getColor(resources, R.color.black, null))
//            } else {
//                binding.tvTitle.setTextColor(ResourcesCompat.getColor(resources, R.color.gray, null))
//            }
//            setExpand(value)
//        }
//
//    fun setSelection(position: Int) {
//        spinner.setSelection(position)
//    }
//
//    fun updateRequired(isRequired: Boolean) {
//        this.isRequired = isRequired
//        val star = if (isRequired) ResourcesCompat.getDrawable(resources, R.drawable.ic_required, null) else null
//        binding.tvTitle.setCompoundDrawablesRelativeWithIntrinsicBounds(
//            null,
//            null,
//            star,
//            null
//        )
//    }
//
//    fun setEnableSpinner(isEnable: Boolean = true) {
//        binding.spinner.spinner.isEnabled = isEnable
//    }
//
//    fun setExpand(isEnable: Boolean = true) {
//        binding.spinner.icExpand.visible(isEnable)
//    }
//
//    init {
//        context.theme.obtainStyledAttributes(attrs, R.styleable.SpinnerComponent, defStyle, 0)
//            .apply {
//                title = getString(R.styleable.SpinnerComponent_android_title)
//                isRequired = getBoolean(R.styleable.SpinnerComponent_android_required, false)
//                if (title?.endsWith("(*)") == true) {
//                    isRequired = true
//                    title = title?.substring(0, title!!.length - 3)
//                }
//            }
//        if (title.isNullOrEmpty()) {
//            binding.tvTitle.isVisible = false
//        } else {
//            binding.tvTitle.text = title
//            if (isRequired) {
//                val star = ResourcesCompat.getDrawable(resources, R.drawable.ic_required, null)
//                binding.tvTitle.setCompoundDrawablesRelativeWithIntrinsicBounds(
//                    null,
//                    null,
//                    star,
//                    null
//                )
//            }
//        }
//        setOnClickListener {
//            if (isEnable){
//                binding.spinner.spinner.performClick()
//                binding.spinner.tvError.visibility = View.GONE
//            }
//        }
//    }
//
//    inline fun <reified T> applyResource(
//        resource: Resource<List<T>>,
//        @StringRes resId: Int? = null
//    ) {
//        binding.spinner.apply {
//            spinnerProgressBar.gone()
//            tvError.gone()
//            when (resource) {
//                is Resource.Loading -> {
//                    spinner.adapter =
//                        context.spinnerAdapter(resId?.let { listOf(context.getString(it)) }
//                            ?: listOf())
//                    spinnerProgressBar.visible()
//                }
//                is Resource.Success -> {
//                    val array = (resource.data).toTypedArray()
//                    spinner.adapter =
//                        context.spinnerAdapter(
//                            resId?.let {
//                                listOf(
//                                    context.getString(it),
//                                    *array
//                                )
//                            } ?: listOf(
//                                *array
//                            )
//                        )
//                }
//                is Resource.Error -> {
//                    tvError.visible()
//                    tvError.text = resource.error.message
//                }
//            }
//        }
//    }
//    fun removeRequired() {
//        isRequired = false
//        updateRequired(false)
//    }
//    fun title (title: String){
//        binding.tvTitle.text = title
//    }
//
//    fun showLoading() {
//        binding.spinner.spinnerProgressBar.visible()
//    }
//
//    fun hideLoading() {
//        binding.spinner.spinnerProgressBar.gone()
//    }
//    fun changeTextViewErrorTexColor(error: String, id:Int? = null){
//        binding.spinner.tvError.isVisible = error.isNotEmpty()
//        binding.spinner.tvError.text = error
//        if (id != null) {
//            binding.spinner.tvError.setTextColor(resources.getColor(id,null))
//        }
//    }
//    fun showError(error: String) {
//        binding.spinner.tvError.isVisible = error.isNotEmpty()
//        binding.spinner.tvError.text = error
//        binding.tvTitle.setTextColor(ResourcesCompat.getColor(resources, R.color.textRed, null))
//        hideLoading()
//
//        spinner.setOnTouchListener { _, event ->
//            if (MotionEvent.ACTION_DOWN == event.action) {
//                binding.spinner.tvError.gone()
//                binding.tvTitle.setTextColor(ResourcesCompat.getColor(resources, R.color.black, null))
//                performClick()
//            }
//            return@setOnTouchListener false
//        }
//    }
//
//    fun hideError() {
//        binding.spinner.tvError.gone()
//        binding.spinner.tvError.text = ""
//        binding.tvTitle.setTextColor(ResourcesCompat.getColor(resources, R.color.black, null))
//    }
//
//    inline fun <reified T> applyNullableResource(
//        resource: Resource<List<T>?>,
//        @StringRes resId: Int? = null
//    ) {
//        binding.spinner.apply {
//            spinnerProgressBar.gone()
//            tvError.gone()
//            when (resource) {
//                is Resource.Loading -> {
//                    spinner.adapter =
//                        context.spinnerAdapter(resId?.let { listOf(context.getString(it)) }
//                            ?: listOf())
//                    spinnerProgressBar.visible()
//                }
//                is Resource.Success -> {
//                    val array = (resource.data ?: listOf()).toTypedArray()
//                    if (array.isEmpty()) {
//                        spinner.adapter =
//                            context.spinnerAdapter(resId?.let { listOf(context.getString(it)) } ?: listOf())
//                    } else {
//                        spinner.adapter = context.spinnerAdapter(listOf(*array))
//                    }
//                }
//                is Resource.Error -> {
//                    tvError.visible()
//                    tvError.text = resource.error.message
//                }
//            }
//        }
//    }
//
//    inline fun <reified T> applyResourceWithDefaultOption(
//        resource: Resource<List<T>?>,
//        @StringRes resId: Int = R.string.hint_option
//    ) {
//        binding.spinner.apply {
//            spinnerProgressBar.gone()
//            tvError.gone()
//            when (resource) {
//                is Resource.Loading -> {
//                    spinner.adapter =
//                        context.spinnerAdapter(listOf(context.getString(resId)))
//                    spinnerProgressBar.visible()
//                }
//                is Resource.Success -> {
//                    val array = (resource.data ?: listOf()).toTypedArray()
//                    if (array.isEmpty()) {
//                        spinner.adapter =
//                            context.spinnerAdapter(listOf(context.getString(resId)))
//                    } else {
//                        spinner.adapter = context.spinnerAdapter(listOf(context.getString(resId), *array))
//                    }
//                }
//                is Resource.Error -> {
//                    tvError.visible()
//                    tvError.text = resource.error.message
//                }
//            }
//        }
//    }
//
//    fun showInfo(show: Boolean = false){
//        binding.imgInfo.isVisible = show
//    }
//
//    fun applyHint(@StringRes resId: Int? = R.string.hint_option) {
//        binding.spinner.spinner.adapter =
//            context.spinnerAdapter(resId?.let { listOf(context.getString(it)) } ?: listOf())
//        hideLoading()
//    }
//
////    inline fun <reified T> applyList(resource: List<T>, @StringRes resId: Int? = null) {
////        binding.spinner.apply {
////            if (resId != null) {
////                spinner.adapter =
////                    context.spinnerAdapter(
////                        listOf(
////                            UserScope(displayStr = context.getString(resId)),
////                            *resource.toTypedArray()
////                        )
////                    )
////            } else {
////                spinner.adapter = context.spinnerAdapter(resource)
////            }
////            spinnerProgressBar.gone()
////        }
////    }
//    fun setMarginStartSpinner(param:Int){
//        (binding.spinner.line.layoutParams as? ConstraintLayout.LayoutParams)?.apply {
//            marginStart = param
//        }
//        (binding.spinner.tvError.layoutParams as? ConstraintLayout.LayoutParams)?.apply {
//            marginStart = param
//        }
//    }
//}
//
//fun <T> Context.spinnerAdapter(listOf: List<T>): SpinnerAdapter? {
//    val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listOf)
//    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//    return adapter
//}