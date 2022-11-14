package com.testing.weatherapp.util

import android.widget.ImageView
import androidx.core.net.toUri
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.testing.weatherapp.R

object ImageLoader {
    fun loadImage(view: ImageView, icon: String, placeholder: Int = R.drawable.default_weather_icon) {
        val url = Constants.IMAGE_URL.plus(icon).plus(Constants.IMAGE_FORMAT).toUri()
        GlideToVectorYou.init()
            .with(view.context)
            .setPlaceHolder(placeholder, placeholder)
            .load(url, view)
    }
}