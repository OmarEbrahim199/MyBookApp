package com.example.mybookapp.navigation.bottomnav

import androidx.annotation.StringRes
import com.example.mybookapp.R

/**
 *
 */

sealed class NavScreen(val route: String, @StringRes val resourceId: Int) {
    object Settings : NavScreen("settings", R.string.settings_screen_title)
    object Characters : NavScreen("home", R.string.Home_screen_title)
    object Favorites : NavScreen("home", R.string.favorite_screen_title)
    object Search : NavScreen("search", R.string.text_search)
    object Details : NavScreen("book_details", R.string.text_bookDetails)
  // object Episodes : NavScreen("episodes")
   // object CharacterDetail : NavScreen("character_detail", R.string.text_search)


}