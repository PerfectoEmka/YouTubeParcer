package com.example.kotlinl5.ui.details

import androidx.lifecycle.LiveData
import com.example.kotlinl5.App
import com.example.kotlinl5.core.ui.base.BaseViewModel
import com.example.kotlinl5.data.remote.model.PlayList

class DetailsViewModel: BaseViewModel() {

    fun getPlaylistItems(playlistId: String): LiveData<PlayList>{
        return App().repository.createPlaylistItems(playlistId)
    }

}