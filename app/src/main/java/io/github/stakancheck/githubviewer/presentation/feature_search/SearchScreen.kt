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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.dp
import coil3.size.Dimension
import io.github.stakancheck.githubviewer.domain.models.RepositoryModel
import io.github.stakancheck.githubviewer.domain.models.UserModel
import io.github.stakancheck.githubviewer.domain.utils.ListState
import io.github.stakancheck.githubviewer.presentation.feature_search.components.RepositoryCard
import io.github.stakancheck.githubviewer.presentation.feature_search.components.SearchBar
import io.github.stakancheck.githubviewer.presentation.feature_search.components.UserCard
import io.github.stakancheck.githubviewer.ui.values.Dimens
import org.koin.androidx.compose.koinViewModel


@Composable
fun SearchScreen(
    navigateToRepositoryScreen: (repositoryId: Int) -> Unit,
    navigateBack: () -> Unit,
) {
    val viewModel = koinViewModel<SearchScreenViewModel>()

    val searchText by viewModel.searchQuery.collectAsState()
    val searchResultsItem by viewModel.searchResultsItem.collectAsState()

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

        val lazyColumnListState = rememberLazyListState()
        val listState by viewModel.listState.collectAsState()
        val shouldStartPaginate = remember {
            derivedStateOf {
                (lazyColumnListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -9) >= (lazyColumnListState.layoutInfo.totalItemsCount - 6)
            }
        }

        LaunchedEffect(shouldStartPaginate.value) {
            viewModel.onEvent(SearchScreenContract.Event.OnPaginationReached)
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            state = lazyColumnListState,
            contentPadding = PaddingValues(Dimens.spaceSmall),
            verticalArrangement = Arrangement.spacedBy(Dimens.spaceSmall),
        ) {
            items(searchResultsItem, key = { it.uniq_id }) {
                when(it) {
                    is RepositoryModel -> {
                        RepositoryCard(
                            model = it,
                            onClick = { navigateToRepositoryScreen(it.id) },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                    is UserModel -> {
                        UserCard(
                            avatarUrl = it.avatarUrl,
                            name = it.login,
                            onClick = { },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                }
            }

            when(listState) {
                ListState.LOADING -> {
                    items(3) {
                        Text("Loading")
                    }
                }
                ListState.PAGINATING -> {
                    items(3) {
                        Text("Loading paginating")
                    }
                }
                ListState.PAGINATION_EXHAUST -> items(3) {
                    Text("Loading PAGINATION_EXHAUST")
                }
                else -> {}
            }
        }
    }
}
