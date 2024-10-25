package com.bold.main_weather.presentation.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bold.main_weather.R
import com.bold.main_weather.data.api.dto.nextDays.ForecastdayModel
import com.bold.main_weather.databinding.DayItemBinding

class ForecastAdapter : ListAdapter<ForecastdayModel, ForecastAdapter.ForecastViewHolder>(ForecastDiffCallback()) {

    class ForecastViewHolder(val binding: DayItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: DayItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.day_item, parent, false)
        return ForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            val newData = item.copy(isSelected = true)
            modifyItem(position, newData)
        }
        holder.binding.forecast = item
        holder.binding.executePendingBindings()
        holder.itemView.isClickable = !item.isSelected

    }

    class ForecastDiffCallback : DiffUtil.ItemCallback<ForecastdayModel>() {
        override fun areItemsTheSame(oldItem: ForecastdayModel, newItem: ForecastdayModel): Boolean {
            return oldItem.date == newItem.date
        }

        override fun areContentsTheSame(oldItem: ForecastdayModel, newItem: ForecastdayModel): Boolean {
            return oldItem == newItem
        }
    }

    private fun modifyItem(index: Int, modifiedItem: ForecastdayModel) {
        val newList = currentList.map {
            it.copy(isSelected = false)
        }.toMutableList()
        newList[index] = modifiedItem
        submitList(newList)
    }


}