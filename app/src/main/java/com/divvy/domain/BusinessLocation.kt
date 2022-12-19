package com.divvy.domain

import android.location.Location
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
        // Even though Android Studio says distanceToUser can't be null, there's a rance condition
        // that we must avoid, in which the method is called before distanceToUser has a chance to
        // be initialized.
        if (distanceToUser == null)
            distanceToUser = MutableLiveData<Distance?>(null)
        val distance = FloatArray(1)
        // returns the distance in meters, we can format it later
        Location.distanceBetween(user.latitude, user.longitude, businessLatLng.latitude, businessLatLng.longitude, distance)
        distanceToUser.value = Distance(distance.getOrNull(0))
    }
}