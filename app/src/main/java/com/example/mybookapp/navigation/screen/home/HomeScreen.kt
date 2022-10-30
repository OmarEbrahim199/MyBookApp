package com.example.mybookapp.navigation.screen.home


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mybookapp.navigation.bottomnav.MainActions
import com.example.mybookapp.view.BookListScreen
import com.example.mybookapp.viewmodel.MainViewModel


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    val actions = remember(navController) { MainActions(navController) }
    val context = LocalContext.current

    viewModel.getAllBooks(context = context)
    BookListScreen(viewModel, actions)





}







