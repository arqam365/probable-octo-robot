package com.revzion.cognivia.core.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import cognivia.composeapp.generated.resources.Res
import cognivia.composeapp.generated.resources.nav_icon_course
import cognivia.composeapp.generated.resources.nav_icon_fav
import cognivia.composeapp.generated.resources.nav_icon_home
import cognivia.composeapp.generated.resources.nav_icon_profile
import com.revzion.cognivia.app.primaryBlue
import org.jetbrains.compose.resources.painterResource

@Composable
fun BottomNavBar(navController: NavController) {

    val entry by navController.currentBackStackEntryAsState()
    val currentDestination=entry?.destination

    val bottomBarItems = listOf(Routes.HomeScreen, Routes.CourseScreen, Routes.FavScreen,Routes.ProfileScreen)
    val itemsLabels= listOf("Home","Favourite","Course","Profile")
    val icons= listOf(Res.drawable.nav_icon_home,Res.drawable.nav_icon_fav,Res.drawable.nav_icon_course,Res.drawable.nav_icon_profile)

    Row(modifier=Modifier.fillMaxWidth().height(72.dp)
        .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
        .background(Color.White, RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)),
        verticalAlignment = Alignment.CenterVertically) {

        NavigationBar(containerColor = Color.White,
            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),){
            bottomBarItems.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any{
                        it.hasRoute(item::class)
                    }==true,
                    onClick = {navController.navigate(item){
                        popUpTo(navController.graph.startDestinationId){
                            saveState=true
                        }
                        launchSingleTop=true
                        restoreState=true
                    } },
                    icon = {
                        Icon(
                            painter = painterResource(icons[index]),
                            contentDescription = itemsLabels[index],
                            modifier = Modifier.size(24.dp),
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        unselectedIconColor = MaterialTheme.colorScheme.onBackground,
                        unselectedTextColor = MaterialTheme.colorScheme.onBackground,
                        indicatorColor = Color.Transparent,
                        selectedIconColor = primaryBlue,
                        selectedTextColor = primaryBlue
                    )
                )
            }
        }
    }
}