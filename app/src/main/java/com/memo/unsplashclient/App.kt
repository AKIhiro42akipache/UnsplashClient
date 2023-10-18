package com.memo.unsplashclient

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//アプリの起動時に最初に起動するクラス
//クラス全体でアプリを使うためのルートとなるアノテーションを付与する
@HiltAndroidApp
class App :Application (){
}