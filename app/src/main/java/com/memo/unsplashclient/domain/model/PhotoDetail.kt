package com.memo.unsplashclient.domain.model

data class PhotoDetail(
    //画像の説明
    val description:String?,
    //likeの数
    val likes:Int?,
    //画像のURL
    val imageUrl:String,
    //撮影者のユーザ名
    val photographer:String?,
    //撮影したカメラの名前
    val camera:String?,
    //撮影場所
    val location:String?,
    //Unsplashでダウンロードした回数
    val downloads:Int?,
)
