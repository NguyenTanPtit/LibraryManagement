package com.example.librarymanagement.ui.general.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.librarymanagement.databinding.ItemCategoryBinding
import com.example.librarymanagement.models.Author

class AuthorAdapter(var list: MutableList<Author>, val onItemClick:(Int) -> Unit):
    RecyclerView.Adapter<AuthorAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemCategoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = list[position]
            binding.apply {
                tvCategoryName.text = item.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false))
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

    fun submitList(list: MutableList<Author>) {
        this.list = list
        notifyDataSetChanged()
    }
}