package com.obsndy.prospectfarmersapp.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.obsndy.prospectfarmersapp.BuildConfig
import com.obsndy.prospectfarmersapp.R
import com.obsndy.prospectfarmersapp.ui.components.dialogs.MessageDialog
import com.obsndy.prospectfarmersapp.ui.navigation.Nav
import com.stevdzasan.onetap.OneTapSignInWithGoogle
import com.stevdzasan.onetap.rememberOneTapSignInState

@Composable
fun LoginScreen(navController: NavController){

    val viewModel = viewModel<LoginViewModel>()

    val isSignedIn by viewModel.isSignedIn.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val isMessage by viewModel.isMessage.collectAsState()
    val message by viewModel.message.collectAsState()
    var dialog by remember {
        mutableStateOf(false)
    }

    if(isMessage){
        dialog = true
        viewModel.onMessageReported()
    }

    if(dialog){
        MessageDialog(
            message = message,
            onDismiss = {dialog = false}
        )
    }

    if(isSignedIn){
        navController.navigate(Nav.Home.route)
    }

    val state = rememberOneTapSignInState()

    OneTapSignInWithGoogle(
        state = state,
        clientId = BuildConfig.CLIENT_ID,
        onTokenIdReceived = { tokenId ->

            val credential = GoogleAuthProvider.getCredential(tokenId, null)
            FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener { task ->

                    viewModel.setLoadingStatus(false)
                    if(task.isSuccessful){

                        navController.navigate(Nav.Home.route)

                    } else {

                        viewModel.setLoadingStatus(false)
                        viewModel.setMessageStatus(true)
                        viewModel.setMessage("Login Failed")
                    }
                }



        },
        onDialogDismissed = { msg ->
            viewModel.setLoadingStatus(false)
            viewModel.setMessageStatus(true)
            viewModel.setMessage(msg)
        }
    )


    Scaffold {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ){
            Column (
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    modifier = Modifier.size(140.dp),
                    painter = painterResource(id = R.drawable.pfa_logo),
                    contentDescription = "Logo"
                )

                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    text = "Prospect Farmers",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    modifier = Modifier.padding(horizontal = 30.dp),
                    text = "Manage your farmers seamlessly and effortlessly with Prospect Farmers App",
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(100.dp))


            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
                    .align(Alignment.BottomCenter),
                enabled = !isLoading,
                colors = ButtonDefaults.buttonColors().copy(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                //onClick = { state.open(); viewModel.setLoadingStatus(true) }
                onClick = {navController.navigate(Nav.Home.route)}
            ) {

                if(isLoading){
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = MaterialTheme.colorScheme.primary,
                        strokeWidth = 2.dp
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(text = "Please Wait")

                } else {
                    Image(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(30.dp),
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = "Google"
                    )
                    Text(
                        text = "Sign in with Google"
                    )
                }


            }
        }
    }

}