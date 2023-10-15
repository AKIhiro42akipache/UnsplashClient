package com.memo.unsplashclient.domain.use_case

import com.memo.unsplashclient.common.NetworkResponse
import com.memo.unsplashclient.data.remote.toPhotos
import com.memo.unsplashclient.domain.model.Photo
import com.memo.unsplashclient.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

//画像検索を行うためのUseCase
class SearchPhotosUseCase @Inject constructor(
    private val photoRepository: PhotoRepository
){
 //流れ query(String) -> 検索を実行 -> List<Photo>

 //別メソッドからinvoke()を呼び出すときにメソッド名()()で呼び出すことができる
 /*通信が完了したときには、Successを呼び、失敗時にはFailureを呼び、ロード中にはLoadingを呼ぶなど連続的な処理を行いたいため、
 * Flowを使って逐一監視を行い、statusの状態をflowに格納しておく*/
   operator fun invoke(query:String): Flow<NetworkResponse<List<Photo>>> = flow{
       try {
           //ロード中に設定
           emit(NetworkResponse.Loading<List<Photo>>())
           val photos = photoRepository.searchPhotos(query).toPhotos()
           //通信成功時には、ロード中に取得し変換したphotosを渡す
           emit(NetworkResponse.Success<List<Photo>>(photos))
       }catch (e:Exception){
           //通信失敗時には、エラー内容を渡す
           emit(NetworkResponse.Failure<List<Photo>>(e.message))
       }
   }
}