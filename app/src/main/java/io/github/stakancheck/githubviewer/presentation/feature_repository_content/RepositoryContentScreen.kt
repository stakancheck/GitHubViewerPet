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

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.stakancheck.githubviewer.presentation.feature_repository_content.components.RepositoryContentTopAppBar


@Composable
fun RepositoryContentScreen(
    initialRepositoryId: Int,
    navigateToUrl: (url: String) -> Unit,
    navigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            RepositoryContentTopAppBar(
                title = "Repository",
                onBackClick = navigateBack
            )
        },
        contentWindowInsets = WindowInsets.safeDrawing,
    ) { innerPadding ->
        Text(
            text = "Repository content",
            modifier = Modifier.padding(innerPadding)
        )
    }
}
