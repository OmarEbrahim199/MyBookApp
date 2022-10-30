package com.example.mybookapp.view

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.mybookapp.R
import com.example.mybookapp.components.BookDetailsCard
import com.example.mybookapp.components.RickAndMortyTopBar
import com.example.mybookapp.components.TopBar
import com.example.mybookapp.navigation.bottomnav.MainActions
import com.example.mybookapp.utils.DetailViewState
import com.example.mybookapp.viewmodel.MainViewModel
import com.google.accompanist.coil.CoilImage


@Composable
fun BookDetailsScreen(viewModel: MainViewModel, actions: MainActions) {


    Scaffold(topBar = {
        TopBar(title = stringResource(id = R.string.text_bookDetails), action = actions)
       // RickAndMortyTopBar(text = stringResource(id = R.string.settings_screen_title) ,elevation = 10.dp)
    }) {

        BookDetailss(viewModel = viewModel)
    }

}

@Composable
fun BookDetails(viewModel: MainViewModel) {
    when (val result = viewModel.bookDetails.value) {
        DetailViewState.Loading -> Text(text = "Loading")
        is DetailViewState.Error -> Text(text = "Error found: ${result.exception}")
        is DetailViewState.Success -> {
            val book = result.data

            LazyColumn {
                // Book Details Card
                item {
                    BookDetailsCard(book.title, book.authors, book.thumbnailUrl, book.categories)
                }

                // Description
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = stringResource(id = R.string.text_bookDetails),
                        style = typography.subtitle1,
                        color = MaterialTheme.colors.primaryVariant,
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = book.longDescription,
                        style = typography.body1,
                        textAlign = TextAlign.Justify,
                        color = MaterialTheme.colors.primaryVariant.copy(0.7F),
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                    )
                }
            }

        }
        DetailViewState.Empty -> Text("No results found!")
    }
}

@Composable
fun BookDetailss(viewModel: MainViewModel) {


    when (val result = viewModel.bookDetails.value) {
        DetailViewState.Loading -> Text(text = "Loading")
        is DetailViewState.Error -> Text(text = "Error found: ${result.exception}")
        is DetailViewState.Success -> {
            val book = result.data
            val painter =
                rememberImagePainter(book.thumbnailUrl)


            var showBookDescription by remember { mutableStateOf(false) }
            val bookCoverImageSize by animateDpAsState(
                targetValue =
                if (showBookDescription) 50.dp else 80.dp
            )

            Column(modifier = Modifier.clickable {
                showBookDescription = showBookDescription.not()
            }) {
                Row(modifier = Modifier.padding(12.dp)) {
                    Image(
                        //data = painter,
                        painter = painter,
                        contentDescription = "Book cover page",
                        //fadeIn = true,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(bookCoverImageSize)
                    )

                    Column {
                        Text(
                            text = book.title, style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                        )

                        Text(
                            text = book.authors.toString(), style = TextStyle(
                                fontWeight = FontWeight.Light,
                                fontSize = 12.sp
                            )
                        )
                    }
                }

                AnimatedVisibility(visible = showBookDescription) {
                    Text(
                        text = book.shortDescription, style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Italic
                        ),
                        modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 16.dp)
                    )
                }
            }
        }
        DetailViewState.Empty -> Text("No results found!")


    }
}