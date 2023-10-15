package com.memo.unsplashclient.domain.repository

import com.memo.unsplashclient.data.remote.PhotoDetailDto
import com.memo.unsplashclient.data.remote.SearchPhotosResultDto

//写真を取得するためのリポジトリインタフェース
interface PhotoRepository {
    //非同期ワンショットクエリのため、suspendをつける
    suspend fun searchPhotos(query: String):SearchPhotosResultDto
    suspend fun getPhotoById(photoId: String):PhotoDetailDto
}