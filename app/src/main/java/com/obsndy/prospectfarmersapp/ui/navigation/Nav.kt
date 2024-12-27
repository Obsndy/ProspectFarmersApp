package com.obsndy.prospectfarmersapp.ui.navigation

sealed class Nav(val route: String) {
    data object Home : Nav("home")
    data object Login : Nav("login")
}