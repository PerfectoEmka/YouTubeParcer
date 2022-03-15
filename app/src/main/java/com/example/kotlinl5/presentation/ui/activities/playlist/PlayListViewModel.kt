package com.example.kotlinl5.presentation.ui.activities.playlist

import androidx.lifecycle.*
import com.example.kotlinl5.core.network.result.Status
import com.example.kotlinl5.core.ui.base.BaseViewModel
import com.example.kotlinl5.data.repository.YouTubeRepositoryImpl
import com.example.kotlinl5.domain.models.PlayList
import com.example.kotlinl5.domain.use_cases.GetPlaylistUseCase

class PlayListViewModel(repository: YouTubeRepositoryImpl): BaseViewModel() {

    private val getPlaylistUseCase = GetPlaylistUseCase(repository)
    val playlist = MutableLiveData<PlayList>()
    var loading = MutableLiveData<Boolean>()

    fun getPlayList(){
        Transformations.map(
            getPlaylistUseCase.getPlaylist()
        ) {
            when(it.status){
                Status.LOADING -> loading.postValue(true)
                Status.SUCCESS -> {
                    loading.postValue(false)
                    //Toast.makeText(this, it.data?.kind, Toast.LENGTH_SHORT).show()
                    playlist.postValue(it.data!!)
                }
                Status.ERROR -> {
                    loading.postValue(false)
                }
            }
        }
    }
}