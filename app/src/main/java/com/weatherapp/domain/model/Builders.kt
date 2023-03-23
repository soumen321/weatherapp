package com.weatherapp.domain.model

import com.weatherapp.utility.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import java.text.SimpleDateFormat
import java.util.*


fun Long.toDateTimeString(format: String, zone: TimeZone? = null): String {
    val date = Date().apply { time = this@toDateTimeString }
    return SimpleDateFormat(format, Locale.ENGLISH)
        .apply { zone?.let { timeZone = it } }
        .format(date)
}

fun Long.toDateTimeString(format: String, zoneId: Int? = null): String {
    val date = Date().apply { time = this@toDateTimeString }
    return try {
        SimpleDateFormat(format, Locale.ENGLISH)
            .apply { timeZone = TimeZone.getDefault().apply { zoneId?.let { rawOffset = it } } }
            .format(date)
    } catch (e: Exception) {
        SimpleDateFormat(Constants.DateFormat.DEFAULT_FORMAT, Locale.ENGLISH)
            .apply { timeZone = TimeZone.getDefault().apply { zoneId?.let { rawOffset = it } } }
            .format(date)
    }
}

fun String.toCountryName(): String {
    return Locale("", this).displayName
}
fun convertTemp(temperature: String) : String {

    val temp =((((temperature).toFloat()-273.15)).toInt()).toString()

    return temp
}
