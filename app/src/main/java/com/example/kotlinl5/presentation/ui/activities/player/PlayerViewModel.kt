package com.example.kotlinl5.presentation.ui.activities.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinl5.core.network.result.Resource
import com.example.kotlinl5.core.ui.base.BaseViewModel
import com.example.kotlinl5.domain.models.Video
import com.example.kotlinl5.data.repository.YouTubeRepositoryImpl
import com.example.kotlinl5.domain.use_cases.GetVideoUseCase

class PlayerViewModel(private val repository: YouTubeRepositoryImpl): BaseViewModel() {

    private val getVideoUseCase = GetVideoUseCase(repository)
    var loading = MutableLiveData<Boolean>()

    fun getVideo(videoId: String): LiveData<Resource<Video>> {
        return getVideoUseCase.getVideo(videoId)
    }
}