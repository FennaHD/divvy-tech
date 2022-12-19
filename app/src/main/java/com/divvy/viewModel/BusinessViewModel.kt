package com.divvy.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.divvy.domain.Business
import com.divvy.service.BusinessRepository
import kotlinx.coroutines.launch

class BusinessViewModel: ViewModel() {

    val businesses = MutableLiveData<List<Business>>(null)
    val selectedBusiness = MutableLiveData<Business>(null)

    fun retrieveBusinesses() {
        viewModelScope.launch {
            BusinessRepository().client.getBusinesses()?.let {
                businesses.value = it
            }
        }
    }
}