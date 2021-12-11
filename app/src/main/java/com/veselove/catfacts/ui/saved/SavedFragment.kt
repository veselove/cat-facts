package com.veselove.catfacts.ui.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.veselove.catfacts.R
import com.veselove.catfacts.adapters.SavedCatFactsAdapter
import com.veselove.catfacts.databinding.FragmentSavedBinding
import kotlinx.coroutines.flow.collectLatest

class SavedFragment : Fragment() {

    private val savedViewModel: SavedViewModel by activityViewModels()
    private var _binding: FragmentSavedBinding? = null
    lateinit var savedCatFactsAdapter: SavedCatFactsAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSavedBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupRecyclerView()

        val linearLayoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.catFactRV.layoutManager = linearLayoutManager

//        savedViewModel.getSavedCatFacts().observe(viewLifecycleOwner, Observer { catFacts ->
//            savedCatFactsAdapter.differ.submitList(catFacts)
//        })

        lifecycleScope.launchWhenStarted {
            savedViewModel.getSavedCatFacts.collectLatest {
                savedCatFactsAdapter.differ.submitList(it)
            }
        }

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
                activity?.let {
                    Snackbar.make(
                        it.findViewById(R.id.savedConstraintLayout),
                        R.string.successfully_removed,
                        Snackbar.LENGTH_LONG
                    ).apply {
                        setAction(R.string.undo) {
                            savedViewModel.saveCatFact(catFact)
                        }
                        show()
                    }
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.catFactRV)
        }

        return root
    }

    private fun setupRecyclerView() {
        savedCatFactsAdapter = SavedCatFactsAdapter()
        val itemDecoration = DividerItemDecoration(requireActivity(), VERTICAL)
        itemDecoration.setDrawable(ContextCompat.getDrawable(requireActivity(),R.drawable.rv_divider)!!)
        binding.catFactRV.apply {
            adapter = savedCatFactsAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(itemDecoration)
        }
    }

}