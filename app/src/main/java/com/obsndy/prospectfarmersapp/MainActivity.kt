package com.obsndy.prospectfarmersapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.obsndy.prospectfarmersapp.data.config.ProspectDatabase
import com.obsndy.prospectfarmersapp.ui.navigation.Nav
import com.obsndy.prospectfarmersapp.ui.navigation.SetUpNavGraph
import com.obsndy.prospectfarmersapp.ui.theme.ProspectFarmersAppTheme

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            ProspectDatabase::class.java,
            "prospect.db"
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {



            ProspectFarmersAppTheme {
                SetUpNavGraph(
                    navHostController = rememberNavController(),
                    startDestination = Nav.Login.route,
                    database = db
                )
            }
        }
    }
}
