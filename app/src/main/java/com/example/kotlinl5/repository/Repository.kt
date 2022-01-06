package com.example.kotlinl5.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.kotlinl5.App
import com.example.kotlinl5.BuildConfig
import com.example.kotlinl5.core.network.result.Resource

import com.example.kotlinl5.utils.`object`.Constant
import com.example.kotlinl5.data.remote.model.PlayList
import kotlinx.coroutines.Dispatchers

class Repository {

    fun createPlayList(): LiveData<Resource<PlayList>> = liveData(Dispatchers.IO){
        emit(Resource.loading()) // emit observes thread
        val response = App().youTubeApi.getPlaylists(Constant.PART, Constant.CHANNEL_ID, BuildConfig.API_KEY, 20)
        if (response.isSuccessful && response.body() != null){
            emit(Resource.success(response.body()))
        } else {
            emit(Resource.error(response.message(), response.body(), response.code()))
        }
    }

    fun createPlaylistItems(playlistId: String)
    : LiveData<Resource<PlayList>> = liveData(Dispatchers.IO){
        emit(Resource.loading())
        val response = App().youTubeApi.getPlaylistItems(Constant.PART, playlistId, BuildConfig.API_KEY, 20)
        if (response.isSuccessful && response.body() != null){
            emit(Resource.success(response.body()))
        } else {
            emit(Resource.error(response.message(), response.body(), response.code()))
        }
    }

    /*fun createPlaylistItems(playListId: String): LiveData<PlayList> {
        val data = MutableLiveData<PlayList>()

        App().youTubeApi.getPlaylistItems(Constant.PART, playListId, BuildConfig.API_KEY, 20)
            .enqueue(object : Callback<PlayList>{
                override fun onResponse(call: Call<PlayList>, response: Response<PlayList>) {
                    if (response.isSuccessful && response.body() != null){
                        data.value = response.body()
                    }
                }
                override fun onFailure(call: Call<PlayList>, t: Throwable) {
                    print(t.stackTrace)
                }
            })
        return data
    }*/


    /* fun createPlaylist(): LiveData<PlayList> {
         val data = MutableLiveData<PlayList>()

         App().youTubeApi.getPlaylists(Constant.PART, Constant.CHANNEL_ID, BuildConfig.API_KEY, 20)
             .enqueue(object : Callback<PlayList> {
                 override fun onResponse(call: Call<PlayList>, response: Response<PlayList>) {
                     if (response.isSuccessful && response.body() != null){
                         data.value = response.body()
                     }
                 }
                 override fun onFailure(call: Call<PlayList>, t: Throwable) {
                     print(t.stackTrace)
                 }
             })
         return data
     }*/
}