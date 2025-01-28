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
    private val onStateChanged: (state: ListState, error: DataError?) -> Unit,
    private val updateCanPaginate: (Boolean) -> Unit = {},
) {
    private var page by mutableIntStateOf(1)
    var canPaginate by mutableStateOf(false)
    private var listState by mutableStateOf(ListState.IDLE)

    fun loadNextPage(
        loadData: suspend (Int) -> Result<List<T>, DataError>,
    ) = coroutineScope.launch {
        if (page == 1 || (page != 1 && canPaginate) && (listState == ListState.IDLE || listState == ListState.EMPTY)) {
            listState = ListState.LOADING
            onStateChanged(listState, null)

            val result = loadData(page)

            result.ifSuccess {
                canPaginate = it.data.size == paginationSize
                updateCanPaginate(canPaginate)

                onDataLoaded(page, it.data)

                listState = if (it.data.isEmpty()) ListState.EMPTY else ListState.IDLE
                onStateChanged(listState, null)

                if (canPaginate) {
                    page++
                }
            }.ifError {
                listState = if (page == 1) ListState.ERROR else ListState.PAGINATION_EXHAUST
                onStateChanged(listState, it.error)
            }
        }
    }

    fun reset() {
        page = 1
        listState = ListState.IDLE
        canPaginate = false
        updateCanPaginate(canPaginate)
    }
}

enum class ListState {
    IDLE, EMPTY, LOADING, ERROR, PAGINATION_EXHAUST
}