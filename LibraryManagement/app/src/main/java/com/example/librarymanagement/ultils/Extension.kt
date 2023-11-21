package com.example.librarymanagement.ultils

import android.net.Uri
import android.text.Editable
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.librarymanagement.R


fun ImageView.loadImageBookCover(url: Uri) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.default_book_cover)
        .into(this)
}

fun ImageView.loadImageUser(url: Uri) {
    Glide.with(this)
        .load(url)
        .error(R.drawable.default_avatar)
        .into(this)
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible(isVisible:Boolean) {
    if (isVisible)
        this.visibility = View.VISIBLE
    else
        this.visibility = View.GONE
}



fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)