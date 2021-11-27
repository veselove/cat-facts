package com.veselove.catfacts.ui.saved

import android.app.ProgressDialog.show
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.accessibility.AccessibilityEventCompat.setAction
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.veselove.catfacts.R
import com.veselove.catfacts.adapters.SavedCatFactsAdapter
import com.veselove.catfacts.databinding.FragmentSavedBinding

class SavedFragment : Fragment() {

    private val savedViewModel: SavedViewModel by activityViewModels()
    private var _binding: FragmentSavedBinding? = null
    lateinit var savedCatFactsAdapter: SavedCatFactsAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
   // val view = activity.findViewById(R.id.savedConstraintLayout)

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

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val catFact = savedCatFactsAdapter.differ.currentList[position]
                savedViewModel.deleteCatFact(catFact)
                activity?.let { Snackbar.make(it.findViewById(R.id.savedConstraintLayout), "Successfully deleted fact", Snackbar.LENGTH_LONG).show() }
                }
            }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.catFactRV)
        }

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