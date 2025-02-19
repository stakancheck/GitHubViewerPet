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

package io.github.stakancheck.githubviewer.presentation.feature_search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import io.github.stakancheck.githubviewer.ui.components.Spacer
import io.github.stakancheck.githubviewer.ui.values.Dimens
import io.github.stakancheck.githubviewer.ui.values.shapes


@Composable
fun TextBadge(
    modifier: Modifier = Modifier,
    parameter: String,
    value: String,
) {
    Box(
        modifier = modifier
            .clip(shapes.small)
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant) {
            Row (
                modifier = Modifier
                    .padding(
                        vertical = Dimens.spaceExtraSmall,
                        horizontal = Dimens.spaceSmall,
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    color = MaterialTheme.colorScheme.outline,
                    text = parameter,
                    style = MaterialTheme.typography.bodyMedium,
                )

                Spacer(Dimens.spaceExtraSmall)

                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}