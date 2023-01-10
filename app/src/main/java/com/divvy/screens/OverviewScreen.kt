package com.divvy.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OverviewScreen(navController: NavController, viewModel: BusinessViewModel) {

    @Composable
    fun MonthsSlider() {
        val range = viewModel.selectedRange.observeAsState()
        range.value?.let {
            RangeSlider(
                values = it, onValueChange = {
                    viewModel.selectedRange.value = it
                },
                valueRange = 1f..6f,
                steps = 4,
                onValueChangeFinished = {
                    viewModel.sortBusinessesByRevenue()
                }
            )
        }
    }

    @Composable
    fun MonthsConfirmationDialog(openDialog: MutableState<Boolean>) {
        AlertDialog(
            onDismissRequest = { openDialog.value =false },
            backgroundColor = Color.Black,
            title = { Text(text = stringResource(R.string.sort_dialog_title), fontWeight = FontWeight.Bold)},
            confirmButton = {
                Button(onClick = {openDialog.value = false}) {
                    Text(stringResource(R.string.ok))
                }
            }, text = { MonthsSlider()})
    }

    @Composable
    // We're keeping this holder inside OverviewScreen just so we have access to the nav controller and the view model without passing them in.
    fun BusinessHolder(business: Business) {
        business.name?.let {
            Column {
                ClickableText(text = AnnotatedString(business.getOverviewName()), style = TextStyle(LIGHT_WHITE, fontWeight = FontWeight.Bold), modifier = Modifier
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
        val openDialog = remember { mutableStateOf(false) }
        TopAppBar(title = { Text(stringResource(R.string.overview_screen_title)) }, actions = {
            val dropDownExpanded = remember { mutableStateOf(false) }
            ExposedDropdownMenuBox(expanded = true, onExpandedChange = { dropDownExpanded.value = !dropDownExpanded.value }) {
                Text(stringResource(R.string.sort_dropdown_title))
                ExposedDropdownMenu(expanded = dropDownExpanded.value, onDismissRequest = { dropDownExpanded.value = false }, modifier = Modifier.width(90.dp)) {
                    DropdownMenuItem(onClick = { viewModel.sortBusinessesAlphabetically() }) {
                        Text(stringResource(R.string.alphabetic_drop_down), style = TextStyle.Default.copy(color = Color.Black))
                    }
                    DropdownMenuItem(onClick = { openDialog.value = true }, modifier = Modifier.wrapContentWidth()) {
                        Text(text = stringResource(R.string.revenue_drop_down), style = TextStyle.Default.copy(color = Color.Black))
                    }
                }
            }
        })
        val businesses = viewModel.businesses.observeAsState()
        if (openDialog.value)
            MonthsConfirmationDialog(openDialog)
        LazyColumn(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(
                items = businesses.value ?: listOf(),
                itemContent = {
                    BusinessHolder(it)
                })
        }
    }
}