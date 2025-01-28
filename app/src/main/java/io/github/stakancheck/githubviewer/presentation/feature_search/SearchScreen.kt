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

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import io.github.stakancheck.githubviewer.presentation.feature_search.components.SearchBar


@Composable
fun SearchScreen(
    navigateToRepositoryScreen: (repositoryId: Int) -> Unit,
    navigateBack: () -> Unit,
) {
    var searchText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            SearchBar(
                searchText = searchText,
                onSearchChange = { searchText = it },
                onBackClick = navigateBack,
            )
        },
    ) { innerPadding ->
        Text(
            "Test",
            modifier = Modifier.padding(innerPadding)
        )
    }
}

