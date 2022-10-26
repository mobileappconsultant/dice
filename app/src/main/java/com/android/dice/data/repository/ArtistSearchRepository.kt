package com.android.dice.data.repository

import androidx.paging.PagingData
import com.android.dice.data.model.ArtistSchema
import com.android.dice.data.model.album.AlbumResponseSchema
import com.android.dice.utils.ApiResult
import kotlinx.coroutines.flow.Flow

interface ArtistSearchRepository {
    suspend fun searchUser(query: String): Flow<PagingData<ArtistSchema>>
    suspend fun getAlbum(artist: String): ApiResult<AlbumResponseSchema>
}
