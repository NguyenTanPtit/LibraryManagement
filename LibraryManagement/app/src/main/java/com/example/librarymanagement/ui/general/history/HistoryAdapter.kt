package com.example.librarymanagement.ui.general.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.librarymanagement.databinding.ItemHistoryBinding
import com.example.librarymanagement.models.HistoryCallCard
import com.example.librarymanagement.ultils.loadImageBookCover

class HistoryAdapter (var list: List<HistoryCallCard>):RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    inner class ViewHolder(val binding : ItemHistoryBinding):RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(position: Int){
            val historyCallCard = list[position]
           binding.apply {
               itemImg.loadImageBookCover(historyCallCard.books?.cover?.toUri()?:"".toUri())
               tvTitle.text = historyCallCard.books?.title
               tvAuthor.text = historyCallCard.books?.authorName
               tvTime.text = "From ${historyCallCard.borrowDate} to ${historyCallCard.dueDate}"
               tvState.text = historyCallCard.state
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

    fun updateData(list: List<HistoryCallCard>){
        this.list = list
        notifyDataSetChanged()
    }
}