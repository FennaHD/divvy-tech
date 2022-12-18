package com.divvy.controller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.divvy.domain.Business
import com.divvy.ui.theme.DivvyTechTheme
import com.divvy.viewModel.BusinessViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.livedata.observeAsState

class MainActivity : ComponentActivity() {

    val viewModel = BusinessViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            viewModel.retrieveBusinesses()
            DivvyTechTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BusinessContent(viewModel)
                }
            }
        }
    }
}

@Composable
fun BusinessContent(vm: BusinessViewModel) {
    val businesses = vm.businesses.observeAsState()
    LazyColumn {
        items(
            items = businesses.value ?: listOf(),
            itemContent = {
                BusinessHolder(it)
            })
    }
}

@Composable
fun BusinessHolder(business: Business) {
    business.name?.let {
        Text(text = it)
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DivvyTechTheme {
        Greeting("Android")
    }
}