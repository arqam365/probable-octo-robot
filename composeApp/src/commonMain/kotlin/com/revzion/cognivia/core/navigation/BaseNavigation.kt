package com.revzion.cognivia.core.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.revzion.cognivia.auth.AuthViewModel
import com.revzion.cognivia.feature.homeBase.HomeBaseHolder
import com.revzion.cognivia.feature.login.AuthMode
import com.revzion.cognivia.feature.login.AuthScreen
import com.revzion.cognivia.feature.login.IntroScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NavigationSetup(paddingValues: PaddingValues) {
    val navController = rememberNavController()
    val vm: AuthViewModel = koinViewModel()
    val isLoggedIn by vm.isLoggedIn.collectAsState()

    val startDestination = if (isLoggedIn) Routes.HomeGraph else Routes.IntroScreen

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable<Routes.IntroScreen> {
            IntroScreen(navController, paddingValues)
        }

        composable<Routes.Auth> {
            AuthScreen(navController = navController, mode = AuthMode.SignUp)
        }

        composable<Routes.HomeGraph> {
            HomeBaseHolder(navController)
        }
    }
}