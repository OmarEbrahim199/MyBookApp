package com.example.mybookapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mybookapp.navigation.bottomnav.NavGraph


import com.example.mybookapp.ui.theme.MyBookAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var application: MyBookApp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBookAppTheme (darkTheme = application.isDark.value) {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background,
                        modifier = Modifier.fillMaxSize()) {
                   NavGraph()
                   //MainScreen()


                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyBookAppTheme {
NavGraph()
    }
}