package com.memo.unsplashclient.di

import com.memo.unsplashclient.common.Constants.BASE_URL
import com.memo.unsplashclient.data.remote.UnsplashApi
import com.memo.unsplashclient.data.repository.PhotoRepositoryImpl
import com.memo.unsplashclient.domain.repository.PhotoRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

//Hiltモジュールを作成する処理
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    //UnSplashAPIのインスタンスを作成する処理
    //1つで良いため、Singletonパターンとして定義
    @Provides
    @Singleton
    fun provideUnSplashApi(): UnsplashApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            //どのJSONライブラリを使うか指定
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build(
                    )
                )
            ).build().create(UnsplashApi::class.java)
    }

    //repositoryの提供方法を指定する必要がある
    @Provides
    @Singleton
    fun providePhotoRepository(api:UnsplashApi):PhotoRepository{
        return PhotoRepositoryImpl(api)
    }
}
