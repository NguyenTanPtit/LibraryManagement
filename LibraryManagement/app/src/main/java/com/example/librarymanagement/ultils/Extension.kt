package com.example.librarymanagement.ultils

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.librarymanagement.R

fun ImageView.loadImage(url: Uri) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.default_book_cover)
        .into(this)
}


