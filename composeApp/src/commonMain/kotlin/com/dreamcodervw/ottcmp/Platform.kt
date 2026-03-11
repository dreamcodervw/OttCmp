package com.dreamcodervw.ottcmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform