package com.revzion.cognivia.feature.homeBase

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import cognivia.composeapp.generated.resources.Res
import cognivia.composeapp.generated.resources.icon_avatar
// ❌ REMOVE this line:
// import coil3.Image
import com.revzion.cognivia.app.primaryBlue
import com.revzion.cognivia.auth.AuthViewModel
import com.revzion.cognivia.core.navigation.BottomNavBar
import com.revzion.cognivia.core.navigation.NavBarNavigation
import com.revzion.cognivia.core.navigation.Routes
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeBaseHolder(mainNav: NavController){
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val authVm: AuthViewModel = koinViewModel()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationDrawerContent(
                onLogout = {
                    authVm.signOut()
                    scope.launch { drawerState.close() }
                    mainNav.navigate(
                        route = Routes.Auth,
                        navOptions = androidx.navigation.navOptions {
                            popUpTo(Routes.HomeGraph) { inclusive = true }
                            launchSingleTop = true
                        }
                    )
                }
            )
        },
    ) {
        Scaffold(
            bottomBar = { BottomNavBar(navController = navController) }
        ) { pv ->
            NavBarNavigation(
                mainController = mainNav,
                navController = navController,
                paddingValues = pv,
                drawerState = drawerState
            )
        }
    }
}

@Composable
fun NavigationDrawerContent(
    onLogout: () -> Unit
){
    ModalDrawerSheet(modifier= Modifier.fillMaxWidth(0.78f)){
        Row(modifier=Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 24.dp)) {
            Column {
                Image(
                    painter = painterResource(Res.drawable.icon_avatar),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp).clip(CircleShape)
                        .border(1.dp, primaryBlue, shape = CircleShape)
                )
                Spacer(modifier=Modifier.height(2.dp))
                Text("Sharad Singh",
                    fontWeight = FontWeight.Medium,
                    lineHeight = 16.sp,
                    fontSize = 16.sp)
                Text("sharadsengar2003@gmail.com",
                    lineHeight = 14.sp,
                    fontSize = 14.sp)
            }
        }
        HorizontalDivider()
        NavigationDrawerItem(label = { Text("Kids Mode") }, selected = false, shape = RectangleShape, onClick = {})
        NavigationDrawerItem(label = { Text("Wishlist") }, selected = false, shape = RectangleShape, onClick = {})
        NavigationDrawerItem(label = { Text("Settings") }, selected = false, shape = RectangleShape, onClick = {})
        NavigationDrawerItem(label = { Text("Help & Support") }, selected = false, shape = RectangleShape, onClick = {})
        NavigationDrawerItem(label = { Text("About") }, selected = false, shape = RectangleShape, onClick = {})
        NavigationDrawerItem(
            label = { Text("Log Out") },
            selected = false,
            shape = RectangleShape,
            onClick = onLogout
        )
    }
}