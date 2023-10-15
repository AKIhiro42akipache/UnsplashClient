package com.memo.unsplashclient.data.remote

import com.memo.unsplashclient.common.Constants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashApi {

    @Headers("Authorization: Client-ID ${Constants.API_KEY}")
    @GET("search/photos")
    //非同期ワンショットクエリを行うためにsuspendをつける(レスポンスとしてSearchPhotosResultDtoを返す)
    suspend fun searchPhotos(@Query("query") query:String):SearchPhotosResultDto
}