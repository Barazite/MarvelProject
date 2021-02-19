package com.example.marvelproject.presentation.fragments.characterdetail.curriculum

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelproject.data.model.Item
import com.example.marvelproject.databinding.FragmentCurriculumBinding

class CurriculumFragment(val items: List<Item>) : Fragment() {

    lateinit var binding: FragmentCurriculumBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCurriculumBinding.inflate(inflater,container, false)

        setupView()

        return binding.root
    }

    private fun setupView() {
        val listToShow: MutableList<String> = mutableListOf()
        items.forEach{listToShow.add(it.name)}

        binding.rvFragmentCurriculum.apply{
            adapter = CurriculumAdapter(listToShow)
            itemAnimator = DefaultItemAnimator()
            layoutManager = LinearLayoutManager(requireActivity())
        }

    }

}