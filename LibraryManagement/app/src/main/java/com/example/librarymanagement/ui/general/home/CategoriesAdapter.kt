package com.example.librarymanagement.ui.general.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.librarymanagement.databinding.ItemCategoryBinding
import com.example.librarymanagement.models.Category

class CategoriesAdapter(var listCategories:MutableList<Category>,
    private val onItemClick: (Int)-> Unit): RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoriesAdapter.CategoriesViewHolder {
       return CategoriesViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    inner class CategoriesViewHolder(private val binding: ItemCategoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = listCategories[position]
            binding.apply {
                tvCategoryName.text = item.name
            }
        }
    }

    override fun onBindViewHolder(holder: CategoriesAdapter.CategoriesViewHolder, position: Int) {
        holder.bind(position)
        holder.itemView.rootView.setOnClickListener{
            onItemClick.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        return listCategories.size
    }

    fun submitList(list: List<Category>) {
        listCategories = list.toMutableList()
        notifyDataSetChanged()
    }
}