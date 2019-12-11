package com.ezequieldisisto.devigettest.util

import android.content.Context
import com.ezequieldisisto.devigettest.R
import java.util.*

class Utils {

    companion object {

        fun getTimeAgo(context: Context, postedTime: Long): String {

            val diff = Calendar.getInstance().timeInMillis - (postedTime * Constants.SECOND_MILLIS)

            return when {
                diff < Constants.MINUTE_MILLIS -> context.getString(R.string.post_moment_ago)
                diff < 2 * Constants.MINUTE_MILLIS -> context.getString(R.string.post_a_minute_ago)
                diff < 60 * Constants.MINUTE_MILLIS -> "${diff / Constants.MINUTE_MILLIS} ${context.getString(R.string.post_minutes_ago)}"
                diff < 2 * Constants.HOUR_MILLIS -> context.getString(R.string.post_an_hour_ago)
                diff < 24 * Constants.HOUR_MILLIS -> "${diff / Constants.HOUR_MILLIS} ${context.getString(R.string.post_hours_ago)}"
                diff < 48 * Constants.HOUR_MILLIS -> context.getString(R.string.post_yesterday)
                else -> "${diff / Constants.DAY_MILLIS} ${context.getString(R.string.post_days_ago)}"
            }
        }
    }
}