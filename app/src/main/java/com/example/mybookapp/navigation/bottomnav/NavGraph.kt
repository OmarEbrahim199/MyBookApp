package com.example.mybookapp.navigation.bottomnav

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.mybookapp.components.RickAndMortyBottomAppBar
import com.example.mybookapp.components.RickAndMortyFloatingActionBar
import com.example.mybookapp.components.RickAndMortyScaffold
import com.example.mybookapp.navigation.screen.Search.SearchScreen
import com.example.mybookapp.navigation.screen.home.HomeScreen
import com.example.mybookapp.navigation.screen.settings.SettingsScreen
import com.example.mybookapp.utils.Utility.toJson
import com.example.mybookapp.view.BookDetailsScreen
import com.example.mybookapp.viewmodel.MainViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

/**
 *  Created by Omar on 31.10.2022
 */
object EndPoints {
    const val ID = "id"
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(startDestination: String = NavScreen.Characters.route) {
    val navController = rememberAnimatedNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    val actions = remember(navController) { MainActions(navController) }
    val context = LocalContext.current


    RickAndMortyScaffold(
        bottomBar = {
            BottomNav.values().forEach { navItem ->
                if (navItem.route == currentRoute) {
                    RickAndMortyBottomAppBar(
                        navController = navController,
                        currentRoute = currentRoute
                    )
                }
            }
        },
        floatingActionButton = {
            BottomNav.values().forEach { navItem ->
                if (navItem.route == currentRoute) {
                    RickAndMortyFloatingActionBar(
                        navController = navController,
                    )
                }
            }
        },
        backgroundColor = MaterialTheme.colors.background,
    ) { innerPadding ->
        AnimatedNavHost(
            navController = navController,
            startDestination = startDestination,
            Modifier.padding(innerPadding)
        ) {


            // Task HomeScreen
            composable(NavScreen.Characters.route) {
                HomeScreen(
                    hiltViewModel()
                ,navController
                )
            }

            ////FavoriteScreen

            composable(NavScreen.Search.route) {
                SearchScreen(
                    hiltViewModel()

                )
            }


            // Task SettingsScreen
            composable(NavScreen.Settings.route) {
                SettingsScreen(
                    hiltViewModel()
                )
            }





            // Task Details
            composable(
                "${NavScreen.Details.route}/{id}",
                arguments = listOf(navArgument(EndPoints.ID) { type = NavType.StringType })
            ) {
                val viewModel = hiltViewModel<MainViewModel>(it)
                val isbnNo = it.arguments?.getString(EndPoints.ID)
                    ?: throw IllegalStateException("'Book ISBN No' shouldn't be null")

                viewModel.getBookByID(context = context, isbnNO = isbnNo)
                BookDetailsScreen(viewModel, actions)
            }


            /*composable(
                NavScreen.Favorites.route,
                content = {
                    FavoritesScreen(
                        viewModel = hiltViewModel(),
                        navigateCharacterDetail = {
                            navController.navigate(NavScreen.CharacterDetail.route.plus("?characterDetail=${it.toJson()}"))
                        }
                    )
                },
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                }
            )*/

            /* composable(NavScreen.Characters.route) {
                 CharactersScreen(
                     hiltViewModel(),
                     navigateToDetail = {
                         navController.navigate(NavScreen.CharacterDetail.route.plus("?characterDetail=${it.toJson()}"))
                     }
                 )
             }
             composable(
                 NavScreen.CharacterDetail.route.plus("?characterDetail={characterDetail}"),
                 content = {
                     CharactersDetailScreen(
                         viewModel = hiltViewModel(),
                         navigateToBack = {
                             navController.popBackStack()
                         }
                     )
                 },
                 enterTransition = {
                     slideIntoContainer(
                         AnimatedContentScope.SlideDirection.Left,
                         animationSpec = tween(700)
                     )
                 },
                 popExitTransition = {
                     slideOutOfContainer(
                         AnimatedContentScope.SlideDirection.Right,
                         animationSpec = tween(700)
                     )
                 }
             )

             composable(NavScreen.Episodes.route) {
                 EpisodesScreen(
                     hiltViewModel()
                 )
             }

             composable(NavScreen.Search.route) {
                 SearchScreen(
                     hiltViewModel(),
                     navigateToDetail = {
                         navController.navigate(NavScreen.CharacterDetail.route.plus("?characterDetail=${it.toJson()}"))
                     }
                 )
             }

             composable(NavScreen.Settings.route) {
                 SettingsScreen(
                     hiltViewModel()
                 )
             }

             */
        }
    }
}



class MainActions(navController: NavController) {

    val upPress: () -> Unit = {
        navController.navigateUp()
    }

    val gotoBookDetails: (String) -> Unit = { isbnNo ->
        navController.navigate("${NavScreen.Details.route}/$isbnNo")
    }

    val gotoBookList: () -> Unit = {
       // navController.navigate(Screen.BookList.route)
    }
}
