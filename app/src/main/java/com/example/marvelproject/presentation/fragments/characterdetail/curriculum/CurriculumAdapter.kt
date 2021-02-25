package com.example.marvelproject.presentation.fragments.characterdetail.curriculum

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelproject.databinding.ItemCurriculumBinding

class CurriculumAdapter (private var myList: List<String>, private val showButton: Boolean = false, private val myListener: (comic: String) -> Unit) : RecyclerView.Adapter<CurriculumAdapter.ViewHolder>() {

        class ViewHolder(val binding: ItemCurriculumBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurriculumAdapter.ViewHolder {
            return ViewHolder(ItemCurriculumBinding.inflate(LayoutInflater.from(parent.context),parent, false))
        }

        override fun onBindViewHolder(holder:CurriculumAdapter.ViewHolder, position: Int) {
            val item = myList[position]

            holder.binding.apply {
                tvCharacterCurriculum.text = item
                btCharacterCurriculum.visibility = if (showButton) View.VISIBLE else View.GONE
                btCharacterCurriculum.setOnClickListener{
                    myListener.invoke(item)
                }
            }
        }

        override fun getItemCount() = myList.size

}