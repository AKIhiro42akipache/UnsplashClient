package com.memo.unsplashclient.data.repository

import com.memo.unsplashclient.data.remote.PhotoDetailDto
import com.memo.unsplashclient.data.remote.SearchPhotosResultDto
import com.memo.unsplashclient.data.remote.UnsplashApi
import com.memo.unsplashclient.domain.repository.PhotoRepository
import javax.inject.Inject

//UnsplashApiのインタフェースをRetrofit又は、HiltでDIとして受け取り、
class PhotoRepositoryImpl @Inject constructor(
    private val api:UnsplashApi): PhotoRepository {

    override suspend fun searchPhotos(query: String): SearchPhotosResultDto {
        //api.searchPhotos(query)を叩き、戻り値を返却する
        return api.searchPhotos(query)
    }

    override suspend fun getPhotoById(photoId: String): PhotoDetailDto {
        //api.getPhotoById(photoId)を叩き、取得した画像のidを返却する
        return api.getPhotoById(photoId)
    }
}