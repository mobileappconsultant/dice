package com.android.dice.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.android.dice.domain.mapper.ArtistMapper
import com.android.dice.domain.usecase.SearchArtistUseCase
import com.android.dice.ui.model.Artist
import com.android.dice.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val artistMapper: ArtistMapper,
    private val searchArtistUseCase: SearchArtistUseCase,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    private val _searchResult = MutableStateFlow<PagingData<Artist>>(PagingData.empty())
    val searchResult = _searchResult.asStateFlow()

    fun searchArtist(query: String) {
        _searchResult.value = PagingData.empty()
        viewModelScope.launch(dispatchers.main) {
            // Delay is necessary to reset the pagedlist before sending another request
            delay(LIST_RESET_DELAY)
            searchArtistUseCase.execute(query)
                .cachedIn(viewModelScope)
                .collect {
                    _searchResult.value = it.map { domain ->
                        artistMapper.mapToPresentation(domain)
                    }
                }
        }
    }

    companion object {
        private const val LIST_RESET_DELAY = 100L
    }
}
