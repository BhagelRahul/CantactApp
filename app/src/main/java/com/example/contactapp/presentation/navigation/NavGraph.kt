package com.example.contactapp.presentation.navigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.contactapp.presentation.ContactViewModel
import com.example.contactapp.presentation.Screen.AddEditScreen
import com.example.contactapp.presentation.Screen.HomeScreen
import com.example.contactapp.presentation.Screen.SplashScreen



@Composable
fun NavGraph(navHostController: NavHostController, viewModel: ContactViewModel) {
    val state by viewModel.state.collectAsState()

    NavHost(
        navController = navHostController,
        startDestination = Routes.Splash.route
    ) {
        composable(Routes.Splash.route) {
            // Navigate to HomeScreen after SplashScreen finishes
            SplashScreen(navController = navHostController)

        }
        composable(Routes.Home.route) {
            HomeScreen(
                navHostController=navHostController,
                state = state,
                viewModel = viewModel
            )
        }

        composable(Routes.AddEdit.route) {
            AddEditScreen(
                navHostController=navHostController,
                state=viewModel.state.collectAsState().value,
                onEvent = { viewModel.saveContact() })
        }


    }
}


