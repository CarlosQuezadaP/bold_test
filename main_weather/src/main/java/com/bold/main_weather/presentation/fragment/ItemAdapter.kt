package com.bold.main_weather.presentation.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bold.main_weather.R
import com.bold.main_weather.data.repo.models.CitySearchModel
import com.bold.main_weather.databinding.ItemRecentSearchBinding
import com.bold.main_weather.presentation.CityClickListener

class ItemAdapter(private val itemList: List<CitySearchModel>, private val clickListener: CityClickListener) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: ItemRecentSearchBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemRecentSearchBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_recent_search, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemList[position]
        holder.binding.city = item
        holder.binding.clickListener = clickListener
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = itemList.size
}