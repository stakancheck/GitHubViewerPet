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

package io.github.stakancheck.githubviewer.presentation.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import io.github.stakancheck.githubviewer.presentation.feature_repository_content.RepositoryContentScreen
import io.github.stakancheck.githubviewer.presentation.feature_search.SearchScreen
import io.github.stakancheck.githubviewer.presentation.feature_start.StartScreen
import kotlinx.serialization.Serializable


sealed interface NavigationRoute {
    @Serializable
    data object StartScreen : NavigationRoute

    @Serializable
    data object SearchScreen : NavigationRoute

    @Serializable
    class RepositoryScreen(val repoFullName: String) : NavigationRoute
}

@Composable
fun MainNavigationHost() {
    val navController = rememberNavController()

    val localUriHandler = LocalUriHandler.current

    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = NavigationRoute.StartScreen,
        enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn() },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut() },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn() },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut() }
    ) {
        composable<NavigationRoute.StartScreen> {
            StartScreen(
                navigateToSearchScreen = {
                    navController.navigate(NavigationRoute.SearchScreen)
                }
            )
        }
        composable<NavigationRoute.SearchScreen> {
            SearchScreen(
                navigateToRepositoryScreen = { repoFullName ->
                    navController.navigate(NavigationRoute.RepositoryScreen(repoFullName))
                },
                navigateToUrl = { url ->
                    localUriHandler.openUri(url)
                },
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable<NavigationRoute.RepositoryScreen> {
            val thisRoute = it.toRoute<NavigationRoute.RepositoryScreen>()
            RepositoryContentScreen(
                repoFullName = thisRoute.repoFullName,
                navigateToUrl = { url ->
                    localUriHandler.openUri(url)
                },
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
