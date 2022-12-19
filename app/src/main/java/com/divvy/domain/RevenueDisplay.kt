package com.divvy.domain

data class RevenueDisplay(
    val revenue: Double?
) {
    fun getFormatted() = "$%,.2f".format(revenue)
}