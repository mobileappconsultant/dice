package com.android.dice.ui.composable

import android.content.Context
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import com.android.dice.R
import com.android.dice.ui.model.Artist
import retrofit2.HttpException

@ExperimentalCoilApi
@Composable
fun SearchList(searchResult: LazyPagingItems<Artist>, onArtistClicked: (Artist) -> Unit) {
    LazyColumn {
        item {
            Text(stringResource(R.string.search_result))
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(
            items = searchResult
        ) { result ->
            result?.let {
                SearchListItem(it, onArtistClicked)
                Spacer(Modifier.height(16.dp))
            }
        }
        searchResult.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        FullScreenProgress(Modifier.fillParentMaxSize())
                    }
                }
                loadState.append is LoadState.Loading -> {
                    item {
                        FullScreenProgress(Modifier.fillMaxWidth())
                    }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = searchResult.loadState.refresh as LoadState.Error
                    item {
                        ErrorPage(
                            modifier = Modifier.fillParentMaxSize(),
                            message = getErrorMessage(e.error, LocalContext.current),
                            onClickRetry = { retry() }
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = searchResult.loadState.append as LoadState.Error
                    item {
                        ErrorPage(
                            modifier = Modifier.fillMaxSize(),
                            message = getErrorMessage(e.error, LocalContext.current),
                            onClickRetry = { retry() }
                        )
                    }
                }
                itemCount == 0 -> {
                    item {
                        IdleState(Modifier.fillParentMaxSize())
                    }
                }
            }
        }
    }
}

private fun getErrorMessage(throwable: Throwable, context: Context): String =
    if ((throwable as? HttpException)?.code() == 403) context.getString(R.string.request_limit_reached) else context.getString(
        R.string.an_error_occurred
    )
