package com.memo.unsplashclient.presentation.search_photos

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.memo.unsplashclient.presentation.search_photos.components.PhotoThumbnail

@Composable
fun SearchPhotosScreen(viewModel:SearchPhotosViewModel = hiltViewModel()){
    //ViewModelが保持しているStateを取得
    val state = viewModel.state.value
    LazyColumn{
        items(state.photos){photo ->
           PhotoThumbnail(photo = photo, onClick = {})
        }
    }
}