package com.memo.unsplashclient.presentation.search_photos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.memo.unsplashclient.presentation.ScreenRoute
import com.memo.unsplashclient.presentation.search_photos.components.PhotoThumbnail
import com.memo.unsplashclient.presentation.search_photos.components.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPhotosScreen(
    navController: NavController,
    viewModel:SearchPhotosViewModel = hiltViewModel()
){
    //ViewModelが保持しているStateを取得
    val state = viewModel.state.value
    Scaffold(
        topBar = {
            SearchBar(
            searchText = viewModel.query,
            onSearchChangeText = {viewModel.query = it},
            onDone = {viewModel.searchPhotos()})}
    ) {paddingValues ->
        //Boxを定義して画面幅いっぱいにローディングインジゲーターを設定する
        Box(modifier = Modifier.fillMaxSize()){
            when{
                //画像検索画面のロードを行っている場合
                state.isLoading ->{
                    //ローディング中はローディングインジゲーターをBOX幅の中央に表示する
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                !state.error.isNullOrBlank() ->{
                    //エラー表示
                    Text(
                        text = state.error,
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.error
                    )
                }
                else ->{
                    LazyColumn(modifier = Modifier.padding(paddingValues)){
                        items(state.photos) { photo ->
                            PhotoThumbnail(photo = photo, onClick = {
                                //画像詳細画面へ遷移する
                                navController.navigate(ScreenRoute.PhotoDetailScreen.route + "/${photo.photoId}")
                            })
                        }
                    }
                }
            }

        }

    }
}