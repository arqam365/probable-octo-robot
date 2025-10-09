package com.revzion.cognivia.feature.homeBase.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.revzion.cognivia.app.surfaceBlue

@Composable
fun FavScreen(navController: NavController){
    Box(modifier=Modifier.fillMaxSize().background(color = surfaceBlue),
        contentAlignment = Alignment.Center){
        Text("Fav Screen Todo")
    }
}