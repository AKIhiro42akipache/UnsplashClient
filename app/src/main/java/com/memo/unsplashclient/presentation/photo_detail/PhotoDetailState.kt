package com.memo.unsplashclient.presentation.photo_detail

import com.memo.unsplashclient.domain.model.PhotoDetail

class PhotoDetailState(
    val isLoading:Boolean = false,
    val photoDetail: PhotoDetail? = null,
    val error :String? = null
) {

}