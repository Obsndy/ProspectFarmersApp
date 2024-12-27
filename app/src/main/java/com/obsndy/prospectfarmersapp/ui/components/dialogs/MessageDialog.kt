package com.obsndy.prospectfarmersapp.ui.components.dialogs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun MessageDialog(message: String, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Card (
            shape = RoundedCornerShape(24.dp)
        ) {

            Column (modifier = Modifier.padding(24.dp)) {
                Text(text = "Message", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(10.dp))

                Text(text = message)
                Spacer(modifier = Modifier.height(10.dp))

                Box (modifier = Modifier.fillMaxWidth()) {
                    TextButton(modifier = Modifier.align(Alignment.CenterEnd), onClick = onDismiss) {
                        Text(text = "Okay")
                    }
                }
            }

        }
    }
}