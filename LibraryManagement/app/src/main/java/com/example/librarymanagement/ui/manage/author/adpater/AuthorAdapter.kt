package com.example.librarymanagement.ui.manage.author.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.librarymanagement.databinding.ItemManageAuthorBinding
import com.example.librarymanagement.models.Author

class AuthorAdapter (val listAuthor:MutableList<Author>,val onDelete:(Author)->Unit,val onEdit:(Author)->Unit)
    :RecyclerView.Adapter<AuthorAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemManageAuthorBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int){
            val author = listAuthor[position]
            binding.authorName.text = author.name

            binding.deleteAuthor.setOnClickListener {
                onDelete.invoke(author)
            }

            binding.editAuthor.setOnClickListener{
                onEdit.invoke(author)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemManageAuthorBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
       return listAuthor.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    fun submitList(list: List<Author>) {
        listAuthor.clear()
        listAuthor.addAll(list)
        notifyDataSetChanged()
    }
}