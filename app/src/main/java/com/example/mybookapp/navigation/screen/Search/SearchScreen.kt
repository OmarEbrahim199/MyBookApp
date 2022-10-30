package com.example.mybookapp.navigation.screen.Search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mybookapp.BuildConfig
import com.example.mybookapp.R
import com.example.mybookapp.components.RickAndMortyScaffold
import com.example.mybookapp.components.RickAndMortyText
import com.example.mybookapp.components.RickAndMortyTopBar
import com.example.mybookapp.navigation.bottomnav.MainActions
import com.example.mybookapp.navigation.bottomnav.NavScreen
import com.example.mybookapp.navigation.screen.settings.SettingsViewEvent
import com.example.mybookapp.navigation.screen.settings.SettingsViewModel
import com.example.mybookapp.view.BookListScreen
import com.example.mybookapp.viewmodel.MainViewModel


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val scaffoldState = rememberScaffoldState()
    val viewState by viewModel.uiState.collectAsState()

    RickAndMortyScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            RickAndMortyTopBar(
                text = stringResource(id = R.string.search_screen_title),
                elevation = 10.dp,
            )
        },
        content = {
            Content(
                isDark = viewState.isDark,
                onTriggerEvent = {
                    viewModel.onTriggerEvent(it)
                }
            )
        },
        backgroundColor = MaterialTheme.colors.background
    )

}

@Composable
private fun Content(
    isDark: Boolean = false,
    onTriggerEvent: (SettingsViewEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .border(
                    border = BorderStroke(width = 1.dp, color = Color.LightGray),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                RickAndMortyText(
                    text = "This is search page",
                    style = MaterialTheme.typography.body2
                )


            }


        }
    }
}
