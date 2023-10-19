package com.memo.unsplashclient.presentation.photo_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.memo.unsplashclient.domain.use_case.GetPhotoDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoDetailViewModel @Inject constructor(
    getPhotoDetailUseCase: GetPhotoDetailUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel(){
    
}