package com.testing.weatherapp.data.local.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.testing.weatherapp.util.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

interface PreferenceStorage {
    var lat: String
    var lon: String
}

@Singleton
class SharedPreferenceStorage @Inject constructor(@ApplicationContext context: Context): PreferenceStorage {
    private val preferences: Lazy<SharedPreferences> = lazy {
        context.applicationContext.getSharedPreferences("WEATHER_PREFERENCES", Context.MODE_PRIVATE)
    }

    override var lat by StringPreference(preferences, Constants.PREF_CITY_LAT, Constants.DEFAULT_CITY_LAT)
    override var lon by StringPreference(preferences, Constants.PREF_CITY_LON, Constants.DEFAULT_CITY_LON)
}

class StringPreference(
    private val preferences: Lazy<SharedPreferences>,
    private val name: String,
    private val defaultValue: String
): ReadWriteProperty<Any, String> {
    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return preferences.value.getString(name, defaultValue).toString()
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        preferences.value.edit { putString(name, value) }
    }
}