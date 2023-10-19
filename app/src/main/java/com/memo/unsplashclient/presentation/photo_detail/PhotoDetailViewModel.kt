package com.memo.unsplashclient.presentation.photo_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.memo.unsplashclient.common.NetworkResponse
import com.memo.unsplashclient.domain.use_case.GetPhotoDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PhotoDetailViewModel @Inject constructor(
    private val getPhotoDetailUseCase: GetPhotoDetailUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel(){
    //画像詳細画面の状態を管理するための変数
    private val _state = mutableStateOf(PhotoDetailState())
    //view側で状態を変更できるようにするための変数
    val state : State<PhotoDetailState> = _state

    init {
        //正常にphotoIdを受け取れた場合は画像詳細情報を取得する
        savedStateHandle.get<String>("photoId")?.let { photoId ->
            getPhotoDetail(photoId)
        }
    }

    //画像詳細情報を取得するメソッド
    private fun getPhotoDetail(photoId:String){
        getPhotoDetailUseCase(photoId).onEach {result ->
            when(result){
                //画面ロード中の処理
                is NetworkResponse.Loading -> {
                    _state.value = PhotoDetailState(isLoading = true)
                }
                //ロードが正常に完了したときの処理
                is NetworkResponse.Success ->{
                    _state.value = PhotoDetailState(
                        photoDetail = result.data,
                        isLoading = false
                    )
                }
                //ロード中に何らかのエラーが発生したときの処理
                is NetworkResponse.Failure ->{
                    _state.value = PhotoDetailState(error = result.error)
                }
            }
        }.launchIn(viewModelScope)
    }
}