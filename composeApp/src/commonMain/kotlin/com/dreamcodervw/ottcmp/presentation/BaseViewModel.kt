package com.dreamcodervw.ottcmp.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

abstract class BaseViewModel {
    protected val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    open fun clear() {
        scope.cancel()
    }
}

