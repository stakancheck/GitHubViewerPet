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

package io.github.stakancheck.githubviewer.app

import android.app.Application
import io.github.stakancheck.githubviewer.BuildConfig
import io.github.stakancheck.githubviewer.di.initKoin
import org.koin.android.ext.koin.androidContext

class GitHubViewerApp: Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@GitHubViewerApp)
        }

        if (BuildConfig.DEBUG) {
            // Debug initialization
        }
    }
}