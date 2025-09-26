package com.revzion.cognivia.core.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.revzion.cognivia.feature.HomeBase.HomeBaseHolder
import com.revzion.cognivia.feature.Login.IntroScreen

@Composable
fun NavigationSetup(paddingValues: PaddingValues) {
    val navController= rememberNavController()
    val start= Routes.IntroScreen
    NavHost(
        navController = navController,
        startDestination = start
    ){

        composable<Routes.IntroScreen> {
            IntroScreen(navController,paddingValues)
        }

        composable<Routes.HomeGraph> {
            HomeBaseHolder(navController)
        }

    }
}