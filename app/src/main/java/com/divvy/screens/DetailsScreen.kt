package com.divvy.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.divvy.viewModel.BusinessViewModel

@Composable
fun DetailsScreen(navController: NavController, viewModel: BusinessViewModel) {
    Column {
        TopAppBar(
            title = { Text(text = "Businesses")},
            navigationIcon = {IconButton(onClick = { navController.popBackStack() }) {
            Icon(Icons.Filled.ArrowBack, null)
            }})
        Text("Details Screen")
    }
}