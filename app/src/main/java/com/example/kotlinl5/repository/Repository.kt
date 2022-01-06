package com.example.kotlinl5.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinl5.App
import com.example.kotlinl5.BuildConfig
import com.example.kotlinl5.data.remote.model.Items
import com.example.kotlinl5.utils.`object`.Constant
import com.example.kotlinl5.data.remote.model.PlayList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    fun createPlayList(): LiveData<PlayList> {
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
    }

    fun createPlaylistItems(playListId: String): LiveData<PlayList> {
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
    }
}