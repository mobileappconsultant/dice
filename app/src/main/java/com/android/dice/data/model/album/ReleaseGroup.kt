package com.android.dice.data.model.album

import com.google.gson.annotations.SerializedName

data class ReleaseGroup(
    @SerializedName("disambiguation")
    val disambiguation: String,
    @SerializedName("first-release-date")
    val firstReleaseDate: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("primary-type")
    val primaryType: String,
    @SerializedName("primary-type-id")
    val primaryTypeId: String,
    @SerializedName("secondary-type-ids")
    val secondaryTypeIds: List<String>,
    @SerializedName("secondary-types")
    val secondaryTypes: List<String>,
    @SerializedName("title")
    val title: String
)
