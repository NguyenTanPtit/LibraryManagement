package com.example.librarymanagement.ui.manage.callcard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.librarymanagement.databinding.ItemAddBookBinding
import com.example.librarymanagement.models.BookDetailResponse
import com.example.librarymanagement.ultils.loadImageBookCover

class AddBookAdapter(private var listBook: MutableList<BookDetailResponse> ): RecyclerView.Adapter<AddBookAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemAddBookBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = listBook[position]
            binding.apply {
                itemImg.loadImageBookCover(item.cover!!.toUri())
                tvTitle.text = item.title
                tvAuthor.text = item.authorName
                binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
                    item.isChecked = isChecked
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemAddBookBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return listBook.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    fun submitList(list: List<BookDetailResponse>) {
        listBook = list.toMutableList()
        notifyDataSetChanged()
    }

    fun getSelectedBooks(): List<BookDetailResponse> {
        val listSelectedBook = mutableListOf<BookDetailResponse>()
        for (book in listBook) {
            if (book.isChecked) {
                listSelectedBook.add(book)
            }
        }
        return listSelectedBook
    }
}