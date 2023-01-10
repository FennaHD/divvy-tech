package com.divvy.domain

/**
 * Simple class to handle the formatting of revenue when the user clicks a point in the chart.
 */

private const val FORMAT = "$%,.2f"

data class RevenueDisplay(
    val revenue: Double?
) {
    fun getFormatted() = FORMAT.format(revenue)
}