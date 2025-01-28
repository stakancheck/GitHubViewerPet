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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.stakancheck.githubviewer.ui.components.ShimmerPlaceHolder
import io.github.stakancheck.githubviewer.ui.components.Spacer
import io.github.stakancheck.githubviewer.ui.values.Dimens
import io.github.stakancheck.githubviewer.ui.values.IconSize
import io.github.stakancheck.githubviewer.ui.values.Radius


@Composable
fun PlaceHolderContentItem(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(Dimens.spaceMedium),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ShimmerPlaceHolder(
            modifier = Modifier.size(IconSize.Medium),
            clip = Radius.small,
        )

        Spacer(Dimens.spaceMedium)

        ShimmerPlaceHolder(
            modifier = Modifier
                .height(IconSize.Medium)
                .width(128.dp),
            clip = Radius.small,
        )
    }
}