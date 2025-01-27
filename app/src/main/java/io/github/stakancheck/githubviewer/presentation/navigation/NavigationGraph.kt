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

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.stakancheck.githubviewer.presentation.feature_start.StartScreen


sealed interface NavigationRoute {
    @Serializable
    data object StartScreen : NavigationRoute

    @Serializable
    data object SearchScreen : NavigationRoute

    @Serializable
    class RepositoryScreen(val repositoryId: Int) : NavigationRoute
}

@Composable
fun MainNavigationHost() {
    val navController = rememberNavController()
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = NavigationRoute.StartScreen
    ) {
        composable<NavigationRoute.StartScreen> {
            StartScreen(
                navigateToSearchScreen = {
                    navController.navigate(NavigationRoute.SearchScreen)
                }
            )
        }
        composable<NavigationRoute.SearchScreen> {
            Text(
                text = "Search screen",
            )
        }
        composable<NavigationRoute.RepositoryScreen> {
            // RepositoryScreen()
        }
    }
}
