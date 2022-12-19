package com.divvy.domain

/**
 * Simple class to handle the formatting of revenue when the user clicks a point in the chart.
 */
data class RevenueDisplay(
    val revenue: Double?
) {
    fun getFormatted() = "$%,.2f".format(revenue)
}