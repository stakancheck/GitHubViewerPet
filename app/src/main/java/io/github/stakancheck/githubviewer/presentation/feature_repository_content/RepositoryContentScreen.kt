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

package io.github.stakancheck.githubviewer.presentation.feature_repository_content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import io.github.stakancheck.githubviewer.domain.models.ContentTree
import io.github.stakancheck.githubviewer.presentation.feature_repository_content.components.ContentItem
import io.github.stakancheck.githubviewer.presentation.feature_repository_content.components.RepositoryContentTopAppBar
import io.github.stakancheck.githubviewer.ui.values.Dimens
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf


typealias OnEvent = (RepositoryContentContract.Event) -> Unit

@Composable
fun RepositoryContentScreen(
    repoFullName: String,
    navigateToUrl: (url: String) -> Unit,
    navigateBack: () -> Unit,
) {
    val viewModel = koinViewModel<RepositoryContentViewModel>(
        parameters = { parametersOf(repoFullName) }
    )

    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onEvent(RepositoryContentContract.Event.OnStart)
    }

    Scaffold(
        topBar = {
            RepositoryContentTopAppBar(
                title = if (state is RepositoryContentContract.State.Success) {
                    (state as RepositoryContentContract.State.Success).currentContentName
                } else {
                    repoFullName
                },
                onBackClick = navigateBack
            )
        },
        contentWindowInsets = WindowInsets.safeDrawing,
    ) { innerPadding ->
        with(state) {
            when (this) {
                RepositoryContentContract.State.Error -> {}
                RepositoryContentContract.State.Loading -> {}
                is RepositoryContentContract.State.Success -> {
                    RepositoryContentsTree(
                        content = content,
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxWidth(),
                        onEvent = { event ->
                            viewModel.onEvent(event)
                        }
                    )
                }

                RepositoryContentContract.State.Idle -> {}
            }
        }

    }
}


@Composable
private fun RepositoryContentsTree(
    content: ContentTree,
    modifier: Modifier = Modifier,
    onEvent: OnEvent,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Dimens.spaceSmall),
        contentPadding = PaddingValues(
            vertical = Dimens.spaceSmall
        )
    ) {
        items(content, key = { it.path }) {
            ContentItem(
                name = it.name,
                size = it.size,
                type = it.type,
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onEvent(RepositoryContentContract.Event.OnOpenContentItem(it))
                }
            )

            HorizontalDivider(
                modifier = Modifier
                    .padding(horizontal = Dimens.spaceSmall)
                    .fillMaxWidth()
            )
        }
    }
}


