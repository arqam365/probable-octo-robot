package com.revzion.cognivia.core.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.revzion.cognivia.feature.HomeBase.presentation.screens.CourseScreen
import com.revzion.cognivia.feature.HomeBase.presentation.screens.FavScreen
import com.revzion.cognivia.feature.HomeBase.presentation.screens.HomeScreen
import com.revzion.cognivia.feature.HomeBase.presentation.screens.ProfileScreen

@Composable
fun NavBarNavigation(
    mainController: NavController,
    navController: NavHostController,
    paddingValues: PaddingValues,
    drawerState: DrawerState
){

    val startDestination= Routes.HomeScreen

    NavHost(navController=navController, startDestination = startDestination){

        composable<Routes.HomeScreen> {
            HomeScreen(navController =navController, pv =paddingValues,drawerState=drawerState)
        }

        composable<Routes.CourseScreen> {
            CourseScreen(navController=navController,pv=paddingValues)
        }

        composable<Routes.ProfileScreen> {
            ProfileScreen(navController=navController,pv=paddingValues)
        }

        composable<Routes.FavScreen> {
            FavScreen(navController=navController)
        }

    }

}