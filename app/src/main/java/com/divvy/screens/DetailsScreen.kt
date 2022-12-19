package com.divvy.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.divvy.R
import com.divvy.domain.RevenueDisplay
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
        val distanceToUser = business.value?.location?.distanceToUser?.observeAsState()
        TopAppBar(
            title = { Text(text = business.value?.name.orEmpty()) },
            navigationIcon = {IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Filled.ArrowBack, null)
            }})
        Column(modifier = Modifier.padding(16.dp)) {
            Text(business.value?.location?.address.orEmpty())
            Text(business.value?.location?.city.orEmpty())
            Text(business.value?.location?.country.orEmpty(), modifier = Modifier.padding(bottom = 8.dp))
            Text(distanceToUser?.value?.getMilesText().orEmpty(), style = TextStyle(fontSize = 12.sp))
            LineGraph(
                xAxisData = business.value?.getXAxis() ?: emptyList(),
                yAxisData = business.value?.getYAxis() ?: emptyList(),
                onPointClicked = {
                    val lele = it.second
                    val lolo = it.second as? Double
                    viewModel.selectedRevenue.value = RevenueDisplay(it.second as? Double)
                },
                header = {
                    val selectedRevenue = viewModel.selectedRevenue.observeAsState()
                    Column(modifier = Modifier.padding(vertical = 16.dp)) {
                        Text(stringResource(R.string.revenue_chart_header), style = TextStyle(fontSize = 24.sp))
                        val valele = selectedRevenue.value
                        Text(selectedRevenue.value?.getFormatted().orEmpty())
                    }
                },
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
}