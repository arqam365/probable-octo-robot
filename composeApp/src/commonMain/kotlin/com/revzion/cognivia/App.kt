package com.revzion.cognivia

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview

import com.revzion.cognivia.core.navigation.NavigationSetup

@Composable
@Preview
fun App() {
    MaterialTheme {
        Scaffold{
            NavigationSetup(paddingValues=it)
        }
    }
}