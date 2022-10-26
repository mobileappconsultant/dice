package com.android.dice.data.model

import com.google.gson.annotations.SerializedName

data class SearchResponseSchema(
    @SerializedName("artists")
    val artists: List<ArtistSchema>,
    @SerializedName("count")
    val count: Int,
    @SerializedName("created")
    val created: String,
    @SerializedName("offset")
    val offset: Int
)
