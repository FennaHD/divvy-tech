package com.divvy.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.AnnotatedString
import androidx.navigation.NavController
import com.divvy.domain.Business
import com.divvy.navigation.Screens
import com.divvy.viewModel.BusinessViewModel

@Composable
fun OverviewScreen(navController: NavController, vm: BusinessViewModel) {

    @Composable
    fun BusinessHolder(business: Business) {
        business.name?.let {
            ClickableText(text = AnnotatedString(it)) {
                navController.navigate(route = Screens.Details.route)
            }
        }
    }

    val businesses = vm.businesses.observeAsState()
    LazyColumn {
        items(
            items = businesses.value ?: listOf(),
            itemContent = {
                BusinessHolder(it)
            })
    }
}