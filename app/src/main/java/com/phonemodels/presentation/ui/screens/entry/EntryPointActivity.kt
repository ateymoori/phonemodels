package com.phonemodels.presentation.ui.screens.entry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.phonemodels.presentation.ui.screens.dashboard.DashboardScreen
import com.phonemodels.presentation.ui.theme.PhoneModelsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EntryPointActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhoneModelsAppTheme {
                PhonesApp()
            }
        }
    }

    @Composable
    private fun PhonesApp() {
        val navController = rememberNavController()
        NavHost(navController, startDestination = NavigationKeys.Route.PHONES_LIST) {
            composable(route = NavigationKeys.Route.PHONES_LIST) {
                 DashboardScreen(navController)
            }
        }
    }


    object NavigationKeys {
        object Arg {
            const val PHONE_ID = "phone_id"
        }
        object Route {
            const val PHONES_LIST = "phones_list"
            const val PHONE_DETAILS = "phone_details"
        }
    }
}