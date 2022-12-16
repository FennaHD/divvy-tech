package com.divvy.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.divvy.service.BusinessRepository

class BusinessViewModel: ViewModel() {

    val businesses = liveData {
        emit(BusinessRepository().client.getBusinesses())
    }
}