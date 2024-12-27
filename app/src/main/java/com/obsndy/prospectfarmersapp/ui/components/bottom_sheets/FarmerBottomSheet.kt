package com.obsndy.prospectfarmersapp.ui.components.bottom_sheets

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.obsndy.prospectfarmersapp.ui.screens.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FarmerBottomSheet(viewModel: HomeViewModel, onDismiss: () -> Unit, onAdd: () -> Unit){
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        sheetState = bottomSheetState,
        onDismissRequest = { /*TODO*/ }) {

        Column {

        }

    }

}