package com.android.dice.di

import androidx.paging.PagingConfig
import com.android.dice.data.api.MusicApi
import com.android.dice.utils.Constants
import com.android.dice.utils.DefaultDispatcherProvider
import com.android.dice.utils.DispatcherProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrl

@Module
@InstallIn(SingletonComponent::class)
open class NetworkModule {

    open fun getBaseUrl() = BASE_URL

    open fun getDispatchers(): DispatcherProvider = DefaultDispatcherProvider()

    @Provides
    @BaseUrl
    fun provideBaseUrl() = getBaseUrl()

    @Provides
    @Singleton
    fun gson(): Gson {
        return GsonBuilder()
            .setLenient()
            .serializeNulls()
            .create()
    }

    @Provides
    @Singleton
    fun provideHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson, @BaseUrl baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideAPI(retrofit: Retrofit): MusicApi {
        return retrofit.create(MusicApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoroutineDispatcher(): DispatcherProvider {
        return getDispatchers()
    }

    @Provides
    fun providePagingConfig(): PagingConfig {
        return PagingConfig(pageSize = Constants.NO_PER_PAGE)
    }

    companion object {
        private const val CONNECT_TIMEOUT = 10L
        private const val BASE_URL = "https://musicbrainz.org/"
    }
}
