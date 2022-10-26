package com.android.dice.domain.model

data class ArtistDomain(
    val id: String,
    val country: String,
    val gender: String,
    val name: String,
    val disambiguation: String,
    val type: String,
    val tags: List<String>,
    val score: Int
)
