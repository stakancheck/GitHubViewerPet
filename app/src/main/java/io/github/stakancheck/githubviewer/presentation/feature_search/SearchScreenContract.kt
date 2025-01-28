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

package io.github.stakancheck.githubviewer.presentation.feature_search

import io.github.stakancheck.githubviewer.common.error.DataError

object SearchScreenContract {
    sealed interface Event {
        data class OnSearchChanged(val query: String) : Event
        object OnPaginationReached : Event
    }

    sealed interface Effect

    sealed interface State {
        object Idle : State
        data class Error(val error: DataError) : State
        object EmptyResult : State
        object Success : State
    }
}
