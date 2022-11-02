package com.android.dice.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers

@InstallIn(SingletonComponent::class)
@Module
class CoroutinesModule {

    companion object {
        const val CONTEXT_UI = "UI"
        const val CONTEXT_IO = "IO"
    }

    @Provides
    @Named(CONTEXT_UI)
    fun provideUiCoroutineContext(): CoroutineContext = Dispatchers.Main

    @Provides
    @Named(CONTEXT_IO)
    fun provideIoCoroutineContext(): CoroutineContext = Dispatchers.IO
}
