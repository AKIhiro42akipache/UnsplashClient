package com.memo.unsplashclient.presentation.photo_detail.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun CountLabel(
    imageVector: ImageVector,
    count:Int,
    iconTint:Color,
    //引数としてmodifierを受け取ることで外からViewを変更できるようにする(modifierを調整しない場合は、= Modifierとしてデフォルト値を設定する)
    modifier: Modifier = Modifier,
)
{
    Row(modifier = modifier){
        Icon(
            imageVector =  imageVector,
            contentDescription = "likes",
            tint = iconTint,
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = count.toString(),
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge
        )
    }


}