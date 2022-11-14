package com.testing.weatherapp.api.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.testing.weatherapp.R
import com.testing.weatherapp.data.local.database.model.CityEntity
import com.testing.weatherapp.util.Constants
import kotlinx.android.synthetic.main.city_list_item.view.*

interface OnItemClickCallback {
    fun onItemClick(lat: String, lon: String)
    fun onDeleteIconClick(name: String)
}

class CityListAdapter(private val onItemClickCallback: OnItemClickCallback) : RecyclerView.Adapter<CityListAdapter.CityListViewHolder>(){
    private val cityList = mutableListOf<CityEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityListViewHolder {
        return CityListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.city_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CityListViewHolder, position: Int) {
        holder.bind(cityList[position], onItemClickCallback)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    fun updateList(list: List<CityEntity>) {
        this.cityList.clear()
        this.cityList.addAll(list)
        notifyDataSetChanged()
    }

    class CityListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(model: CityEntity, onItemClickCallback: OnItemClickCallback) {

            itemView.cityNameTextView.text = model.name

            itemView.citeDeleteIconImageView.setOnClickListener {
                onItemClickCallback.onDeleteIconClick(model.name)
            }

            itemView.setOnClickListener {
                onItemClickCallback.onItemClick(model.lat ?: Constants.DEFAULT_CITY_LAT, model.lon ?: Constants.DEFAULT_CITY_LON)
            }
        }
    }
}