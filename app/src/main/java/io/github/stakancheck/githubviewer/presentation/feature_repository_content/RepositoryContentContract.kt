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

import io.github.stakancheck.githubviewer.domain.models.ContentItemModel
import io.github.stakancheck.githubviewer.domain.models.ContentTree

object RepositoryContentContract {
    sealed interface Event {
        object OnStart : Event
        object OnBackHandle : Event
        data class OnOpenContentItem(val item: ContentItemModel) : Event
    }

    sealed interface Effect {
        object NavigateBack : Effect
        data class NavigateToUrl(val url: String) : Effect
    }

    sealed interface State {
        object Idle : State
        object Loading : State
        object Error : State
        data class Success(val content: ContentTree) : State
    }
}
