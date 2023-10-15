package com.memo.unsplashclient.data.remote

import com.memo.unsplashclient.common.Constants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

//UnsplashApiをRetrofitのインタフェースを追加
interface UnsplashApi {

    @Headers("Authorization: Client-ID ${Constants.API_KEY}")
    @GET("search/photos")
    //非同期ワンショットクエリを行うためにsuspendをつける(レスポンスとしてSearchPhotosResultDtoを返す)
    suspend fun searchPhotos(@Query("query") query:String):SearchPhotosResultDto

    //{id}と記述することで、パラメータとして受け取ることをRetrofitに示す
    @Headers("Authorization: Client-ID ${Constants.API_KEY}")
    @GET("photos/{id}")
    //非同期ワンショットクエリを行うためにsuspendをつける(レスポンスとしてPhotoDetailDtoを返す)
    suspend fun getPhotoById(@Path("id") photoId:String):PhotoDetailDto
}