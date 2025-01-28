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

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.stakancheck.githubviewer.presentation.feature_search.components.SearchBar
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
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            items(searchResultsItem, key = { it.uniq_id }) {
                Text(
                    text = it.sortValue,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}
