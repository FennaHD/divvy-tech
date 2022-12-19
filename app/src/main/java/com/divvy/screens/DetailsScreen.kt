package com.divvy.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.divvy.R
import com.divvy.ui.theme.*
import com.divvy.viewModel.BusinessViewModel
import com.jaikeerthick.composable_graphs.color.LinearGraphColors
import com.jaikeerthick.composable_graphs.composables.LineGraph
import com.jaikeerthick.composable_graphs.style.LineGraphStyle
import com.jaikeerthick.composable_graphs.style.LinearGraphVisibility

@Composable
fun DetailsScreen(navController: NavController, viewModel: BusinessViewModel) {
    Column {
        val business = viewModel.selectedBusiness.observeAsState()
        TopAppBar(
            title = { Text(text = business.value?.name.orEmpty()) },
            navigationIcon = {IconButton(onClick = { navController.popBackStack() }) {
            Icon(Icons.Filled.ArrowBack, null)
            }})
        Text(business.value?.location?.address.orEmpty())
        Text(business.value?.location?.city.orEmpty())
        Text(business.value?.location?.country.orEmpty())
        LineGraph(
            xAxisData = business.value?.getXAxis() ?: emptyList(),
            yAxisData = business.value?.getYAxis() ?: emptyList(),
            header = { Text(stringResource(R.string.revenue_chart_header)) },
            style = LineGraphStyle(
                visibility = LinearGraphVisibility(
                    isHeaderVisible = true,
                    isCrossHairVisible = true
                ),
                colors = LinearGraphColors(
                    pointColor = POINT_GREEN,
                    lineColor = ROBINHOOD_GREEN,
                    clickHighlightColor = MATRIX_GREEN,
                    fillGradient = Brush.verticalGradient(colors= listOf(ROBINHOOD_GREEN, ROBINHOOD_BLACK))
                )
            )
        )
    }
}