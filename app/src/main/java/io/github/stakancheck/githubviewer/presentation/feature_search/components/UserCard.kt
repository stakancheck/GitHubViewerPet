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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.stakancheck.githubviewer.presentation.common.components.AvatarImage
import io.github.stakancheck.githubviewer.ui.components.Spacer
import io.github.stakancheck.githubviewer.ui.icons.IconPack
import io.github.stakancheck.githubviewer.ui.icons.iconpack.ChevronRight
import io.github.stakancheck.githubviewer.ui.values.Dimens
import io.github.stakancheck.githubviewer.ui.values.shapes


@Composable
fun UserCard(
    avatarUrl: String?,
    name: String,
    modifier: Modifier = Modifier,
    cardColors: CardColors = CardDefaults.elevatedCardColors(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
    ),
    onClick: () -> Unit,
) {
    ElevatedCard(
        colors = cardColors,
        modifier = modifier,
        onClick = onClick,
        shape = shapes.small,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.spaceMedium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AvatarImage(
                    imageUrl = avatarUrl,
                )

                Spacer(Dimens.spaceMedium)

                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium,
                )
            }

            Icon(
                imageVector = IconPack.ChevronRight,
                contentDescription = null,
            )
        }
    }
}
