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

import androidx.annotation.RawRes
import androidx.annotation.StringRes
import io.github.stakancheck.githubviewer.R

sealed interface DataError : RootError {
    enum class Network : DataError {
        REQUEST_TIMEOUT,
        NO_INTERNET,
        UNAUTHORIZED,
        SERVER_ERROR,
        FORBIDDEN,
        SERIALIZATION,
        CANCELLED,
        UNKNOWN,
    }
}


@RawRes
fun DataError.getAnimationResource(): Int {
    return when(this) {
        else -> {
            R.raw.bad_connection_animation
        }
    }
}

@StringRes
fun DataError.getErrorMessageResource(): Int {
    return when(this) {
        DataError.Network.REQUEST_TIMEOUT -> R.string.error_request_timeout
        DataError.Network.NO_INTERNET -> R.string.error_no_internet
        DataError.Network.UNAUTHORIZED -> R.string.error_unauthorized
        DataError.Network.SERVER_ERROR -> R.string.error_server_error
        DataError.Network.FORBIDDEN -> R.string.error_forbidden
        DataError.Network.SERIALIZATION -> R.string.error_serialization
        DataError.Network.CANCELLED -> R.string.error_cancelled
        DataError.Network.UNKNOWN -> R.string.error_unknown
    }
}

@StringRes
fun DataError.getErrorDesctriptionResource(): Int {
    return when(this) {
        DataError.Network.REQUEST_TIMEOUT -> R.string.error_request_timeout_description
        DataError.Network.NO_INTERNET -> R.string.error_no_internet_description
        DataError.Network.UNAUTHORIZED -> R.string.error_unauthorized_description
        DataError.Network.SERVER_ERROR -> R.string.error_server_error_description
        DataError.Network.FORBIDDEN -> R.string.error_forbidden_description
        DataError.Network.SERIALIZATION -> R.string.error_serialization_description
        DataError.Network.CANCELLED -> R.string.error_cancelled_description
        DataError.Network.UNKNOWN -> R.string.error_unknown_description
    }
}
