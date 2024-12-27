package com.obsndy.prospectfarmersapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.obsndy.prospectfarmersapp.data.config.ProspectDatabase
import com.obsndy.prospectfarmersapp.ui.screens.home.HomeScreen
import com.obsndy.prospectfarmersapp.ui.screens.login.LoginScreen

@Composable
fun SetUpNavGraph(navHostController: NavHostController, startDestination: String, database: ProspectDatabase){

    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ){
        composable(route = Nav.Home.route){
            HomeScreen(navController = navHostController, db = database)
        }
        composable(route = Nav.Login.route){
            LoginScreen(navHostController)
        }
    }
}

