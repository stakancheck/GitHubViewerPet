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

package io.github.stakancheck.githubviewer.di

import io.github.stakancheck.githubviewer.data.repository.GitHubRepositoryImpl
import io.github.stakancheck.githubviewer.data.sources.remote.GitHubApiSource
import io.github.stakancheck.githubviewer.data.utils.BearerTokenInterceptor
import io.github.stakancheck.githubviewer.domain.repository.GitHubRepository
import io.github.stakancheck.githubviewer.domain.usecases.RetrieveRepositoryContentsUseCase
import io.github.stakancheck.githubviewer.domain.usecases.SearchRepositoriesAndUsersUseCase
import io.github.stakancheck.githubviewer.presentation.feature_repository_content.RepositoryContentViewModel
import io.github.stakancheck.githubviewer.presentation.feature_search.SearchScreenViewModel
import io.github.stakancheck.githubviewer.utils.Constants
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory


val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
}

/**
 * Data module.
 *
 * Provides all data-related dependencies.
 */
private val dataModule = module {
    single<Retrofit> {
        val client = OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor(
                BearerTokenInterceptor()
            )
            .build()
        val contentType = "application/json".toMediaType()

        Retrofit.Builder()
            .baseUrl(Constants.GITHUB_API_BASE_URL)
            .client(client)
            .addConverterFactory(
                json.asConverterFactory(
                    contentType
                )
            ).build()
    }

    single<GitHubApiSource> {
        get<Retrofit>().create(GitHubApiSource::class.java)
    }

    // Repositories
    single<GitHubRepository> {
        GitHubRepositoryImpl(
            apiSource = get(),
        )
    }
}

/**
 * Domain module
 */

private val domainModule = module {
    // Use cases
    factory {
        SearchRepositoriesAndUsersUseCase(
            gitHubRepository = get()
        )
    }

    factory {
        RetrieveRepositoryContentsUseCase(
            gitHubRepository = get()
        )
    }
}

/**
 * Presentation module.
 */
private val presentationModule = module {
    // View models
    viewModel {
        SearchScreenViewModel(
            searchRepositoriesAndUsersUseCase = get(),
        )
    }

    viewModel { parameters ->
        RepositoryContentViewModel(
            repoFullName = parameters.get(),
            retrieveRepositoryContentsUseCase = get(),
        )
    }
}

private val appModules = listOf(
    dataModule,
    domainModule,
    presentationModule
)

fun initKoin(
    appDeclaration: KoinAppDeclaration = {},
) {
    startKoin {
        appDeclaration()
        modules(
            appModules
        )
    }
}
