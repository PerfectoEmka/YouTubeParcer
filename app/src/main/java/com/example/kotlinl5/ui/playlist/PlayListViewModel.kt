package com.example.kotlinl5.ui.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinl5.App
import com.example.kotlinl5.core.network.result.Resource
import com.example.kotlinl5.core.ui.base.BaseViewModel
import com.example.kotlinl5.data.remote.model.PlayList

class PlayListViewModel: BaseViewModel() {

    var loading = MutableLiveData<Boolean>()

    fun getPlayList(): LiveData<Resource<PlayList>> {
        return App().repository.createPlayList()
    }
}