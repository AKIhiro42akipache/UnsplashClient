package com.memo.unsplashclient.presentation.search_photos

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierInfo
import androidx.hilt.navigation.compose.hiltViewModel
import com.memo.unsplashclient.presentation.search_photos.components.PhotoThumbnail
import com.memo.unsplashclient.presentation.search_photos.components.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPhotosScreen(viewModel:SearchPhotosViewModel = hiltViewModel()){
    //ViewModelが保持しているStateを取得
    val state = viewModel.state.value
    
    Scaffold(
        topBar = {
            SearchBar(
            searchText = "",
            onSearchChangeText = {},
            onDone = {})}
    ) {
        paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)){
            items(state.photos) { photo ->
                PhotoThumbnail(photo = photo, onClick = {})
            }
        }
    }
}