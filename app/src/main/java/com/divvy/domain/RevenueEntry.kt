package com.divvy.domain

import java.text.SimpleDateFormat
import java.util.*

private const val INCOMING_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
private const val LABEL_DATE_FORMAT = "MMM"

data class RevenueEntry(
    val seq: Int?,
    val date: String?,
    val value: Double?
) {
    fun getDateLabel() = getDate()?.let {
        val formatter = SimpleDateFormat(LABEL_DATE_FORMAT, Locale.US)
        formatter.format(it)
    }

    private fun getDate() = date?.let {
        val formatter = SimpleDateFormat(INCOMING_DATE_FORMAT, Locale.US)
        formatter.parse(it)
    }
}