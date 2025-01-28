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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.stakancheck.githubviewer.R
import io.github.stakancheck.githubviewer.common.error.getAnimationResource
import io.github.stakancheck.githubviewer.common.error.getErrorDescriptionResource
import io.github.stakancheck.githubviewer.common.error.getErrorMessageResource
import io.github.stakancheck.githubviewer.domain.models.RepositoryModel
import io.github.stakancheck.githubviewer.domain.models.SearchResultsItem
import io.github.stakancheck.githubviewer.domain.models.UserModel
import io.github.stakancheck.githubviewer.domain.utils.ListState
import io.github.stakancheck.githubviewer.presentation.common.components.AnimationStateViewBox
import io.github.stakancheck.githubviewer.presentation.feature_search.components.RepositoryCard
import io.github.stakancheck.githubviewer.presentation.feature_search.components.SearchBar
import io.github.stakancheck.githubviewer.presentation.common.components.StateInfo
import io.github.stakancheck.githubviewer.presentation.feature_search.components.UserCard
import io.github.stakancheck.githubviewer.ui.components.ShimmerPlaceHolder
import io.github.stakancheck.githubviewer.ui.values.Dimens
import io.github.stakancheck.githubviewer.ui.values.Radius
import org.koin.androidx.compose.koinViewModel


@Composable
fun SearchScreen(
    navigateToRepositoryScreen: (repoFullName: String) -> Unit,
    navigateToUrl: (url: String) -> Unit,
    navigateBack: () -> Unit,
) {
    val viewModel = koinViewModel<SearchScreenViewModel>()

    val searchText by viewModel.searchQuery.collectAsState()
    val searchResultsItem by viewModel.searchResultsItem.collectAsState()
    val state by viewModel.state.collectAsState()
    val listState by viewModel.listState.collectAsState()
    val lazyColumnListState = rememberLazyListState()
    val shouldStartPaginate = remember {
        derivedStateOf {
            (lazyColumnListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                ?: -9) >= (lazyColumnListState.layoutInfo.totalItemsCount - 6)
        }
    }

    LaunchedEffect(shouldStartPaginate.value) {
        viewModel.onEvent(SearchScreenContract.Event.OnPaginationReached)
    }

    Scaffold(
        topBar = {
            SearchBar(
                searchText = searchText,
                onSearchChange = {
                    viewModel.onEvent(SearchScreenContract.Event.OnSearchChanged(it))
                },
                onBackClick = navigateBack,
            )
        },
        contentWindowInsets = WindowInsets.safeDrawing,
    ) { innerPadding ->

        with(state) {
            when (this) {
                SearchScreenContract.State.EmptyResult -> {
                    StateInfo(
                        icon = {
                            AnimationStateViewBox(R.raw.nothing_found_animation)
                        },
                        title = stringResource(R.string.no_results),
                        description = stringResource(R.string.no_results_description),
                        modifier = Modifier.padding(innerPadding),
                    )
                }

                is SearchScreenContract.State.Error -> {
                    StateInfo(
                        icon = {
                            AnimationStateViewBox(this.error.getAnimationResource())
                        },
                        title = stringResource(this.error.getErrorMessageResource()),
                        description = stringResource(this.error.getErrorDescriptionResource()),
                        modifier = Modifier.padding(innerPadding),
                    )
                }

                SearchScreenContract.State.Idle -> {
                    StateInfo(
                        icon = {
                            AnimationStateViewBox(R.raw.start_searching)
                        },
                        title = stringResource(R.string.search),
                        description = stringResource(R.string.start_searching),
                        modifier = Modifier.padding(innerPadding),
                    )
                }

                SearchScreenContract.State.Success -> {
                    ResultsList(
                        lazyListState = lazyColumnListState,
                        listState = listState,
                        searchResultsItem = searchResultsItem,
                        modifier = Modifier.padding(innerPadding),
                        onRepositoryClick = {
                            navigateToRepositoryScreen(it.fullName)
                        },
                        onUserClick = {
                            navigateToUrl(it.url)
                        },
                    )
                }
            }
        }
    }
}


@Composable
private fun ResultsList(
    lazyListState: LazyListState,
    listState: ListState,
    searchResultsItem: List<SearchResultsItem>,
    modifier: Modifier = Modifier,
    onRepositoryClick: (RepositoryModel) -> Unit = {},
    onUserClick: (UserModel) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        state = lazyListState,
        contentPadding = PaddingValues(Dimens.spaceSmall),
        verticalArrangement = Arrangement.spacedBy(Dimens.spaceSmall),
    ) {
        items(searchResultsItem, key = { it.uniq_id }) {
            when (it) {
                is RepositoryModel -> {
                    RepositoryCard(
                        model = it,
                        onClick = {
                            onRepositoryClick(it)
                        },
                        onUserCLick = {
                            if (it.owner != null) {
                                onUserClick(it.owner)
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                    )
                }

                is UserModel -> {
                    UserCard(
                        avatarUrl = it.avatarUrl,
                        name = it.login,
                        onClick = {
                            onUserClick(it)
                        },
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }

        when (listState) {
            ListState.PAGINATION_EXHAUST -> items(3) {
                Text(
                    text = stringResource(R.string.no_more_results),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dimens.spaceMedium),
                )
            }

            ListState.LOADING -> {
                items(3) {
                    ShimmerPlaceHolder(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(84.dp),
                        clip = Radius.small,
                    )
                }
            }

            else -> {}
        }
    }
}
