package com.revzion.cognivia

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform