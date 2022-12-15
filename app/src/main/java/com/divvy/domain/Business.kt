package com.divvy.domain

data class Business(
    val id: Int?,
    val name: String?,
    val location: BusinessLocation?,
    val revenue: List<RevenueEntry>?
)