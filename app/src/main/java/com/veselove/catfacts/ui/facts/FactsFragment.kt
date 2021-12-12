package com.veselove.catfacts.ui.facts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.veselove.catfacts.R
import com.veselove.catfacts.databinding.FragmentFactsBinding
import kotlinx.coroutines.flow.collectLatest

class FactsFragment : Fragment() {

    private val factsViewModel: FactsViewModel by activityViewModels()
    private var _binding: FragmentFactsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFactsBinding.inflate(inflater, container, false)
        val root: View = binding.root

/*                                                              before Kotlin's Flow implementation

        factsViewModel.catFact.observe(viewLifecycleOwner, Observer {
            binding.tvCatFact.text = it.fact
        })
 */

        lifecycleScope.launchWhenStarted {
            factsViewModel.catFact.collectLatest {
                binding.tvCatFact.text = it.fact
            }
        }

        binding.btnNext.setOnClickListener { nextFact() }
        binding.btnSave.setOnClickListener { saveFact() }

        return root
    }

    private fun nextFact() {
        factsViewModel.getCatFact()
    }

    private fun saveFact() {
        factsViewModel.saveCatFact()
        Toast.makeText(activity, R.string.saved, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}