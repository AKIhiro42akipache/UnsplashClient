package com.memo.unsplashclient.presentation.photo_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.memo.unsplashclient.domain.model.PhotoDetail
import com.memo.unsplashclient.presentation.photo_detail.components.CountLabel

@Composable
fun PhotoDetailScreen(
    viewModel: PhotoDetailViewModel = hiltViewModel()
){
    //view側に表示できるように設定したStateを取得する
    val state = viewModel.state.value
    //画面全体のBoxを定義してステータスに応じて処理を分岐させる
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black.copy(alpha = 0.8f))){
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
                        PhotoDetailContent(photoDetail)
                }

            }
        }

    }
}

@Composable
fun PhotoDetailContent(photoDetail: PhotoDetail){
    //columnを縦方向にスクロールできるように設定する
    Column (modifier = Modifier.verticalScroll(rememberScrollState())){
        Box (modifier = Modifier.heightIn(min = 200.dp)){
            var isLoadingImage by remember{mutableStateOf(true)}
            if (isLoadingImage){
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            AsyncImage(
                model = photoDetail.imageUrl,
                contentDescription = photoDetail.description,
                modifier = Modifier
                    .fillMaxWidth()
                    //下部分だけ角丸に設定する
                    .clip(
                        RoundedCornerShape(
                            topStartPercent = 0,
                            topEndPercent = 0,
                            bottomEndPercent = 5,
                            bottomStartPercent = 5
                        )
                    ),
                //画像が読み込まれたタイミングでonSuccessを呼び出しインジゲーターを非表示にする。
                onSuccess = {isLoadingImage = false}
            )
        }

        Column(modifier = Modifier.padding(10.dp)){
            //詳細
            Text(
                text = photoDetail.description ?:"no description",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(20.dp))
            //撮影者
            Text(
                text = photoDetail.photographer ?: "unknown photographer",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(20.dp))
            //お気に入りの数
            CountLabel(
                imageVector = Icons.Default.Favorite,
                count = photoDetail.likes ?: 0,
                iconTint = Color.Magenta
            )
            //ダウンロードの数
            CountLabel(
                imageVector = Icons.Default.Share,
                count = photoDetail.downloads ?: 0,
                iconTint = Color.Green
            )
            Spacer(modifier = Modifier.height(20.dp))
            //カメラの種類
            Text(
                text = "Camera: ${photoDetail.camera}",
                color = Color.White)
            //場所
            Text(
                text = "Location: ${photoDetail.location}",
                color = Color.White)
        }
    }
}