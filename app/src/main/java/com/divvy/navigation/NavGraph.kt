package com.divvy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.divvy.screens.DetailsScreen
import com.divvy.screens.OverviewScreen
import com.divvy.viewModel.BusinessViewModel

@Composable
fun NavGraph(navController: NavHostController, viewModel: BusinessViewModel) {
    NavHost(navController = navController, startDestination = Screens.Overview.route) {
        composable(route = Screens.Overview.route) {
            OverviewScreen(navController, viewModel)
        }
        composable(route = Screens.Details.route) {
            DetailsScreen(navController, viewModel)
        }
    }
}