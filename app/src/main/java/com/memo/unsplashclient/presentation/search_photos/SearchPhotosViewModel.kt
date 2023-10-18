package com.memo.unsplashclient.presentation.search_photos

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.memo.unsplashclient.common.NetworkResponse
import com.memo.unsplashclient.domain.use_case.SearchPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
/*ViewModelでは、画像検索機能をView側に表示する必要があるため、
* SearchPhotosUseCaseへ依存する必要があるためconstructor()の引数に入力する*/
class SearchPhotosViewModel @Inject constructor(
    private val searchPhotosUseCase: SearchPhotosUseCase
) :ViewModel(){
    //画像検索画面(SearchPhotosState)の状態を管理するための変数
    private val _state = mutableStateOf(SearchPhotosState())
    //view側で状態を変更できるようにするためのstate
    val state: State<SearchPhotosState> = _state
    var query by mutableStateOf("Programming")
    //アプリの起動時に最初に呼び出されるメソッド(1回だけ描画)
    init {
        searchPhotos()
    }

    //画像検索機能のメソッド(APIを叩くようのメソッドになるため、queryを呼び出すときに使用する)
   fun searchPhotos() {
        //Invoke関数を呼び出す
        //ネットワークresponseの状態が変わるたびに呼び出されるためそれぞれのケースの処理を記述
        searchPhotosUseCase(query).onEach { result ->
            when (result) {
                //通信成功時の処理
                is NetworkResponse.Success -> {
                    _state.value = SearchPhotosState(
                        isLoading = false,
                        photos = result.data ?: emptyList()
                    )
                }
                //エラーが発生したときの処理
                is NetworkResponse.Failure -> {
                    _state.value = SearchPhotosState(error = result.error)
                }
                //ロード中の処理
                is NetworkResponse.Loading -> {
                    _state.value = SearchPhotosState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
