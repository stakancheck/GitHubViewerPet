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

package io.github.stakancheck.githubviewer.domain.usecases

import io.github.stakancheck.githubviewer.common.error.DataError
import io.github.stakancheck.githubviewer.common.error.Result
import io.github.stakancheck.githubviewer.common.error.map
import io.github.stakancheck.githubviewer.domain.mappers.RepositoryDTOToRepositoryModelMapper
import io.github.stakancheck.githubviewer.domain.mappers.UserItemDTOToUserModelMapper
import io.github.stakancheck.githubviewer.domain.models.RepositoryModel
import io.github.stakancheck.githubviewer.domain.models.SearchResultsItem
import io.github.stakancheck.githubviewer.domain.models.UserModel
import io.github.stakancheck.githubviewer.domain.repository.GitHubRepository

class SearchRepositoriesAndUsersUseCase(
    private val gitHubRepository: GitHubRepository,
) {
    suspend operator fun invoke(
        query: String,
        page: Int = 1
    ): Result<List<SearchResultsItem>, DataError.Network> {

        // Getting repositories
        val reposResult: Result<List<RepositoryModel>, DataError.Network> =
            gitHubRepository.searchRepositories(
                query = query,
                page = page,
                perPage = DEFAULT_PAGE_SIZE
            ).map {
                it.items.map(RepositoryDTOToRepositoryModelMapper::invoke)
            }

        // Getting users
        val usersResult: Result<List<UserModel>, DataError.Network> =
            gitHubRepository.searchUsers(
                query = query,
                page = page,
                perPage = DEFAULT_PAGE_SIZE
            ).map {
                it.items.map(UserItemDTOToUserModelMapper::invoke)
            }


        // Checking for errors
        if (reposResult is Result.Error || usersResult is Result.Error) {
            return reposResult
        }

        require(reposResult is Result.Success && usersResult is Result.Success)

        // Merging results and sort by sortValue
        val resultList = buildList {
            addAll(reposResult.data)
            addAll(usersResult.data)
        }.sortedBy { it.sortValue }

        return Result.success(resultList)
    }

    companion object {
        const val DEFAULT_PAGE_SIZE = 30
        const val TOTAL_RESULTS_PER_PAGE = DEFAULT_PAGE_SIZE * 2
    }
}
