package com.example.marvelproject.presentation.fragments.characterlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvelproject.R
import com.example.marvelproject.base.BaseExtraData
import com.example.marvelproject.base.BaseState
import com.example.marvelproject.databinding.FragmentCharacterListBinding


class CharacterListFragment : Fragment() {

    lateinit var binding : FragmentCharacterListBinding
    lateinit var mAdapter: CharacterListAdapter

    val viewModel: CharacterListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCharacterListBinding.inflate(layoutInflater, container, false)

        viewModel.getState().observe(viewLifecycleOwner, {state ->
            when(state){
                is BaseState.Normal ->{
                    onNormal(state.data as CharacterListState)
                }
                is BaseState.Error ->{
                    onError(state.dataError)
                }
                is BaseState.Loading ->{
                    onLoading(state.dataLoading)
                }
            }
        })

        setupView()
        viewModel.requestInformation()

        return binding.root
    }

    private fun setupView() {
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
                    viewModel.onActionChangeSpinnerValue(20.toString())
                else
                    viewModel.onActionChangeSpinnerValue(parent.getItemAtPosition(pos).toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }

    }

    private fun onLoading(dataLoading: BaseExtraData?) {

    }

    private fun onError(dataError: Throwable) {

    }

    private fun onNormal(characterListState: CharacterListState) {
        mAdapter.updateList(characterListState.characterList)
    }
}