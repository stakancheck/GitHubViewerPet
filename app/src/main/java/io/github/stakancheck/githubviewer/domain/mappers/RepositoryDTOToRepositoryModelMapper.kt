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

package io.github.stakancheck.githubviewer.domain.mappers

import io.github.stakancheck.githubviewer.data.dto.RepositoryDTO
import io.github.stakancheck.githubviewer.domain.models.RepositoryModel
import io.github.stakancheck.githubviewer.domain.models.UserModel
import io.github.stakancheck.githubviewer.domain.utils.DateTimeFormatter

object RepositoryDTOToRepositoryModelMapper {
    operator fun invoke(dto: RepositoryDTO): RepositoryModel {
        return RepositoryModel(
            id = dto.id,
            name = dto.name,
            owner = dto.owner?.let {
                UserModel(
                    id = it.id,
                    login = it.login,
                    avatarUrl = it.avatarUrl,
                    url = it.htmlUrl
                )
            },
            stargazersCount = dto.stargazersCount,
            watchersCount = dto.watchersCount,
            forksCount = dto.forksCount,
            createdAt = DateTimeFormatter(dto.createdAt),
            updatedAt = DateTimeFormatter(dto.updatedAt),
            description = dto.description,
            fullName = dto.fullName,
        )
    }
}
