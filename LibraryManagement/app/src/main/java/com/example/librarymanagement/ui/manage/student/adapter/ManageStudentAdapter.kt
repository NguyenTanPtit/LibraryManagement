package com.example.librarymanagement.ui.manage.student.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.librarymanagement.databinding.ItemManageStudentBinding
import com.example.librarymanagement.models.User

class ManageStudentAdapter(
    private val list: List<User>,
    val onDelete: (User) -> Unit,
    val onClick: (User) -> Unit): RecyclerView.Adapter<ManageStudentAdapter.ViewHolder>() {

        inner class ViewHolder(val binding: ItemManageStudentBinding): RecyclerView.ViewHolder(binding.root){
            fun bind(position: Int){
                val student = list[position]
                binding.studentName.text = student.userName
                binding.deleteStudent.setOnClickListener {
                    onDelete.invoke(student)
                }
                binding.root.setOnClickListener {
                    onClick.invoke(student)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemManageStudentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         holder.bind(position)
    }
}