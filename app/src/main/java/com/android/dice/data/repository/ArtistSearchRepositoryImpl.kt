package com.android.dice.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.dice.data.api.MusicApi
import com.android.dice.data.model.ArtistSchema
import com.android.dice.data.model.album.AlbumResponseSchema
import com.android.dice.data.paging.SearchPagingSource
import com.android.dice.di.CoroutinesModule.Companion.CONTEXT_IO
import com.android.dice.utils.ApiResult
import com.android.dice.utils.apiResult
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ArtistSearchRepositoryImpl @Inject constructor(
    private val searchApi: MusicApi,
    private val pagingConfig: PagingConfig,
    @Named(CONTEXT_IO) private val ioContext: CoroutineContext
) : ArtistSearchRepository {
    override suspend fun searchUser(query: String): Flow<PagingData<ArtistSchema>> =
        withContext(ioContext) {
            Pager(pagingConfig) {
                SearchPagingSource(searchApi, query)
            }.flow
        }

    override suspend fun getAlbum(artist: String): ApiResult<AlbumResponseSchema> =
        withContext(ioContext) {
            apiResult { searchApi.getAlbum(artist) }
        }
}
