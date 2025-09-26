package com.revzion.cognivia.feature.HomeBase

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
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
import coil3.Image
import com.revzion.cognivia.app.primaryBlue
import com.revzion.cognivia.core.navigation.BottomNavBar
import com.revzion.cognivia.core.navigation.NavBarNavigation
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomeBaseHolder(mainNav: NavController){
    val navController= rememberNavController()
    val drawerState= rememberDrawerState(initialValue = DrawerValue.Closed)
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {NavigationDrawerContent()},
    ) {
        Scaffold(
            bottomBar = {
                BottomNavBar(navController = navController)
            }
        ) {

            NavBarNavigation(
                mainController = mainNav,
                navController = navController,
                paddingValues = it,
                drawerState=drawerState
            )

        }
    }
}

@Composable
fun NavigationDrawerContent(){
    ModalDrawerSheet(modifier= Modifier.fillMaxWidth(0.78f)){
        Row(modifier=Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 24.dp)) {
            Column() {
                Image(
                    painter = painterResource(Res.drawable.icon_avatar),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp).clip(CircleShape).border(1.dp, primaryBlue,shape= CircleShape)
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
        NavigationDrawerItem(
            label = { Text("Kids Mode") },
            selected = false,
            shape = RectangleShape,
            onClick = {}
        )
        NavigationDrawerItem(
            label = { Text("Wishlist") },
            selected = false,
            shape = RectangleShape,
            onClick = {}
        )
        NavigationDrawerItem(
            label = { Text("Settings") },
            selected = false,
            shape = RectangleShape,
            onClick = {}
        )
        NavigationDrawerItem(
            label = { Text("Help & Support") },
            selected = false,
            shape = RectangleShape,
            onClick = {}
        )
        NavigationDrawerItem(
            label = { Text("About") },
            selected = false,
            shape = RectangleShape,
            onClick = {}
        )
        NavigationDrawerItem(
            label = { Text("Log Out") },
            selected = false,
            shape = RectangleShape,
            onClick = {}
        )
    }
}