package com.android.dice.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.android.dice.ui.model.Artist
import com.android.dice.ui.screens.artists.ArtistScreen
import com.android.dice.ui.screens.details.ArtistDetailsScreen
import com.android.dice.ui.viewmodel.SearchViewModel
import com.google.gson.Gson

@ExperimentalCoilApi
@Composable
fun MainNavigation(navController: NavHostController) {
    val gson: Gson = remember { Gson() }
    NavHost(
        navController,
        startDestination = Routes.ArtistScreen.route
    ) {

        composable(route = Routes.ArtistScreen.route) {
            val artistsViewModel = hiltViewModel<SearchViewModel>()
            val artists = artistsViewModel.searchResult.collectAsLazyPagingItems()
            ArtistScreen(
                artists = artists,
                onArtistSearch = {
                    artistsViewModel.searchArtist(it)
                }
            ) {
                navController.navigate(Routes.ArtistDetailsScreen.createRoute(gson.toJson(it)))
            }
        }

        composable(
            route = Routes.ArtistDetailsScreen.route,
            arguments = listOf(
                navArgument("artist") {
                    type = NavType.StringType
                }
            )
        ) {
            it.arguments?.getString("artist")?.let { user ->
                ArtistDetailsScreen(gson.fromJson(user, Artist::class.java)) {
                    navController.popBackStack()
                }
            }
        }
    }
}
