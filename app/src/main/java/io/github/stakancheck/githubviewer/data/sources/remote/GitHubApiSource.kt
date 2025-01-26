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

package io.github.stakancheck.githubviewer.data.sources.remote

import androidx.annotation.IntRange
import io.github.stakancheck.githubviewer.data.dto.GHApiSearchRepositoriesResponseDTO
import io.github.stakancheck.githubviewer.data.dto.GHApiSearchUsersResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * GitHub API source.
 */
interface GitHubApiSource {

    /**
     * Search repositories by query.
     *
     * @param query search query.
     * @param sort sort repositories by this field.
     * @param order sort order.
     * @param page page number.
     * @param perPage number of items per page.
     * @return search results in [GHApiSearchRepositoriesResponseDTO].
     *
     * See [GitHub API documentation](https://docs.github.com/en/rest/search/search?apiVersion=2022-11-28#search-repositories).
     */
    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("order") order: String,
        @Query("page") page: Int,
        @Query("per_page") @IntRange(1, 100) perPage: Int
    ): GHApiSearchRepositoriesResponseDTO

    /**
     * Search users by query.
     *
     * @param query search query.
     * @param sort sort users by this field.
     * @param order sort order.
     * @param page page number.
     * @param perPage number of items per page.
     * @return search results in [GHApiSearchUsersResponseDTO].
     *
     * See [GitHub API documentation](https://docs.github.com/en/rest/search/search?apiVersion=2022-11-28#search-users).
     */
    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("order") order: String,
        @Query("page") page: Int,
        @Query("per_page") @IntRange(1, 100) perPage: Int
    ): GHApiSearchUsersResponseDTO
}
