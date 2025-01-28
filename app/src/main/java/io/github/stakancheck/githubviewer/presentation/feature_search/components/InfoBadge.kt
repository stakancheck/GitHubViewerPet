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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.stakancheck.githubviewer.ui.components.Spacer
import io.github.stakancheck.githubviewer.ui.icons.IconPack
import io.github.stakancheck.githubviewer.ui.icons.iconpack.Eye
import io.github.stakancheck.githubviewer.ui.icons.iconpack.Star
import io.github.stakancheck.githubviewer.ui.theme.GitHubViewerPetTheme
import io.github.stakancheck.githubviewer.ui.values.Dimens
import io.github.stakancheck.githubviewer.ui.values.IconSize
import io.github.stakancheck.githubviewer.ui.values.shapes


@Composable
fun InfoBadge(
    modifier: Modifier = Modifier,
    text: String,
    icon: ImageVector,
    onClick: (() -> Unit) ? = null,
) {
    Box(
        modifier = modifier
            .clip(shapes.small)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .then(
                if (onClick != null) {
                    Modifier.clickable { onClick() }
                } else {
                    Modifier
                }
            ),
    ) {
        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant) {
            Row (
                modifier = Modifier.padding(Dimens.spaceExtraSmall),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodySmall,
                )

                Spacer(Dimens.spaceExtraSmall)

                Icon(
                    modifier = Modifier.size(IconSize.Small),
                    imageVector = icon,
                    contentDescription = text,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InfoBadgePreview() {
    Column {
        GitHubViewerPetTheme(darkTheme = false) {
            Surface {
                InfoBadge(
                    text = "112",
                    icon = IconPack.Star,
                    onClick = { /* Do something */ }
                )
            }
        }

        GitHubViewerPetTheme(darkTheme = true) {
            Surface {
                InfoBadge(
                    text = "12",
                    icon = IconPack.Eye,
                    onClick = { /* Do something */ }
                )
            }
        }
    }
}
