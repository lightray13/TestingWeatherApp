package com.testing.weatherapp.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun Long?.dateFormat(): String {
    return this?.let {
        val date = Date(it * 1000)
        val dateFormat = SimpleDateFormat("EEE, dd MMM")
        dateFormat.format(date)
    } ?: ""
}