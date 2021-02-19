package com.example.marvelproject.presentation.fragments.characterdetail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.marvelproject.data.model.Character
import com.example.marvelproject.presentation.fragments.characterdetail.curriculum.CurriculumFragment

class CharacterDetailViewPagerAdapter(fragment: Fragment, val character: Character) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 3 //Paginas que tendra el pager

    override fun createFragment(position: Int): Fragment {
       return when(position){
           0 -> CurriculumFragment(character.comics.items)
           1 -> CurriculumFragment(character.series.items)
           2 -> CurriculumFragment(character.stories.items)
           else -> Fragment()
       }
    }
}