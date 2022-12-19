package com.divvy.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.divvy.R
import com.divvy.domain.Business
import com.divvy.navigation.Screens
import com.divvy.ui.theme.DARK_WHITE
import com.divvy.ui.theme.LIGHT_WHITE
import com.divvy.viewModel.BusinessViewModel

@Composable
fun OverviewScreen(navController: NavController, viewModel: BusinessViewModel) {

    @Composable
    fun BusinessHolder(business: Business) {
        business.name?.let {
            Column {
                ClickableText(text = AnnotatedString(it), style = TextStyle(LIGHT_WHITE, fontWeight = FontWeight.Bold), modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxSize()) {
                    viewModel.selectedBusiness.value = business
                    viewModel.retrieveLocation(business)
                    navController.navigate(route = Screens.Details.route)
                }
                Divider(thickness = 1.dp, color = DARK_WHITE)
            }
        }
    }

    Column {
        TopAppBar(title = { Text(stringResource(R.string.overview_screen_title))})
        val businesses = viewModel.businesses.observeAsState()
        LazyColumn(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(
                items = businesses.value ?: listOf(),
                itemContent = {
                    BusinessHolder(it)
                })
        }
    }
}