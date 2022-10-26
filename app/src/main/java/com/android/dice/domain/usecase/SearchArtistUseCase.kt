package com.android.dice.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.android.dice.data.repository.ArtistSearchRepository
import com.android.dice.domain.mapper.ArtistMapper
import com.android.dice.domain.model.ArtistDomain
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@ViewModelScoped
class SearchArtistUseCase @Inject constructor(
    private val searchRepository: ArtistSearchRepository,
    private val artistMapper: ArtistMapper
) {
    suspend fun execute(query: String): Flow<PagingData<ArtistDomain>> =
        searchRepository.searchUser(query).map {
            it.map { schema ->
                artistMapper.mapToDomain(schema = schema)
            }
        }
}
