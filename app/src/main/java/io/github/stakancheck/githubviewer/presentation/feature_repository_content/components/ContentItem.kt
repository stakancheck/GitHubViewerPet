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

package io.github.stakancheck.githubviewer.presentation.feature_repository_content.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import io.github.stakancheck.githubviewer.domain.models.FileType
import io.github.stakancheck.githubviewer.ui.components.Spacer
import io.github.stakancheck.githubviewer.ui.icons.IconPack
import io.github.stakancheck.githubviewer.ui.icons.iconpack.File
import io.github.stakancheck.githubviewer.ui.icons.iconpack.Folder
import io.github.stakancheck.githubviewer.ui.values.Dimens
import io.github.stakancheck.githubviewer.ui.values.IconSize


@Composable
fun ContentItem(
    name: String,
    size: String,
    type: FileType,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier.clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.spaceSmall),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier.size(IconSize.Small),
                imageVector = when(type) {
                    FileType.FILE -> IconPack.File
                    FileType.DIRECTORY -> IconPack.Folder
                },
                contentDescription = type.toString()
            )

            Spacer(Dimens.spaceSmall)

            Text(
                modifier = Modifier
                    .weight(1f)
                    .alignByBaseline(),
                text = name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(Dimens.spaceSmall)

            Text(
                modifier = Modifier.alignByBaseline(),
                color = MaterialTheme.colorScheme.outline,
                text = size,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}
