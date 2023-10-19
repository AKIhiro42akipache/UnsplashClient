package com.memo.unsplashclient.presentation.photo_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun PhotoDetailScreen(
    viewModel: PhotoDetailViewModel = hiltViewModel()
){
    //view側に表示できるように設定したStateを取得する
    val state = viewModel.state.value
    //画面全体のBoxを定義してステータスに応じて処理を分岐させる
    Box(modifier = Modifier.fillMaxSize()){
        when{
            //ローディング中の場合はインジゲーターを表示する
            state.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            //エラーが発生したときの処理
            !state.error.isNullOrBlank() ->{
                Text(
                    text = state.error,
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.error)
            }
            else ->{
                state.photoDetail?.let {photoDetail ->
                    AsyncImage(
                        model = photoDetail.imageUrl,
                        contentDescription =photoDetail.description,
                        modifier = Modifier.fillMaxSize()
                    )
                }

            }
        }

    }
}