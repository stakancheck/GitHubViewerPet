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

package io.github.stakancheck.githubviewer.presentation.feature_repository_content

import android.util.Log
import androidx.lifecycle.viewModelScope
import io.github.stakancheck.githubviewer.common.error.ifError
import io.github.stakancheck.githubviewer.common.error.ifSuccess
import io.github.stakancheck.githubviewer.domain.usecases.RetrieveRepositoryContentsUseCase
import io.github.stakancheck.githubviewer.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class RepositoryContentViewModel(
    private val repoFullName: String,
    private val retrieveRepositoryContentsUseCase: RetrieveRepositoryContentsUseCase,
) : BaseViewModel<RepositoryContentContract.Event, RepositoryContentContract.Effect>() {

    private val _state =
        MutableStateFlow<RepositoryContentContract.State>(RepositoryContentContract.State.Idle)
    val state = _state.asStateFlow()

    override fun onEvent(event: RepositoryContentContract.Event) {
        when (event) {
            RepositoryContentContract.Event.OnStart -> handleOpenContentItem("")
            is RepositoryContentContract.Event.OnOpenContentItem -> handleOpenContentItem(event.item.path)
        }
    }

    private fun handleOpenContentItem(path: String) {
        viewModelScope.launch {
            _state.value = RepositoryContentContract.State.Loading
            with(retrieveRepositoryContentsUseCase(repoFullName, path)) {
                ifSuccess { result ->
                    _state.update { RepositoryContentContract.State.Success(path, result.data) }
                }
                ifError {
                    _state.update { RepositoryContentContract.State.Error }
                }
            }
            Log.d(TAG, "handleOpenContentItem: path -> $path")
        }
    }

    companion object {
        const val TAG = "RepositoryContentViewModel"
    }
}
