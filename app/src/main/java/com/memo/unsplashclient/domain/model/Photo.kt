package com.memo.unsplashclient.domain.model

data class Photo(
    //画像ID
    val photoId:String,
    //画像のタイトル
    val description:String?,
    //画像へのlikeの数
    val likes:Int?,
    //画像のリンク
    val imageUrl:String,
    //撮影者のユーザー名
    val photographer:String?,
)
