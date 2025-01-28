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



import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


/**
 * Formats date time string to "dd.MM.yyyy HH:mm:ss" format
 *
 */
object DateTimeFormatter {
    /**
     * Formats date time string to "dd.MM.yyyy HH:mm:ss" format
     *
     * @param dateTimeString date time string
     * @return formatted date time string
     */
    operator fun invoke(dateTimeString: String): String {
        val zonedDateTime = ZonedDateTime.parse(dateTimeString)
        val formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT, Locale.getDefault())
        return zonedDateTime.format(formatter)
    }

    private const val DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm:ss"
}
