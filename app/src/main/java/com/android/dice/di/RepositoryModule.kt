package com.android.dice.di

import com.android.dice.data.repository.ArtistSearchRepository
import com.android.dice.data.repository.ArtistSearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindArtistRepository(artistSearchRepositoryImpl: ArtistSearchRepositoryImpl): ArtistSearchRepository
}
