package com.example.kotlinl5.presentation.ui.activities.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinl5.core.network.result.Resource
import com.example.kotlinl5.core.ui.base.BaseViewModel
import com.example.kotlinl5.domain.models.PlayList
import com.example.kotlinl5.data.repository.YouTubeRepositoryImpl
import com.example.kotlinl5.domain.use_cases.GetPlaylistItemsUseCase

class DetailsViewModel(private val repository: YouTubeRepositoryImpl): BaseViewModel() {

    private val getPlaylistItemUseCase = GetPlaylistItemsUseCase(repository)
    var loading = MutableLiveData<Boolean>()

    fun getPlaylistItems(playlistId: String): LiveData<Resource<PlayList>> {
        return getPlaylistItemUseCase.getPlaylistItems(playlistId)
    }
}