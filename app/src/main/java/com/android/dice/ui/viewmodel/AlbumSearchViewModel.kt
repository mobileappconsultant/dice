package com.android.dice.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.map
import com.android.dice.domain.mapper.AlbumMapper
import com.android.dice.domain.usecase.GetAlbumUseCase
import com.android.dice.ui.model.Album
import com.android.dice.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumSearchViewModel @Inject constructor(
    private val albumMapper: AlbumMapper,
    private val getAlbumUseCase: GetAlbumUseCase,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    private val _result = MutableStateFlow<List<Album>>(emptyList())
    val result = _result.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    fun getAlbum(artist: String) {
        viewModelScope.launch(dispatchers.main) {
            _loading.value = true
            _result.value = getAlbumUseCase.execute(artist).map { albumDomain -> albumMapper.mapToPresentation(albumDomain) }
            _loading.value = false
        }
    }
}
