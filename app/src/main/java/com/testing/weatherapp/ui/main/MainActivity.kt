package com.testing.weatherapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.testing.weatherapp.R
import com.testing.weatherapp.api.adapters.model.TimeWeather
import com.testing.weatherapp.api.adapters.TimeWeatherAdapter
import com.testing.weatherapp.api.adapters.WeekWeatherAdapter
import com.testing.weatherapp.data.local.database.model.WeatherEntity
import com.testing.weatherapp.databinding.ActivityMainBinding
import com.testing.weatherapp.ui.cityList.CityListActivity
import com.testing.weatherapp.util.ImageLoader
import com.testing.weatherapp.util.InterpretationValues
import com.testing.weatherapp.util.dateFormat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Calendar

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    private var timeWeatherAdapter = TimeWeatherAdapter()
    private var weekWeatherAdapter = WeekWeatherAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initializeViews()
        observeViewModel()
        viewModel.loadWeatherFromApi()
    }

    private fun observeViewModel() {
        viewModel.mainWeather.observe(this) {
            it?.let { valuesForViews(it) }
        }
    }

    private fun initializeViews() {
        weatherTimeRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = timeWeatherAdapter
        }

        weatherWeekRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = weekWeatherAdapter
        }

        changeLocationImageView.setOnClickListener {
            startActivity(Intent(this, CityListActivity::class.java))
            finish()
        }
    }

    private fun valuesForViews(weatherEntity: WeatherEntity) {
        cityNameTextView.text = weatherEntity.name
        if (!weatherEntity.icon.isNullOrEmpty()) {
            ImageLoader.loadImage(weatherIconImageView, weatherEntity.icon)
        }
            weatherTempTextView.text = weatherEntity.temp.toString()
            weatherConditionTextView.text = InterpretationValues.weatherCondition(weatherEntity.condition ?: "overcast")
            weatherFeelsLikeTextView.text = weatherEntity.feelsLike.toString()
            weatherWindTextView.text = weatherEntity.windSpeed.toString()
            weatherWindDirTextView.text = InterpretationValues.windDirection(weatherEntity.windDir ?: "n")
            weatherPressureTextView.text = weatherEntity.pressureMm.toString()
            weatherHumidityTextView.text = weatherEntity.humidity.toString()

        weatherEntity.forecasts?.get(0).let {
            val calendarHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
            val timeWeatherList = mutableListOf<TimeWeather>()
            it?.hours?.filter {
                item -> (item.hour?.toInt() ?: 0) > calendarHour }
                ?.map {
                    val timeWeather = TimeWeather(
                        it.hour ?: "0",
                        it.icon ?: "bkn_n",
                        it.temp.toString()
                    )
                    timeWeatherList.add(timeWeather)
                }
            if (timeWeatherList.isNotEmpty()) {
                timeWeatherAdapter.updateList(timeWeatherList)
            }
        }

        val weekWeatherList = mutableListOf<TimeWeather>()
        weatherEntity.forecasts.let {
            for (i in 0..6) {
                val weekWeather = TimeWeather(
                    (it?.get(i)?.dateTs).dateFormat(),
                    it?.get(i)?.parts?.dayShort?.icon ?: "bkn_n",
                    it?.get(i)?.parts?.dayShort?.temp.toString()
                )
                weekWeatherList.add(weekWeather)
            }
        }
        if (weekWeatherList.isNotEmpty()) {
            weekWeatherAdapter.updateList(weekWeatherList)
        }
    }
}