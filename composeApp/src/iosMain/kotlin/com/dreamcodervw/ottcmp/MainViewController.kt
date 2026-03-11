package com.dreamcodervw.ottcmp

import androidx.compose.ui.window.ComposeUIViewController
import com.dreamcodervw.ottcmp.di.initKoin

fun MainViewController() = ComposeUIViewController {
    App()
}.also {
    initKoin()
}
