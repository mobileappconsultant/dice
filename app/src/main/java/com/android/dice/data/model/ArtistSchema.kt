package com.android.dice.data.model

import com.google.gson.annotations.SerializedName

data class ArtistSchema(
    @SerializedName("aliases")
    val aliases: List<Aliase>?,
    @SerializedName("area")
    val area: Area?,
    @SerializedName("begin-area")
    val beginArea: BeginArea?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("disambiguation")
    val disambiguation: String?,
    @SerializedName("end-area")
    val endArea: EndArea?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("gender-id")
    val genderId: String?,
    @SerializedName("id")
    val id: String,
    @SerializedName("ipis")
    val ipis: List<String>?,
    @SerializedName("isnis")
    val isnis: List<String>?,
    @SerializedName("life-span")
    val lifeSpan: LifeSpan,
    @SerializedName("name")
    val name: String,
    @SerializedName("score")
    val score: Int,
    @SerializedName("sort-name")
    val sortName: String,
    @SerializedName("tags")
    val tags: List<Tag>?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("type-id")
    val typeId: String?
)
