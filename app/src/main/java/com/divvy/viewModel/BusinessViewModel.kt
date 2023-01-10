package com.divvy.viewModel

import android.location.Geocoder
import androidx.core.util.toRange
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.divvy.domain.Business
import com.divvy.domain.RevenueDisplay
import com.divvy.service.BusinessRepository
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch

private const val SOUTH_JORDAN_LAT = 40.550906
private const val SOUTH_JORDAN_LNG = -111.943054

class BusinessViewModel(private val geocoder: Geocoder): ViewModel() {

    val businesses = MutableLiveData<List<Business>>(null)
    val selectedBusiness = MutableLiveData<Business>(null)
    val selectedRevenue = MutableLiveData<RevenueDisplay>(null)

    val selectedRange = MutableLiveData(1f..6f)

    fun retrieveBusinesses() {
        viewModelScope.launch {
            BusinessRepository().client.getBusinesses()?.let {
                businesses.value = it.also {
                    it.forEach {
                        // Compose doesn't play well with live data, so we have to make sure to set it manually
                        if (it.totalRevenue == null)
                            it.totalRevenue = MutableLiveData(null)
                    }
                }
            }
        }
    }

    fun retrieveLocation(business: Business) {
        // We really only need to set it once, no need to do it everytime we go to details
        if (business.location?.distanceToUser?.value == null) {
            val user = LatLng(SOUTH_JORDAN_LAT, SOUTH_JORDAN_LNG)
            business.location?.fullAddress()?.let {
                // This method can return several results, but we limit it to 1 and retrieve it if it exists
                geocoder.getFromLocationName(it, 1).getOrNull(0)?.let {
                    business.location.setDistanceToUser(user, LatLng(it.latitude, it.longitude))
                }
            }
        }
    }

    //
    fun setTotalRevenue(range: ClosedFloatingPointRange<Float>?) {
        businesses.value?.forEach { business ->
            business.totalRevenue.value = range?.let {
                // We gotta offset by 1 since the range is 1..6, but indexes are 0..5
                business.revenue?.slice(IntRange(it.start.toInt()-1, it.endInclusive.toInt()-1))?.map { it.value }?.reduce { revenue1, revenue2 ->
                    revenue1?.let {
                        revenue2?.let {
                            revenue1 + revenue2
                        }
                    }
                }
            }
        }
    }

    fun sortBusinessesAlphabetically() {
        businesses.value = businesses.value?.sortedBy { it.name }
    }

    fun sortBusinessesByRevenue() {
        businesses.value = businesses.value?.sortedByDescending { it.totalRevenue.value }
    }
}