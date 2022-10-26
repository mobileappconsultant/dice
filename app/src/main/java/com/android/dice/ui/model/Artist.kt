package com.android.dice.ui.model

data class Artist(
    val id: String,
    val country: String,
    val gender: String,
    val name: String,
    val disambiguation: String,
    val type: String,
    val tags: List<String>,
    val score: Int
)
