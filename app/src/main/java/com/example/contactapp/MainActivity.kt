
package com.example.contactapp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.contactapp.presentation.ContactViewModel
import com.example.contactapp.presentation.navigation.NavGraph
import com.example.contactapp.ui.theme.ContactAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val viewModel = hiltViewModel<ContactViewModel>()
            val navHostController = rememberNavController()

            ContactAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    NavGraph(
                        navHostController = navHostController,
                        viewModel = viewModel
                    )
                }
            }
        }

    }
}
