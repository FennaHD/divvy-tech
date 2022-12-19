package com.divvy.domain

import com.jaikeerthick.composable_graphs.data.GraphData

data class Business(
    val id: Int?,
    val name: String?,
    val location: BusinessLocation?,
    val revenue: List<RevenueEntry>?
) {
    fun getXAxis() = revenue?.mapNotNull {
        it.getDateLabel()?.let {
            GraphData.String(it)
        }
    }.orEmpty()

    fun getYAxis() = revenue?.mapNotNull { it.value }.orEmpty()
}