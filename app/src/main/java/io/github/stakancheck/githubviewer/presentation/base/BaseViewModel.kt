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

package io.github.stakancheck.githubviewer.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.core.component.KoinComponent


abstract class BaseViewModel<Event, Effect> : ViewModel(), KoinComponent {
    // A state flow that represents the updating state of the ViewModel.
    private val _updating = MutableStateFlow(false)

    // A public read-only view of the updating state.
    val updating = _updating.asStateFlow()

    // A channel for effects that the view will handle.
    private val _effects: Channel<Effect> = Channel(Channel.BUFFERED)

    // A public read-only view of the effects channel as a flow.
    val effects: Flow<Effect> get() = _effects.receiveAsFlow()

    /**
     * Sets the updating state to true.
     * This should be called when the ViewModel starts updating its state.
     */
    fun onUpdateState() {
        _updating.value = true
    }

    /**
     * Sets the updating state to false.
     * This should be called when the ViewModel finishes updating its state.
     */
    fun onUpdatedState() {
        _updating.value = false
    }

    /**
     * Sends an effect to the effect channel.
     * This should be called when there is a new action that the view should handle.
     *
     * @param effect The effect to be handled.
     */
    suspend fun launchEffect(effect: Effect) {
        _effects.send(effect)
    }

    /**
     * Handle event from ui.
     * This should be called when there is a new action from view.
     *
     * @param event The event to be handled.
     */
    abstract fun onEvent(event: Event)
}
