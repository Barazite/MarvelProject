package com.example.marvelproject.presentation.fragments.characterdetail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.marvelproject.data.model.Character
import com.example.marvelproject.presentation.fragments.characterdetail.curriculum.CurriculumFragment

class CharacterDetailViewPagerAdapter(fragment: Fragment, val character: Character, private val myListener: (comic: Int) -> Unit) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 3 //Paginas que tendra el pager

    override fun createFragment(position: Int): Fragment {
       return when(position){
           0 -> CurriculumFragment(character.comics.items, true){ nameSelected ->
               val listOfComics = character.comics.items
               val comicSelected = listOfComics.firstOrNull{ it.name == nameSelected}
               val uri = comicSelected?.resourceURI
               val id = uri?.subSequence(uri.lastIndexOf("/") + 1, uri.length).toString()
               myListener.invoke(if(id.isNotEmpty()) id.toInt() else -1)
           }
           1 -> CurriculumFragment(character.series.items, false){}
           2 -> CurriculumFragment(character.stories.items, false){}
           else -> Fragment()
       }
    }
}