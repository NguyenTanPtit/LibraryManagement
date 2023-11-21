package com.example.librarymanagement.ui.general.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.librarymanagement.R
import com.example.librarymanagement.data.local.user.UserManager
import com.example.librarymanagement.databinding.ItemBookRecBinding
import com.example.librarymanagement.models.BookDetailResponse
import com.example.librarymanagement.ultils.gone
import com.example.librarymanagement.ultils.visible

class BookAdapter(val context: Context,
                  var list: MutableList<BookDetailResponse>, val onItemClick:(Int) -> Unit):
    RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemBookRecBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {
            val item = list[position]
            binding.apply {
                itemName.text = item.title
                itemPage.text= item.pageNumber
                itemPrice.text = "$${item.price}"
                Glide.with(context).load(item.cover?.toUri()).error(R.drawable.default_book_cover).into(itemImg)
                when(item.state) {
                    "Available" -> {
                        itemState.text = "Available"
                        itemState.setTextColor(context.resources.getColor(R.color.black))
                        llState.setBackgroundResource(R.color.green)
                    }
                    "Borrowed" -> {
                        itemState.text = "Borrowed"
                        itemState.setTextColor(context.resources.getColor(R.color.black))
                        llState.setBackgroundResource(R.color.state_borrowed)
                    }
                }
                deleteItem.setOnClickListener {
                    list.remove(item)
                    notifyDataSetChanged()
                }
                if(UserManager.user?.role == "ADMIN") {
                    root.setOnLongClickListener {
                        binding.deleteItem.visible()
                        true
                    }
                    itemImg.setOnClickListener {
                        binding.deleteItem.gone()
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemBookRecBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
        holder.itemView.rootView.setOnClickListener{
            onItemClick.invoke(position)
        }
    }

    fun updateData(list: List<BookDetailResponse>) {
        this.list = list.toMutableList()
        notifyDataSetChanged()
    }
}