package com.example.kotlinl5.ui.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinl5.BuildConfig.API_KEY
import com.example.kotlinl5.`object`.Constant
import com.example.kotlinl5.base.BaseViewModel
import com.example.kotlinl5.model.PlayList
import com.example.kotlinl5.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayListViewModel: BaseViewModel() {

    private val youTubeApi = RetrofitClient.create()

    fun getPlayList(): LiveData<PlayList> {
        return createPlayList()
    }

    private fun createPlayList(): LiveData<PlayList> {

        val data = MutableLiveData<PlayList>()

        youTubeApi.getPlayLists(Constant.PART, Constant.CHANEL_ID, API_KEY)
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
}