package com.memo.unsplashclient.presentation.search_photos.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    searchText:String,
    //searchbarに表示しているテキストが変更されたタイミング発火
    onSearchChangeText:(String) -> Unit,
    //ユーザーが検索キーワードを押して決定を押したときタイミングで発火
    onDone:() -> Unit,
    placeholderText:String = "Search..."
) {
    var showClearButton by remember {mutableStateOf(false)}
    val focusRequester = remember {FocusRequester()}
    val keyboardController = LocalSoftwareKeyboardController.current

    TopAppBar{
        OutlinedTextField(
            value = searchText,
            onValueChange =onSearchChangeText,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp)
                //Focusが変更されたときの呼ばれる処理
                .onFocusChanged { FocueState ->
                    /*OutLineTextFieldにFocusがあたっているときは
                    * クリアボタンを表示し、focusが外れたときはクリアボタンを
                    * 非表示にする*/
                    showClearButton = FocueState.isFocused
                }
                //OutlinedTextFieldにfocusが当たっている状態を外部から識別する
                .focusRequester(focusRequester),
            placeholder = {
                Text(text = placeholderText)
            },
            trailingIcon = {
                IconButton(onClick = {onSearchChangeText("")}) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "close"
                    )
                }
            },
            //Textの最大許容文字数を設定する
            maxLines = 1,
            //Textの最大行数を設定する
            singleLine = true,
            //Textのキーボードの種類を設定する(Doneに設定してCheckMarkに設定する)
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions (onDone = {
                //キーボードの終了ボタンが押されたときにキーボードを非表示にする
                keyboardController?.hide()
                onDone()
            })
        )
    }
        //TopAppBarが表示されたタイミングでfocusが1回だけ当たるように設定する
    LaunchedEffect(Unit){
        focusRequester.requestFocus()
    }
}