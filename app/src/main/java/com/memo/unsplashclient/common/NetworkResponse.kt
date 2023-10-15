package com.memo.unsplashclient.common

//ネットワークの接続状況を表すsealed class
sealed class NetworkResponse<T>(
    val data:T? = null,
    val error:String? = null,
){
    //通信成功時には、NetworkResponse<T>の引数に取得したデータを格納する
    class Success<T>(data:T):NetworkResponse<T>(data = data)
    //通信失敗時の処理
    class Failure<T>(error: String?):NetworkResponse<T>(error = error)
    //ローディングの状態を表すクラス
    class Loading<T>:NetworkResponse<T>()
}