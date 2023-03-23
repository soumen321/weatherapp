package com.weatherapp.data.pref

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppPrefs @Inject constructor(
    @ApplicationContext private val context: Context
) : PrefsHelper {

    private val sharedPreferences by lazy {
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    override fun saveLastCity(cityName: String) {
        sharedPreferences.edit { putString(LAST_CITY_TAG, cityName) }
    }

    override fun getLastCity(): String {
        return sharedPreferences.getString(LAST_CITY_TAG, null) ?: DEFAULT_CITY_NAME
    }

    companion object {
        private const val LAST_CITY_TAG = "country"
        private const val DEFAULT_CITY_NAME = ""
    }
}
