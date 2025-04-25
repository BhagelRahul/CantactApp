package com.example.contactapp.presentation.navigation

sealed class Routes (var route:String){
    object Splash: Routes("Splash Screen")
    object AddEdit: Routes("add_edit_screen")
    object Home: Routes("home_screen")
}
