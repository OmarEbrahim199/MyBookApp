package com.example.mybookapp.viewmodel

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybookapp.BuildConfig
import com.example.mybookapp.R
import com.example.mybookapp.components.RickAndMortyText
import com.example.mybookapp.model.BookItem
import com.example.mybookapp.utils.DetailViewState
import com.example.mybookapp.utils.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class MainViewModel : ViewModel() {

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    private val _detailViewState = MutableStateFlow<DetailViewState>(DetailViewState.Loading)

    val books = _viewState.asStateFlow()
    val bookDetails = _detailViewState.asStateFlow()

    // Helps to format the JSON
    val format = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
        isLenient = true
    }


    // get all the data from the Book.json
    fun getAllBooks(context: Context) = viewModelScope.launch {
        try {

            // read JSON File
            val myJson = context.assets.open("books.json").bufferedReader().use {
                it.readText()
            }

            // format JSON
            val bookList = format.decodeFromString<List<BookItem>>(myJson)
            _viewState.value = ViewState.Success(bookList)

        } catch (e: Exception){
            _viewState.value = ViewState.Error(e)
        }
    }


    // get book by ID
    fun getBookByID(context: Context, isbnNO:String) = viewModelScope.launch {
        try {

            // read JSON File
            val myJson = context.assets.open("books.json").bufferedReader().use {
                it.readText()
            }

            // format JSON
            val bookList = format.decodeFromString<List<BookItem>>(myJson) .filter { it.isbn.contentEquals(isbnNO)}.first()
            _detailViewState.value = DetailViewState.Success(bookList)

        } catch (e: Exception){
            _detailViewState.value = DetailViewState.Error(e)
        }
    }



}