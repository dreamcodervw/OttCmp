package com.dreamcodervw.ottcmp.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.koin.compose.getKoin
import org.koin.core.parameter.ParametersDefinition

@Composable
inline fun <reified T : Any> rememberKoinInject(
    key: Any? = null,
    noinline parameters: ParametersDefinition? = null,
): T {
    val koin = getKoin()
    return remember(key) {
        if (parameters != null) koin.get(parameters = parameters) else koin.get()
    }
}
