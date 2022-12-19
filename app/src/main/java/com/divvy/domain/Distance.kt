package com.divvy.domain

/**
 * Simple class to manage the result returned from Location.distanceBetween
 */

private const val METERS_IN_MILE = 1609

data class Distance(
    val meters: Float?
) {
    fun getMilesText(format: String) = getMiles()?.let {
        format.format(it)
    }

    private fun getMiles() = meters?.let {
        (it/METERS_IN_MILE).toInt()
    }
}