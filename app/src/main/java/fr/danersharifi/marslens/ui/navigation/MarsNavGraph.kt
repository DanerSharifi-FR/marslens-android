package fr.danersharifi.marslens.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import fr.danersharifi.marslens.R
import fr.danersharifi.marslens.data.model.Mission
import fr.danersharifi.marslens.ui.screens.MissionSearchScreen
import fr.danersharifi.marslens.ui.screens.PhotosScreen
import fr.danersharifi.marslens.ui.screens.PhotosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarsNavGraph(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SearchRoute,
        modifier = modifier,
    ) {
        composable<SearchRoute> {
            Scaffold { innerPadding ->
                MissionSearchScreen(
                    onSearch = { mission, sol ->
                        navController.navigate(
                            route = PhotosRoute(
                                missionCode = mission.apiValue,
                                sol = sol,
                            ),
                        ) {
                            // avoid stacking the same search twice
                            launchSingleTop = true
                        }
                    },
                    modifier = Modifier.padding(
                        paddingValues = innerPadding,
                    ),
                )
            }
        }

        composable<PhotosRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<PhotosRoute>()

            val mission = Mission.entries.first {
                it.apiValue == route.missionCode
            }

            // Keep one ViewModel per results destination
            val photosViewModel: PhotosViewModel = viewModel(
                viewModelStoreOwner = backStackEntry,
                factory = viewModelFactory {
                    initializer {
                        PhotosViewModel(
                            mission = mission,
                            sol = route.sol,
                        )
                    }
                },
            )

            val uiState by photosViewModel.uiState
                .collectAsStateWithLifecycle()

            val missionName = stringResource(
                id = when (mission) {
                    Mission.CURIOSITY -> R.string.curiosity
                    Mission.INSIGHT -> R.string.insight
                },
            )

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = stringResource(
                                    id = R.string.photos_screen_title,
                                    formatArgs = arrayOf(
                                        missionName,
                                        route.sol.toString(),
                                    ),
                                ),
                            )
                        },
                        navigationIcon = {
                            TextButton(
                                onClick = {
                                    navController.popBackStack()
                                },
                            ) {
                                Text(
                                    text = stringResource(
                                        id = R.string.navigate_back,
                                    ),
                                )
                            }
                        },
                    )
                },
            ) { innerPadding ->
                PhotosScreen(
                    uiState = uiState,
                    onRetry = photosViewModel::loadPhotos,
                    onLoadMore = photosViewModel::loadMore,
                    modifier = Modifier.padding(
                        paddingValues = innerPadding,
                    ),
                )
            }
        }
    }
}