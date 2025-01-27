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

package io.github.stakancheck.githubviewer.data.mappers

import io.github.stakancheck.githubviewer.common.error.DataError
import kotlinx.serialization.SerializationException
import okio.IOException
import retrofit2.HttpException

class ApiThrowableToDataErrorMapper {
    companion object {
        operator fun invoke(throwable: Throwable): DataError.Network {
            return when (throwable) {
                is java.net.UnknownHostException -> DataError.Network.NO_INTERNET
                is IOException -> DataError.Network.NO_INTERNET
                is SerializationException -> DataError.Network.SERIALIZATION
                is HttpException -> when (throwable.code()) {
                    401 -> DataError.Network.UNAUTHORIZED
                    500 -> DataError.Network.SERVER_ERROR
                    408 -> DataError.Network.REQUEST_TIMEOUT
                    else -> DataError.Network.UNKNOWN
                }

                else -> DataError.Network.UNKNOWN
            }
        }
    }
}
