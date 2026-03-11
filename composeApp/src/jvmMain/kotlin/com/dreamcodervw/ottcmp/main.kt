package com.dreamcodervw.ottcmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.dreamcodervw.ottcmp.di.initKoin

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "OttCmp",
    ) {
        App()
    }
}
