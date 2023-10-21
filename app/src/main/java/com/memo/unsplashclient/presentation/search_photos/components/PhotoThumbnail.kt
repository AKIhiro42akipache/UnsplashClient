package com.memo.unsplashclient.presentation.search_photos.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.memo.unsplashclient.domain.model.Photo
import com.memo.unsplashclient.presentation.photo_detail.components.CountLabel
import kotlin.math.min

@Composable
fun PhotoThumbnail(
    photo: Photo,
    onClick:(Photo) -> Unit
){
    //箱で全体を囲む
    Box(modifier = Modifier
        .background(Color.Black)
        .heightIn(min = 200.dp)
        .clickable { onClick(photo) },
        contentAlignment = Alignment.BottomCenter
        ){
        //円状のインジゲーターを取得し、表示する
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        AsyncImage(
            model = photo.imageUrl,
            contentDescription = photo.description,
            modifier = Modifier.fillMaxWidth()
        )
        //テキストを水平方向に並べる
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.5f))
                .padding(10.dp)
        ){
           Column(
               //文字で上のRowの横幅の80%を埋める
               modifier = Modifier.fillMaxWidth(0.8f)
           ){
               //写真のタイトルを表示(タイトルが存在しない場合はno descriptionと表示)
               Text(
                   text = photo.description ?: "no description",
                   color = Color.White,
                   style = MaterialTheme.typography.titleLarge
               )
               //撮影者のユーザー名を取得して表示する(ユーザー名が存在しない場合はunknown photographerと表示する)
               Text(
                   text = photo.photographer?: "unknown photographer",
                   color = Color.White,
                   style = MaterialTheme.typography.bodyMedium
               )
           }
            Spacer(modifier = Modifier.weight(1f))
            CountLabel(
                imageVector = Icons.Default.Favorite,
                count = photo.likes ?:0,
                iconTint = Color.Magenta,
            )
        }
    }
}