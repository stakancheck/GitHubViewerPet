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

package io.github.stakancheck.githubviewer.domain.utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import io.github.stakancheck.githubviewer.common.error.DataError
import io.github.stakancheck.githubviewer.common.error.Result
import io.github.stakancheck.githubviewer.common.error.ifError
import io.github.stakancheck.githubviewer.common.error.ifSuccess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PagingManager<T>(
    private val coroutineScope: CoroutineScope,
    private val paginationSize: Int,
    private val onDataLoaded: (page: Int, list: List<T>) -> Unit,
    private val onStateChanged: (ListState) -> Unit,
) {
    private var page by mutableIntStateOf(1)
    private var canPaginate by mutableStateOf(false)
    private var listState by mutableStateOf(ListState.IDLE)

    fun loadNextPage(
        loadData: suspend (Int) -> Result<List<T>, DataError>,
    ) = coroutineScope.launch {
        if (page == 1 || (page != 1 && canPaginate) && listState == ListState.IDLE) {
            listState = if (page == 1) ListState.LOADING else ListState.PAGINATING
            onStateChanged(listState)

            val result = loadData(page)

            result.ifSuccess {
                canPaginate = it.data.size == paginationSize

                onDataLoaded(page, it.data)

                listState = ListState.IDLE
                onStateChanged(listState)

                if (canPaginate) {
                    page++
                }
            }.ifError {
                listState = if (page == 1) ListState.ERROR else ListState.PAGINATION_EXHAUST
                onStateChanged(listState)
            }
        }
    }

    fun reset() {
        page = 1
        listState = ListState.IDLE
        canPaginate = false
    }
}

enum class PagingResultStatus {
    OK, ERROR
}

enum class ListState {
    IDLE, LOADING, PAGINATING, ERROR, PAGINATION_EXHAUST
}