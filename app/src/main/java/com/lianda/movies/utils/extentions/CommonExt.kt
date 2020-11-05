package com.lianda.movies.utils.extentions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun emptyString() = ""

fun Int.toReadableMinutes(): String {
    val minutes = this
    return if (minutes < 60) {
        String.format("%d mins", minutes)
    } else if (minutes < 1440) {
        String.format("%d hrs, %d mins", minutes / 60, minutes % 60)
    } else {
        String.format("%d days", minutes / 1440)
    }
}

fun convertStringDateToAnotherStringDate(
    stringDate: String,
    stringDateFormat: String?,
    returnDateFormat: String?
): String {
    return try {
        val date: Date? = SimpleDateFormat(stringDateFormat, Locale.US).parse(stringDate)
        date?.let {
            SimpleDateFormat(returnDateFormat, Locale.US).format(it)
        }.orEmpty()
    } catch (e: ParseException) {
        e.printStackTrace()
        ""
    }
}