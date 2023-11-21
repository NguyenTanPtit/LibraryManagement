package com.example.librarymanagement.ui.manage.statistic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.librarymanagement.databinding.ItemHistoryBinding
import com.example.librarymanagement.models.BookDetailResponse
import com.example.librarymanagement.ultils.loadImageBookCover

class StatisticAdapter(var list: List<BookDetailResponse>,val onItemClick:(Int)->Unit):RecyclerView.Adapter<StatisticAdapter.ViewHolder>() {

    inner class ViewHolder(val binding:ItemHistoryBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position:Int){
            val item = list[position]
            binding.apply {
                itemImg.loadImageBookCover(item.cover?.toUri()?:"".toUri())
                tvTitle.text = item.title
                tvAuthor.text = item.authorName
                tvTime.text = item.categoryName
                tvState.text = item.state
                root.setOnClickListener {
                    onItemClick(item.id?.toInt()?:-1)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHistoryBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    fun updateData(list: List<BookDetailResponse>){
        this.list = list
        notifyDataSetChanged()
    }
}