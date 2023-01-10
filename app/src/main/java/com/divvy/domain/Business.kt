package com.divvy.domain

import androidx.lifecycle.MutableLiveData
import com.jaikeerthick.composable_graphs.data.GraphData

data class Business(
    val id: Int?,
    val name: String?,
    val location: BusinessLocation?,
    // Note that this revenue will be ordered from closest to furthest date
    val revenue: List<RevenueEntry>?
) {
    // Total revenue for the range the user selects in the RangeSlider
    val totalRevenue = MutableLiveData<Double>(null)

    fun getXAxis() = revenue?.reversed()?.mapNotNull {
        it.getDateLabel()?.let {
            GraphData.String(it)
        }
    }.orEmpty()

    fun getYAxis() = revenue?.reversed()?.mapNotNull { it.value }.orEmpty()
}