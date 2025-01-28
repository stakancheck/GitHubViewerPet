/*
 * Copyright (c) 2025 Artem Sukhanov (Stakancheck)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.stakancheck.githubviewer.presentation.feature_search

import androidx.lifecycle.viewModelScope
import io.github.stakancheck.githubviewer.domain.models.SearchResultsItem
import io.github.stakancheck.githubviewer.domain.usecases.SearchRepositoriesAndUsersUseCase
import io.github.stakancheck.githubviewer.domain.utils.ListState
import io.github.stakancheck.githubviewer.domain.utils.PagingManager
import io.github.stakancheck.githubviewer.presentation.base.BaseViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchScreenViewModel(
    private val searchRepositoriesAndUsersUseCase: SearchRepositoriesAndUsersUseCase,
) : BaseViewModel<SearchScreenContract.Event, SearchScreenContract.Effect>() {
    private val _searchResultItems = MutableStateFlow(emptyList<SearchResultsItem>())
    val searchResultsItem = _searchResultItems.asStateFlow()

    private val _listState = MutableStateFlow(ListState.IDLE)
    val listState = _listState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private var searchJob: Job? = null

    init {
        observeSearchQuery()
    }

    private val pagingManager = PagingManager(
        coroutineScope = viewModelScope,
        paginationSize = SearchRepositoriesAndUsersUseCase.TOTAL_RESULTS_PER_PAGE,
        onDataLoaded = { page, results ->
            if (page == 1) {
                _searchResultItems.update { emptyList() }
            }
            _searchResultItems.update {
                buildList {
                    addAll(it)
                    addAll(results)
                }
            }
        },
        onStateChanged = { state ->
            _listState.value = state
        }
    )

    override fun onEvent(event: SearchScreenContract.Event) {
        when (event) {
            is SearchScreenContract.Event.OnSearchChanged -> handleSearchChanged(event.query)
        }
    }

    private fun handleSearchChanged(query: String) {
        _searchQuery.value = query
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        _searchQuery
            .debounce(300) // 300 ms delay for start searching
            .distinctUntilChanged() // Ignore duplicate queries
            .onEach { query ->
                if (query.length >= SEARCH_QUERY_LENGTH_THRESHOLD) {
                    performSearch(query)
                } else {
                    _searchResultItems.value = emptyList()
                    _listState.value = ListState.IDLE
                }
            }
            .launchIn(viewModelScope)
    }

    private fun performSearch(query: String) {
        searchJob?.cancel() // Cancel last search job if it's still running
        searchJob = viewModelScope.launch {
            pagingManager.reset()
            pagingManager.loadNextPage { page ->
                searchRepositoriesAndUsersUseCase(query, page)
            }
        }
    }

    override fun onCleared() {
        pagingManager.reset()
        super.onCleared()
    }

    companion object {
        const val SEARCH_QUERY_LENGTH_THRESHOLD = 3
    }
}