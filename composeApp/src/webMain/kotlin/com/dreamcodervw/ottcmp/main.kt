package com.dreamcodervw.ottcmp

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.dreamcodervw.ottcmp.di.initKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    initKoin()
    ComposeViewport {
        App()
    }
}
