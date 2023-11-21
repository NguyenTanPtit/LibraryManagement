package com.example.librarymanagement.ui.manage.statistic

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.librarymanagement.databinding.ItemHistoryBinding
import com.example.librarymanagement.models.Statistic
import com.example.librarymanagement.ultils.loadImageUser

class DetailStatisticAdapter (var listStatistic: MutableList<Statistic>):RecyclerView.Adapter<DetailStatisticAdapter.ViewHolder>() {
    inner class ViewHolder(val binding : ItemHistoryBinding):RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(position: Int){
            val item = listStatistic[position]
            binding.apply {
                tvTitle.text = item.user?.fullName?:""
                tvAuthor.text = item.user?.email?:""
                tvTime.text = "From ${item.borrowDate} to ${item.dueDate}"
                tvState.text = item.state
                itemImg.loadImageUser((item.user?.avatar?:"").toUri())
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHistoryBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return listStatistic.size
    }

    fun updateData(list: List<Statistic>){
        listStatistic = list.toMutableList()
        notifyDataSetChanged()
    }
}