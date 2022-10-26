package com.android.dice.data.model.album

import com.google.gson.annotations.SerializedName

data class AlbumResponseSchema(
    @SerializedName("release-group-count")
    val releaseGroupCount: Int,
    @SerializedName("release-group-offset")
    val releaseGroupOffset: Int,
    @SerializedName("release-groups")
    val releaseGroups: List<ReleaseGroup>
)
