package com.veselove.catfacts.ui.facts

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
import com.veselove.catfacts.databinding.FragmentFactsBinding

class FactsFragment : Fragment() {

    private val factsViewModel: FactsViewModel by activityViewModels()
    private var _binding: FragmentFactsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        factsViewModel =
//            ViewModelProvider(this).get(FactsViewModel::class.java)

        _binding = FragmentFactsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        factsViewModel.catFact.observe(viewLifecycleOwner, Observer {
            binding.tvCatFact.text = it.fact
        })

        binding.btnNext.setOnClickListener { nextFact() }
        binding.btnSave.setOnClickListener { saveFact() }

        return root
    }

    private fun nextFact() {
        factsViewModel.getCatFact()
    }

    private fun saveFact() {
        factsViewModel.saveCatFact()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}