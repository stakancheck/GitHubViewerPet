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

package io.github.stakancheck.githubviewer.domain.repository

import io.github.stakancheck.githubviewer.common.error.DataError
import io.github.stakancheck.githubviewer.common.error.Result
import io.github.stakancheck.githubviewer.data.dto.GHApiRepositoryContentResponseDTO
import io.github.stakancheck.githubviewer.data.dto.GHApiSearchRepositoriesResponseDTO
import io.github.stakancheck.githubviewer.data.dto.GHApiSearchUsersResponseDTO

interface GitHubRepository {

    /**
     * Search repositories by query.
     *
     * @param query search query.
     * @param sort sort repositories by this field.
     * @param order sort order.
     * @param page page number.
     * @param perPage number of items per page.
     * @return search results in [GHApiSearchRepositoriesResponseDTO].
     */
    suspend fun searchRepositories(
        query: String,
        sort: String? = null,
        order: String? = null,
        page: Int,
        perPage: Int
    ): Result<GHApiSearchRepositoriesResponseDTO, DataError.Network>


    /**
     * Search users by query.
     *
     * @param query search query.
     * @param sort sort users by this field.
     * @param order sort order.
     * @param page page number.
     * @param perPage number of items per page.
     * @return search results in [GHApiSearchUsersResponseDTO].
     */
    suspend fun searchUsers(
        query: String,
        sort: String? = null,
        order: String? = null,
        page: Int,
        perPage: Int
    ): Result<GHApiSearchUsersResponseDTO, DataError.Network>


    /**
     * Get repository content by full name and path.
     *
     * @param repoFullName full name of the repository, ex. "user/repo".
     * @param path path to the content, ex. "dir1/dir2/dir3".
     * @return contents response in [GHApiRepositoryContentResponseDTO].
     */
    suspend fun getRepositoryContent(
        repoFullName: String,
        path: String,
    ): Result<GHApiRepositoryContentResponseDTO, DataError.Network>
}
