package com.kmp.kmpnfc

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform