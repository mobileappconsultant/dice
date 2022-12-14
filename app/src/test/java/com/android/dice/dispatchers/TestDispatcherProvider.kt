package com.android.dice.dispatchers

import com.android.dice.utils.DispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@OptIn(ExperimentalCoroutinesApi::class)
class TestDispatcherProvider() : DispatcherProvider {
    override val default
        get() = UnconfinedTestDispatcher()
    override val io
        get() = UnconfinedTestDispatcher()
    override val main
        get() = UnconfinedTestDispatcher()
    override val unconfined
        get() = UnconfinedTestDispatcher()
}
