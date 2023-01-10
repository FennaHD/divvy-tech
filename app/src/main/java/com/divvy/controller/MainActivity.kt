package com.divvy.controller

import android.location.Geocoder
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.divvy.navigation.NavGraph
import com.divvy.ui.theme.DivvyTechTheme
import com.divvy.viewModel.BusinessViewModel

class MainActivity : ComponentActivity() {

    private val viewModel by lazy {
        BusinessViewModel(Geocoder(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            viewModel.retrieveBusinesses()
            viewModel.businesses.observe(this) {
                viewModel.setTotalRevenue(viewModel.selectedRange.value)
            }
            DivvyTechTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavGraph(navController = rememberNavController(), viewModel = viewModel)
                }
            }
        }
    }
}