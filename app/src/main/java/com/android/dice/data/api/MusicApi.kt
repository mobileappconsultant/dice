package com.android.dice.data.api

import com.android.dice.data.model.SearchResponseSchema
import com.android.dice.data.model.album.AlbumResponseSchema
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicApi {

    @GET("ws/2/artist")
    suspend fun searchArtist(
        @Query("query") query: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): SearchResponseSchema

    @GET("ws/2/release-group")
    suspend fun getAlbum(
        @Query("artist") id: String,
        @Query("type") type: String = "album"
    ): AlbumResponseSchema
}
