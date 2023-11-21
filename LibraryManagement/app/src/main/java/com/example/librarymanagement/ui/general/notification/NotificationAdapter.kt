package com.example.librarymanagement.ui.general.notification


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.librarymanagement.databinding.ItemNotificationBinding
import com.example.librarymanagement.models.Notification

class NotificationAdapter(var listNoti: MutableList<Notification>):RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ItemNotificationBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int){
            val item = listNoti[position]
            binding.apply {
                tvNotificationTitle.text = item.title
                tvNotificationContent.text = item.content
                tvNotificationDate.text = item.date
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return listNoti.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(position)
    }

    fun updateList(newList: MutableList<Notification>){
        listNoti = newList
        notifyDataSetChanged()
    }
}