package com.revzion.cognivia.feature.homeBase.domain

import org.jetbrains.compose.resources.DrawableResource

data class CourseDisplayItem(
    val name: String,
    val rating: Double,
    val price: String,
    val banner: DrawableResource,
    val thumbnail:String="",
    val owner: String,
    val ownerImage: DrawableResource,
    val duration: Double
)
