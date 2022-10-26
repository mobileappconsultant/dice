package com.android.dice.ui.navigation

sealed class Routes(val route: String) {
    object ArtistScreen : Routes("artistScreen")
    object ArtistDetailsScreen : Routes("artistDetailsScreen?artist={artist}") {
        fun createRoute(artist: String) = "artistDetailsScreen?artist=$artist"
    }
}
