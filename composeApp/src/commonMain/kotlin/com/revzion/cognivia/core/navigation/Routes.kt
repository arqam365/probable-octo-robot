package com.revzion.cognivia.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {

    @Serializable
    data object IntroScreen

    @Serializable
    data object Auth

    @Serializable
    data object HomeGraph

    @Serializable
    data object HomeScreen

    @Serializable
    data object CourseScreen

    @Serializable
    data object ProfileScreen

    @Serializable
    data object FavScreen

}