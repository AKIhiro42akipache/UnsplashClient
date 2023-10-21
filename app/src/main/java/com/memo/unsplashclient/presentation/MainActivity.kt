package com.memo.unsplashclient.presentation

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.memo.unsplashclient.presentation.photo_detail.PhotoDetailScreen
import com.memo.unsplashclient.presentation.search_photos.SearchPhotosScreen
import com.memo.unsplashclient.presentation.ui.theme.UnsplashClientTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           when(resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK){
               //ユーザーがDarkModeを選択したときだけステータスバーとナビゲーションバーの色を黒にする
               Configuration.UI_MODE_NIGHT_YES -> {
                     window.statusBarColor = Color.Black.copy(alpha = 0.8f).toArgb()
                     window.navigationBarColor =  Color.Black.copy(alpha = 0.8f).toArgb()
               }
           }
            UnsplashClientTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        //最初に表示する画面はSearchPhotosScreenのため、startDestinationにSearchPhotosScreenを指定
                        startDestination = ScreenRoute.SearchPhotosScreen.route)
                    {
                        //画像検索画面
                        composable(route = ScreenRoute.SearchPhotosScreen.route){
                            //画面を表示するタイミングで引数を渡し、SearchPhotosScreen上でも画面遷移を実装できるようにする
                            SearchPhotosScreen(navController)
                        }
                        //画像詳細表示画面を表示する処理(画像を表示する場合はIDを渡してあげる必要がある)
                        composable(route = ScreenRoute.PhotoDetailScreen.route + "/{photoId}") {
                            PhotoDetailScreen()
                        }
                    }

                }
            }
        }
    }
}
