package com.voltaire.listadecompras.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.voltaire.listadecompras.database.models.Item
import com.voltaire.listadecompras.databinding.RecyclerViewItemsBinding

class InnerListAdapter(
    var excludeItem: (item : Item) -> Unit = {},
    var selectedItem: (item : Item) -> Unit = {}
    ) : RecyclerView.Adapter<InnerListAdapter.InnerListViewHolder>() {

    private var itemsList: List<Item> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerListViewHolder {
        val binding =
            RecyclerViewItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InnerListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InnerListViewHolder, position: Int) {
        holder.bind(itemsList[position])
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    fun updateItems(itemLists: List<Item>) {
        itemsList = itemLists
        notifyItemChanged(itemsList.size)
    }

    fun removeAt(position: Int) {
        excludeItem(itemsList[position])
        notifyItemRemoved(position)
    }

    inner class InnerListViewHolder(private val binding: RecyclerViewItemsBinding) :
        RecyclerView.ViewHolder(binding.root)
    {
        fun bind(marketListWithItems: Item) {
            binding.itemName.text = marketListWithItems.itemName
            binding.txtPrice.text = marketListWithItems.price
            binding.txtAmount.text = marketListWithItems.amount
        }
    }
}