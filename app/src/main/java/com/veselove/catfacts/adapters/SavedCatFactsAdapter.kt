package com.veselove.catfacts.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.veselove.catfacts.R
import com.veselove.catfacts.models.CatFact

class SavedCatFactsAdapter :
RecyclerView.Adapter<SavedCatFactsAdapter.SavedCatFactsHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<CatFact>() {
        override fun areItemsTheSame(oldItem: CatFact, newItem: CatFact): Boolean {
            return oldItem.length == newItem.length
        }
        override fun areContentsTheSame(oldItem: CatFact, newItem: CatFact): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    class SavedCatFactsHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvRVCatFact: TextView = view.findViewById(R.id.tvRVCatFact)

        fun setCatFactData(fact: CatFact) {
            tvRVCatFact.text = fact.fact
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedCatFactsHolder {
        return SavedCatFactsHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_rv_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SavedCatFactsHolder, position: Int) {
        return holder.setCatFactData(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}