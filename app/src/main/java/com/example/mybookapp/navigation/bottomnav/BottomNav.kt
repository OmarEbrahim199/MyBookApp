package com.example.mybookapp.navigation.bottomnav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector



enum class BottomNav(
    val route: String,
    val iconId: ImageVector,
    val screenName: String
) {
    CHARACTERS(
        route = "home",
        iconId = Icons.Default.Home,
        screenName = "Home",
    ),
    EPISODES(
        route = "home",
        iconId = Icons.Default.AccountBox,
        screenName = "My Books",
    ),
    FAVORITES(
        route = "home",
        iconId = Icons.Default.Favorite,
        screenName = "",
    ),
    LOCATIONS(
        route = "search",
        iconId = Icons.Default.Share,
        screenName = "Search",
    ),
    SETTINGS(
        route = "settings",
        iconId = Icons.Default.Settings,
        screenName = "Settings",
    )
}