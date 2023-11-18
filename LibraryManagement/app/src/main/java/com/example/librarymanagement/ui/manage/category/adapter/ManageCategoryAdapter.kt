package com.example.librarymanagement.ui.manage.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.librarymanagement.databinding.ItemManageCategoryBinding
import com.example.librarymanagement.models.Category

class ManageCategoryAdapter(
    private val list: List<Category>,
    val onDelete: (Category) -> Unit,
    val onUpdate: (Category) -> Unit
) : RecyclerView.Adapter<ManageCategoryAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemManageCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val category = list[position]
            binding.authorName.text = category.name

            binding.deleteAuthor.setOnClickListener {
                onDelete.invoke(category)
            }
            binding.editAuthor.setOnClickListener {
                onUpdate.invoke(category)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemManageCategoryBinding.inflate(
               LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}