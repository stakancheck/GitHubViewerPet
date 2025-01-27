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

package io.github.stakancheck.githubviewer.common.error

/**
 * Sealed class for handling success and error states
 *
 * @param D Data type
 * @param E Error type, see [RootError]
 * */
sealed interface Result<out D : Any, out E : RootError> {
    data class Success<out D : Any, out E : RootError>(val data: D) : Result<D, E>
    data class Error<out D : Any, out E : RootError>(val error: E) : Result<D, E>

    companion object {
        fun <D : Any, E : RootError> success(data: D): Result<D, E> = Success(data)

        fun <D : Any, E : RootError> error(error: E): Result<D, E> = Error(error)

        val <D : Any, E : RootError> Result<D, E>.isSuccess: Boolean
            get() = this is Success

        val <D : Any, E : RootError> Result<D, E>.isError: Boolean
            get() = this is Error

        fun <D : Any, E : RootError> Result<D, E>.getOrThrow(): D {
            return when (this) {
                is Success -> data
                is Error -> kotlin.error(this.error)
            }
        }
    }
}

/**
 * Map some data in RootError (use it for changing data type)
 *
 * **Like this:**
 * - DTO -> MODEL
 */
fun <I : Any, O : Any, E : RootError> Result<I, E>.map(mapper: (I) -> O): Result<O, E> {
    return when (this) {
        is Result.Success -> Result.Success(mapper(data))
        is Result.Error -> Result.Error(error)
    }
}
