package com.memo.unsplashclient.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.memo.unsplashclient.presentation.search_photos.SearchPhotosScreen
import com.memo.unsplashclient.presentation.ui.theme.UnsplashClientTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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
                            //todo 一旦画像詳細表示画面と置く
                            Text(text = "画像詳細表示画面")
                        }
                    }

                }
            }
        }
    }
}
