package com.android.dice.data.model

import com.google.gson.annotations.SerializedName

data class BeginArea(
    @SerializedName("id")
    val id: String,
    @SerializedName("life-span")
    val lifeSpan: LifeSpan,
    @SerializedName("name")
    val name: String,
    @SerializedName("sort-name")
    val sortName: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("type-id")
    val typeId: String
)
