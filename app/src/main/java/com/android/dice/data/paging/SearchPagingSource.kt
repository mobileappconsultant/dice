package com.android.dice.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.dice.data.api.MusicApi
import com.android.dice.data.model.ArtistSchema
import com.android.dice.utils.Constants.NO_PER_PAGE

class SearchPagingSource(
    private val searchApi: MusicApi,
    private val query: String,
) : PagingSource<Int, ArtistSchema>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArtistSchema> {
        val offset = params.key ?: 0
        return try {
            val items = searchApi.searchArtist(query = query, offset = offset, limit = NO_PER_PAGE)
            val endOfPaginationReached = items.artists.isEmpty()
            if (endOfPaginationReached.not()) {
                LoadResult.Page(
                    data = items.artists,
                    prevKey = if (offset == 0) null else offset - NO_PER_PAGE,
                    nextKey = if (endOfPaginationReached || NO_PER_PAGE >= items.count) null else offset + NO_PER_PAGE
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArtistSchema>): Int? {
        return state.anchorPosition
    }
}
