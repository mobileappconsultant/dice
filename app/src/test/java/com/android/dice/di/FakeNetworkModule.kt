package com.android.dice.di

import com.android.dice.dispatchers.TestDispatcherProvider
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
class FakeNetworkModule : NetworkModule() {
    override fun getBaseUrl() = "http://127.0.0.1:8080"
    override fun getDispatchers() = TestDispatcherProvider()
}
