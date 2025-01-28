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

package io.github.stakancheck.githubviewer.data.repository

import android.util.Log
import io.github.stakancheck.githubviewer.common.error.DataError
import io.github.stakancheck.githubviewer.common.error.Result
import io.github.stakancheck.githubviewer.data.dto.GHApiSearchRepositoriesResponseDTO
import io.github.stakancheck.githubviewer.data.dto.GHApiSearchUsersResponseDTO
import io.github.stakancheck.githubviewer.data.mappers.ApiThrowableToDataErrorMapper
import io.github.stakancheck.githubviewer.data.sources.remote.GitHubApiSource
import io.github.stakancheck.githubviewer.data.utils.ApiResult
import io.github.stakancheck.githubviewer.domain.repository.GitHubRepository
import kotlinx.coroutines.CoroutineDispatcher

class GitHubRepositoryImpl(
    private val apiSource: GitHubApiSource,
    private val dispatcher: CoroutineDispatcher = GitHubApiSource.DEFAULT_DISPATCHER,
) : GitHubRepository {

    override suspend fun searchRepositories(
        query: String,
        sort: String?,
        order: String?,
        page: Int,
        perPage: Int
    ): Result<GHApiSearchRepositoriesResponseDTO, DataError.Network> {
        val response = ApiResult.withCatching(
            dispatcher = dispatcher
        ) {
            apiSource.searchRepositories(query, sort, order, page, perPage)
        }
        return when (response) {
            is ApiResult.Error -> {
                Log.e(TAG, "searchRepositories: ${response.error}")
                Result.error(ApiThrowableToDataErrorMapper(response.error))
            }

            is ApiResult.Success -> {
                Result.success(response.data)
            }
        }
    }

    override suspend fun searchUsers(
        query: String,
        sort: String?,
        order: String?,
        page: Int,
        perPage: Int
    ): Result<GHApiSearchUsersResponseDTO, DataError.Network> {
        val response = ApiResult.withCatching(
            dispatcher = dispatcher
        ) {
            apiSource.searchUsers(query, sort, order, page, perPage)
        }
        return when (response) {
            is ApiResult.Error -> {
                Log.e(TAG, "searchUsers: ${response.error}")
                Result.error(ApiThrowableToDataErrorMapper(response.error))
            }

            is ApiResult.Success -> {
                Result.success(response.data)
            }
        }
    }

    companion object {
        private const val TAG = "GitHubRepositoryImpl"
    }
}
