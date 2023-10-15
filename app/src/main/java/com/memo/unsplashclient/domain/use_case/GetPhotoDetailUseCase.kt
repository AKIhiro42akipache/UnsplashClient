package com.memo.unsplashclient.domain.use_case

import com.memo.unsplashclient.common.NetworkResponse
import com.memo.unsplashclient.data.remote.toPhotoDetail
import com.memo.unsplashclient.domain.model.PhotoDetail
import com.memo.unsplashclient.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPhotoDetailUseCase @Inject constructor(
    private val repository: PhotoRepository
) {
    //画像ID -> 通信　-> 画像状態情報

    operator fun invoke(photoId:String):Flow<NetworkResponse<PhotoDetail>> = flow {
        try {
            //ロード中に設定
            emit(NetworkResponse.Loading<PhotoDetail>())
            //ロード中にrepositoryからphotoIdを取得し、photoDetailに変換する
            val photoDetail = repository.getPhotoById(photoId).toPhotoDetail()
            //認証成功時に変換したphotoDetailを渡す
            emit(NetworkResponse.Success<PhotoDetail>(photoDetail))
        }catch (e:Exception){
            //tryブロック内で何らかのエラーが発生した場合、通信失敗時にエラー内容を渡す
            emit(NetworkResponse.Failure<PhotoDetail>(e.message))
        }
    }
}