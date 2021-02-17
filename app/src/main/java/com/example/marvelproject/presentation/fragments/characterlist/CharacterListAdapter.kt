package com.example.marvelproject.presentation.fragments.characterlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelproject.R
import com.example.marvelproject.data.model.Character
import com.example.marvelproject.databinding.ItemCharacterListBinding

class CharacterListAdapter (private var myList: List<Character>, private val context: Context, private val listener: (item: Character) -> Unit) : RecyclerView.Adapter<CharacterListAdapter.ViewHolder>() {

        class ViewHolder(val binding: ItemCharacterListBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListAdapter.ViewHolder {
            return ViewHolder(ItemCharacterListBinding.inflate(LayoutInflater.from(parent.context),parent, false))
        }

        override fun onBindViewHolder(holder:CharacterListAdapter.ViewHolder, position: Int) {
            val characterItem = myList[position]

            holder.binding.apply{
                tvCharacterList.text = characterItem.name

                val url = characterItem.thumbnail.path.replace("http", "https") + "." + characterItem.thumbnail.extension

                Glide.with(context)
                    .load(url)
                    .centerCrop()
                    .placeholder(R.drawable.ic_placeholder)
                    .into(ivCharacterList)
            }

            holder.itemView.setOnClickListener{
                listener.invoke(characterItem)
            }
        }

        override fun getItemCount() = myList.size

        fun updateList(newList: List<Character>){
            myList = newList
            notifyDataSetChanged()
        }
}