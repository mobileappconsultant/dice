package com.android.dice.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.dice.data.api.MusicApi
import com.android.dice.data.model.ArtistSchema
import com.android.dice.data.model.album.AlbumResponseSchema
import com.android.dice.data.paging.SearchPagingSource
import com.android.dice.utils.ApiResult
import com.android.dice.utils.apiResult
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ArtistSearchRepositoryImpl @Inject constructor(
    private val searchApi: MusicApi,
    private val pagingConfig: PagingConfig
) : ArtistSearchRepository {
    override suspend fun searchUser(query: String): Flow<PagingData<ArtistSchema>> =
        Pager(pagingConfig) {
            SearchPagingSource(searchApi, query)
        }.flow

    override suspend fun getAlbum(artist: String): ApiResult<AlbumResponseSchema> =
        apiResult { searchApi.getAlbum(artist) }
}
