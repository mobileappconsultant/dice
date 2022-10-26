package com.android.dice.domain.usecase

import com.android.dice.data.repository.ArtistSearchRepository
import com.android.dice.domain.mapper.AlbumMapper
import com.android.dice.domain.model.AlbumDomain
import com.android.dice.utils.ApiResult
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetAlbumUseCase @Inject constructor(
    private val searchRepository: ArtistSearchRepository,
    private val albumMapper: AlbumMapper
) {
    suspend fun execute(artist: String): List<AlbumDomain> {
        return when (val result = searchRepository.getAlbum(artist)) {
            is ApiResult.NetworkError -> {
                emptyList()
            }
            is ApiResult.Success -> {
                result.data.releaseGroups.map {
                    albumMapper.mapToDomain(it)
                }.sortedByDescending { it.dateReleased }
            }
        }
    }
}
