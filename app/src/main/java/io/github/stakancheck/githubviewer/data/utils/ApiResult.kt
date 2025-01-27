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

package io.github.stakancheck.githubviewer.data.utils

import io.github.stakancheck.githubviewer.di.json
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import okio.IOException
import retrofit2.HttpException

/**
 * A sealed class representing the result of an API call.
 *
 * @param T The type of the data returned in case of success.
 */
sealed interface ApiResult<out T> {
    /**
     * Represents a successful API call.
     *
     * @property data The data returned by the API call.
     */
    data class Success<out T>(val data: T) : ApiResult<T>

    /**
     * Represents an exception of API call.
     *
     * @property error The error trawable.
     */
    data class Error(val error: Throwable, val code: Int?) : ApiResult<Nothing>

    companion object {

        /**
         * Executes the given [requestFunc] and wraps its result into an [ApiResult].
         *
         * @param R The type of the data returned in case of success.
         * @param requestFunc The function to execute.
         * @return An [ApiResult] representing the result of the [requestFunc].
         */
        suspend inline fun <reified R> withCatching(
            dispatcher: CoroutineDispatcher,
            crossinline requestFunc: suspend () -> R
        ): ApiResult<R> {
            return withContext(dispatcher) {
                try {
                    val response = requestFunc()
                    Success(response)
                } catch (e: HttpException) {
                    Error(e, e.code())
                } catch (e: Throwable) {
                    Error(e, null)
                }
            }
        }

        val <D : Any> ApiResult<D>.isSuccess: Boolean
            get() = this is Success

        val <D : Any> ApiResult<D>.isError: Boolean
            get() = this is Error

        fun <D : Any> ApiResult<D>.getOrThrow(): D {
            return when (this) {
                is Success -> data
                is Error -> error(this.error)
            }
        }
    }
}
