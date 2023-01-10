package com.divvy.domain

import androidx.lifecycle.MutableLiveData
import com.jaikeerthick.composable_graphs.data.GraphData

private const val REVENUE_FORMAT = "(%s)"
private const val NAME_REVENUE_SEPARATOR = " "

data class Business(
    val id: Int?,
    val name: String?,
    val location: BusinessLocation?,
    // Note that this revenue will be ordered from closest to furthest date
    val revenue: List<RevenueEntry>?
) {
    // Total revenue for the range the user selects in the RangeSlider
    var totalRevenue = MutableLiveData<Double>(null)

    // We may want this in strings.xml
    fun getOverviewName() = listOfNotNull(name, totalRevenue.value?.let {
        REVENUE_FORMAT.format(RevenueDisplay(it).getFormatted())
    }).joinToString(NAME_REVENUE_SEPARATOR)

    fun getXAxis() = revenue?.reversed()?.mapNotNull {
        it.getDateLabel()?.let {
            GraphData.String(it)
        }
    }.orEmpty()

    fun getYAxis() = revenue?.reversed()?.mapNotNull { it.value }.orEmpty()
}