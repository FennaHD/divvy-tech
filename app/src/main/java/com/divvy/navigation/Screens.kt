package com.divvy.navigation

sealed class Screens(val route: String) {
    object Overview: Screens("overview_screen")
    object Details: Screens("details_screen")
}