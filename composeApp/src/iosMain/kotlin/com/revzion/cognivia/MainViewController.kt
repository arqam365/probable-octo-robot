package com.revzion.cognivia

import androidx.compose.ui.window.ComposeUIViewController
import com.revzion.cognivia.core.di.initializeKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initializeKoin() }
) { App() }