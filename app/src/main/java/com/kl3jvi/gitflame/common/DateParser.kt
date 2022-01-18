package com.kl3jvi.gitflame.common

import android.text.format.DateUtils
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DateParser{
    private val dateFormat: DateFormat

    init {
        dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
        dateFormat.timeZone = TimeZone.getDefault()
    }

    companion object {
        private val instance = DateParser()

        fun getTimeAgo(toParse: String?): CharSequence {
            try {
                val parsedDate = instance.dateFormat.parse(toParse)
                val now = System.currentTimeMillis()
                return DateUtils.getRelativeTimeSpanString(
                    parsedDate.time,
                    now,
                    DateUtils.SECOND_IN_MILLIS
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return "N/A"
        }

    }


}