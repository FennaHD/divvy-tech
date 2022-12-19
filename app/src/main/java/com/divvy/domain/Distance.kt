package com.divvy.domain

data class Distance(
    val meters: Float?
) {
    fun getMilesText() = getMiles()?.let {
        "$it miles away"
    }

    private fun getMiles() = meters?.let {
        (it/1609).toInt()
    }
}