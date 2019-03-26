package com.example.agh.wheatherapp.features.home.adapter

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.agh.wheatherapp.R
import com.example.entities.City
import java.io.Serializable


const val ACTION_SHOW_CITY_BUTTON_CLICKED = "ACTION_SHOW_CITY_BUTTON_CLICKED"
const val EXTRA_CITY = "EXTRA_CITY"

class CitySearchResultViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    // using findView... instead of synthetic because it can cause some problems in adapter
    private val cityName by lazy { view.findViewById<TextView>(R.id.city_result_name_text_view) }
    private val showButton by lazy { view.findViewById<Button>(R.id.show_city_weather_button) }

    fun bind(city: City) {
        cityName.text = city.name ?: ""
        showButton.setOnClickListener {
            Intent(ACTION_SHOW_CITY_BUTTON_CLICKED)
                    .putExtra(EXTRA_CITY, city as Serializable)
                    .also { view.context.sendBroadcast(it) }
        }

    }

}

class CitySearchResultsAdapter(
        lifecycleOwner: LifecycleOwner,
        private val citiesResult: MutableLiveData<List<City>>
) : RecyclerView.Adapter<CitySearchResultViewHolder>() {

    init {
        // using lifeCycleOwner instead of context
        citiesResult.observe(lifecycleOwner, Observer {
            notifyDataSetChanged()
        })
    }

    override fun onCreateViewHolder(parentView: ViewGroup, p1: Int): CitySearchResultViewHolder {
        return LayoutInflater
                .from(parentView.context)
                .inflate(R.layout.recycler_item_search_result, parentView, false)
                .let { CitySearchResultViewHolder(it) }
    }

    override fun onBindViewHolder(viewHolder: CitySearchResultViewHolder, position: Int) {
        viewHolder.bind(citiesResult.value!![position])
    }

    override fun getItemCount() = citiesResult.value?.size ?: 0

}