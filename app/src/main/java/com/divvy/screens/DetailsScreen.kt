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
import com.jaikeerthick.composable_graphs.composables.LineGraph
import com.jaikeerthick.composable_graphs.data.GraphData

@Composable
fun DetailsScreen(navController: NavController, viewModel: BusinessViewModel) {
    Column {
        TopAppBar(
            title = { Text(text = "Businesses")},
            navigationIcon = {IconButton(onClick = { navController.popBackStack() }) {
            Icon(Icons.Filled.ArrowBack, null)
            }})
        Text("Details Screen")
        LineGraph(
            xAxisData = listOf("Sun", "Mon", "Tues", "Wed", "Thur", "Fri", "Sat").map {
                GraphData.String(it)
            }, // xAxisData : List<GraphData>, and GraphData accepts both Number and String types
            yAxisData = listOf(200, 40, 60, 450, 700, 30, 50),
        )
    }
}