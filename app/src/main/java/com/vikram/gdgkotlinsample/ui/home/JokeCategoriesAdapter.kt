package com.vikram.gdgkotlinsample.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vikram.gdgkotlinsample.R

class JokeCategoriesAdapter(private val interactionListener: InteractionListener? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return JokeCategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_joke_category,
                parent,
                false
            ),
            interactionListener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is JokeCategoryViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<String>) {
        differ.submitList(list)
    }

    class JokeCategoryViewHolder
    constructor(
        itemView: View,
        private val interactionListener: InteractionListener?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: String) = with(itemView) {
            itemView.setOnClickListener {
                interactionListener?.onCategorySelected(adapterPosition, item)
            }

            (itemView.findViewById(R.id.txtCategory) as TextView).text = item
        }
    }

    interface InteractionListener {
        fun onCategorySelected(position: Int, category: String)
    }
}