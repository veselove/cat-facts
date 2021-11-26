package com.veselove.catfacts.ui.saved

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.veselove.catfacts.adapters.SavedCatFactsAdapter
import com.veselove.catfacts.databinding.FragmentSavedBinding
import com.veselove.catfacts.models.CatFact

class SavedFragment : Fragment() {

    private val savedViewModel: SavedViewModel by activityViewModels()
    private var _binding: FragmentSavedBinding? = null
    lateinit var savedCatFactsAdapter: SavedCatFactsAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSavedBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupRecyclerView()

        val linearLayoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.catFactRV.layoutManager = linearLayoutManager

        savedViewModel.getSavedCatFacts().observe(viewLifecycleOwner, Observer{ catFacts ->
            savedCatFactsAdapter.differ.submitList(catFacts)
        })

        return root
    }

    private fun setupRecyclerView() {
        savedCatFactsAdapter = SavedCatFactsAdapter()
        binding.catFactRV.apply {
            adapter = savedCatFactsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}