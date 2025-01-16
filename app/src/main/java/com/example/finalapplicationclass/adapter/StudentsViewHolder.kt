package com.example.finalapplicationclass.adapter

import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.finalapplicationclass.model.Student
import android.util.Log
import com.example.finalapplicationclass.OnItemClickListener
import com.example.finalapplicationclass.databinding.StudentListRowBinding
import com.squareup.picasso.Picasso
import com.example.finalapplicationclass.R


class StudentViewHolder(
    private val binding: StudentListRowBinding,
    listener: OnItemClickListener?
): RecyclerView.ViewHolder(binding.root) {

    private var student: Student? = null

    init {
        binding.checkBox.apply {
            setOnClickListener { view ->
                (tag as? Int)?.let { tag ->
                    student?.isChecked = (view as? CheckBox)?.isChecked ?: false
                }
            }
        }
        itemView.setOnClickListener {
            Log.d("TAG", "On click listener on position $adapterPosition")
            listener?.onItemClick(student)
        }
    }

    fun bind(student: Student?, position: Int) {
        this.student = student
        binding.nameTextView.text = student?.name
        binding.idTextView.text = student?.id
        binding.checkBox.apply {
            isChecked = student?.isChecked ?: false
            tag = position
        }

        student?.avatarUrl?.let { avatarUrl ->
            val url = avatarUrl.ifBlank { return }
            Picasso.get()
                .load(url)
                .placeholder(R.drawable.hacker)
                .into(binding.imageView)
        }
    }
}