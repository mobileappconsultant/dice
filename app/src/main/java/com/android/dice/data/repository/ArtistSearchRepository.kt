package com.android.dice.data.repository

import androidx.paging.PagingData
import com.android.dice.data.model.ArtistSchema
import kotlinx.coroutines.flow.Flow

interface ArtistSearchRepository {
    suspend fun searchUser(query: String): Flow<PagingData<ArtistSchema>>
}
