package com.obsndy.prospectfarmersapp.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.obsndy.prospectfarmersapp.R
import com.obsndy.prospectfarmersapp.data.config.ProspectDatabase
import com.obsndy.prospectfarmersapp.ui.components.app_bars.HomeTopAppBar
import com.obsndy.prospectfarmersapp.ui.components.bottom_sheets.FarmerBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, db: ProspectDatabase) {

    val viewModel = viewModel<HomeViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(db.farmerDao()) as T
            }
        }

    )
    var showBottomSheet by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    val farmers by viewModel.farmers.collectAsState(initial = emptyList())

    if (showBottomSheet) {
        FarmerBottomSheet(
            viewModel = viewModel,
            onDismiss = { showBottomSheet = false },
            onAdd = { showBottomSheet = false }
        )
    }

    Scaffold(
        topBar = {
            HomeTopAppBar(
                title = "Prospect Farmers",
                actions = {

                    IconButton(onClick = { showBottomSheet = true }) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = Icons.Default.AddCircle,
                            contentDescription = "Menu Icon"
                        )
                    }

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            modifier = Modifier.size(26.dp),
                            painter = painterResource(id = R.drawable.menu),
                            contentDescription = "Menu Icon"
                        )
                    }
                }
            )
        }
    ){
        Box(modifier = Modifier.padding(it)){

            LazyColumn {

            }

            HorizontalDivider()
        }
    }

}