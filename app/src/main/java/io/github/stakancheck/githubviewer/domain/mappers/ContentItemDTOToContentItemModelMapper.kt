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

import io.github.stakancheck.githubviewer.data.dto.ContentItemDTO
import io.github.stakancheck.githubviewer.domain.models.ContentItemModel
import io.github.stakancheck.githubviewer.domain.models.FileType

object ContentItemDTOToContentItemModelMapper {
    operator fun invoke(dto: ContentItemDTO): ContentItemModel {
        return ContentItemModel(
            name = dto.name,
            path = dto.path,
            size = dto.size.toString(),
            htmlUrl = dto.htmlUrl,
            type = when (dto.type) {
                "file" -> FileType.FILE
                "dir" -> FileType.DIRECTORY
                else -> throw IllegalArgumentException("Unknown file type: ${dto.type}")
            }
        )
    }
}
