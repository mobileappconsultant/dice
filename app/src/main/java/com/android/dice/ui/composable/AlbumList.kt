package com.android.dice.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.android.dice.R
import com.android.dice.ui.model.Album
import com.android.dice.ui.theme.primaryTextColor

@Composable
fun AlbumList(albums: List<Album>) {
    if (albums.isNotEmpty()) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray.copy(alpha = 0.3f))
            ) {
                Text(
                    text = stringResource(R.string.albums), modifier = Modifier.padding(8.dp)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            albums.forEach {
                AlbumItem(it)
            }
        }
    }
}

@Composable
fun AlbumItem(album: Album) {
    Column(Modifier.fillMaxWidth()) {
        Text(
            text = album.name,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colors.primaryTextColor
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = album.dateReleased,
            style = MaterialTheme.typography.body2,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colors.primaryTextColor
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}
