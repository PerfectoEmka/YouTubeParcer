package com.example.kotlinl5.ui.playlist

import androidx.lifecycle.LiveData
import com.example.kotlinl5.App
import com.example.kotlinl5.core.ui.base.BaseViewModel
import com.example.kotlinl5.data.remote.model.PlayList

class PlayListViewModel: BaseViewModel() {

    fun getPlayList(): LiveData<PlayList> {
        return App().repository.createPlayList()
    }
}