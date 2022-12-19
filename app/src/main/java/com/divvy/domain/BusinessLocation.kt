package com.divvy.domain

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng

data class BusinessLocation(
    val address: String?,
    val city: String?,
    val country: String?
) {
    fun fullAddress() = listOfNotNull(address, city, country).joinToString(", ")

    var distanceToUser = MutableLiveData<Distance?>(null)

    fun setDistanceToUser(user: LatLng, businessLatLng: LatLng) {
        // TODO: explain why this is necessary
        if (distanceToUser == null)
            distanceToUser = MutableLiveData<Distance?>(null)
        val distance = FloatArray(1)
        android.location.Location.distanceBetween(user.latitude, user.longitude, businessLatLng.latitude, businessLatLng.longitude, distance)
        distanceToUser.value = Distance(distance.getOrNull(0))
    }
}