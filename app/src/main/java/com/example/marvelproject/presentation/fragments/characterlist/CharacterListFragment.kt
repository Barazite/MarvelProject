package com.example.marvelproject.presentation.fragments.characterlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvelproject.R
import com.example.marvelproject.base.BaseExtraData
import com.example.marvelproject.base.BaseFragment
import com.example.marvelproject.base.BaseState
import com.example.marvelproject.base.BaseViewState
import com.example.marvelproject.databinding.FragmentCharacterListBinding


class CharacterListFragment : BaseFragment<CharacterListState, CharacterListViewModel, FragmentCharacterListBinding>() {

    /**
     * Base classes variables
     */
    override val viewModelClass = CharacterListViewModel::class.java
    lateinit var vm: CharacterListViewModel
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCharacterListBinding = FragmentCharacterListBinding::inflate
    lateinit var mAdapter: CharacterListAdapter

    /**
     * Base class methods
     */

    override fun setupView(viewModel: CharacterListViewModel) {
         vm = viewModel

        //Setup Recycler View
        mAdapter = CharacterListAdapter(listOf(), requireActivity()){ character ->
            findNavController().navigate(CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(character.id))
        }
        binding.rvFragmentListCharacter.apply{
            adapter = mAdapter
            layoutManager = GridLayoutManager(requireActivity(), 2)
            itemAnimator = DefaultItemAnimator()
        }

        //Setup Spinner
        ArrayAdapter.createFromResource(requireActivity(), R.array.fragment_character_list_spinner_array, android.R.layout.simple_spinner_item).also { spinneradapter ->
            // Specify the layout to use when the list of choices appears
            spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.spFragmentListCharacter.adapter = spinneradapter
        }

        binding.spFragmentListCharacter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                if(pos == 0)
                    vm.onActionChangeSpinnerValue(20.toString())
                else
                    vm.onActionChangeSpinnerValue(parent.getItemAtPosition(pos).toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }

    }

    /**
     * State management functions
     */

    override fun onNormal(data: CharacterListState) {
        mAdapter.updateList(data.characterList)
        val list = resources.getStringArray(R.array.fragment_character_list_spinner_array)
        binding.spFragmentListCharacter.setSelection(list.indexOf(data.limit.toString()))
        //binding.rvFragmentListCharacter.layoutManager?.scrollToPosition(position) Volvemos al elemento que pulsamos cuando volvemos a la lista
    }
    override fun onLoading(dataLoading: BaseExtraData?) {}
    override fun onError(dataError: Throwable) {}


}