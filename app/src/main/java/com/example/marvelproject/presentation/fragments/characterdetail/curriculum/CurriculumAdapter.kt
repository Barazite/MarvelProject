package com.example.marvelproject.presentation.fragments.characterdetail.curriculum

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelproject.R
import com.example.marvelproject.data.model.Character
import com.example.marvelproject.data.model.Item
import com.example.marvelproject.databinding.ItemCharacterListBinding
import com.example.marvelproject.databinding.ItemCurriculumBinding

class CurriculumAdapter (private var myList: List<String>) : RecyclerView.Adapter<CurriculumAdapter.ViewHolder>() {

        class ViewHolder(val binding: ItemCurriculumBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurriculumAdapter.ViewHolder {
            return ViewHolder(ItemCurriculumBinding.inflate(LayoutInflater.from(parent.context),parent, false))
        }

        override fun onBindViewHolder(holder:CurriculumAdapter.ViewHolder, position: Int) {
            val Item = myList[position]

            holder.binding.apply {
                tvCharacterCurriculum.text = Item
            }
        }

        override fun getItemCount() = myList.size

}