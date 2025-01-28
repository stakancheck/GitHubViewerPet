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

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.github.stakancheck.githubviewer.R
import io.github.stakancheck.githubviewer.domain.models.RepositoryModel
import io.github.stakancheck.githubviewer.ui.components.Spacer
import io.github.stakancheck.githubviewer.ui.icons.IconPack
import io.github.stakancheck.githubviewer.ui.icons.iconpack.ChevronRight
import io.github.stakancheck.githubviewer.ui.icons.iconpack.Eye
import io.github.stakancheck.githubviewer.ui.icons.iconpack.GitFork
import io.github.stakancheck.githubviewer.ui.icons.iconpack.Star
import io.github.stakancheck.githubviewer.ui.values.Dimens
import io.github.stakancheck.githubviewer.ui.values.IconSize
import io.github.stakancheck.githubviewer.ui.values.shapes


@Composable
fun RepositoryCard(
    model: RepositoryModel,
    modifier: Modifier = Modifier,
    cardColors: CardColors = CardDefaults.elevatedCardColors(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
    ),
    onClick: () -> Unit,
) {
    ElevatedCard(
        modifier = modifier,
        colors = cardColors,
        onClick = onClick,
        shape = shapes.small,
    ) {
        RepositoryCardContent(
            model = model,
            modifier = Modifier.padding(Dimens.spaceMedium),
        )
    }
}


@Composable
private fun RepositoryCardContent(
    model: RepositoryModel,
    modifier: Modifier = Modifier,
) {
    var openedDetails by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier.animateContentSize(),
    ) {
        RepositoryCardHeader(
            repositoryName = model.name,
            starsCount = model.stargazersCount,
            watchersCount = model.watchersCount,
            forksCount = model.forksCount,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Dimens.spaceSmall)

        DetailsButton(
            openedDetails = openedDetails,
            modifier = Modifier.fillMaxWidth(),
        ) {
            openedDetails = !openedDetails
        }

        if (openedDetails) {
            RepositoryDetails(model)
        }
    }
}

@Composable
private fun RepositoryCardHeader(
    repositoryName: String,
    starsCount: Int,
    watchersCount: Int,
    forksCount: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(Dimens.spaceSmall),
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = repositoryName,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )

        Row {
            InfoBadge(
                icon = IconPack.Star,
                text = starsCount.toString(),
            )

            Spacer(Dimens.spaceSmall)

            InfoBadge(
                icon = IconPack.Eye,
                text = watchersCount.toString(),
            )

            Spacer(Dimens.spaceSmall)

            InfoBadge(
                icon = IconPack.GitFork,
                text = forksCount.toString(),
            )
        }
    }
}


@Composable
private fun ColumnScope.RepositoryDetails(
    model: RepositoryModel,
) {

    Spacer(Dimens.spaceSmall)

    if (model.ownerName != null) {
        UserCard(
            avatarUrl = model.avatarUrl,
            name = model.ownerName,
            cardColors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // TODO: Navigate to user profile
        }
    }

    Spacer(Dimens.spaceSmall)

    TextBadge(
        parameter = stringResource(R.string.created_at),
        value = model.createdAt,
    )

    Spacer(Dimens.spaceSmall)

    TextBadge(
        parameter = stringResource(R.string.updated_at),
        value = model.updatedAt,
    )

    if (model.description != null) {
        Spacer(Dimens.spaceSmall)

        Text(
            text = stringResource(R.string.description),
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
        )

        Spacer(Dimens.spaceExtraSmall)

        Text(
            text = model.description,
            color = MaterialTheme.colorScheme.outline,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}


@Composable
private fun DetailsButton(
    openedDetails: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val iconDegree by animateFloatAsState(if (openedDetails) 90f else 0f, label = "iconDegree")

    Row(
        modifier = modifier
            .clip(shapes.extraSmall)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.more_details),
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
        )

        Spacer(Dimens.spaceSmall)

        Icon(
            modifier = Modifier
                .size(IconSize.Medium)
                .rotate(iconDegree),
            imageVector = IconPack.ChevronRight,
            contentDescription = null,
        )
    }
}


