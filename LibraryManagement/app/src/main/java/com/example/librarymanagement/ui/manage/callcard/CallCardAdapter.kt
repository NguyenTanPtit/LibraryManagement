package com.example.librarymanagement.ui.manage.callcard

import android.content.Context
import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.librarymanagement.databinding.ItemCallCardBinding
import com.example.librarymanagement.models.CallCard
import com.example.librarymanagement.ui.general.home.BookAdapter

class CallCardAdapter (val context: Context,private val list: List<CallCard>, val onItemClick: (CallCard) -> Unit)
    : RecyclerView.Adapter<CallCardAdapter.ViewHolder>() {
        inner class ViewHolder(val binding: ItemCallCardBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(position:Int) {
                val item = list[position]
                binding.tvUserName.text = item.user?.userName
                binding.tvUserEmail.text = item.user?.email
                binding.edtDate.text = strToEditable("${item.borrowDate} - ${item.dueDate}")
                binding.edtState.text = strToEditable(item.state.toString())
                binding.edtNote.text = strToEditable(item.note.toString())
                binding.root.setOnClickListener {
                    onItemClick.invoke(item)
                }
                binding.rcvBookCard.layoutManager= LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                binding.rcvBookCard.adapter = BookAdapter(context, item.books?.toMutableList() ?: mutableListOf()) {}
            }

            fun strToEditable(str: String): Editable {
                return Editable.Factory.getInstance().newEditable(str)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCallCardBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }
}