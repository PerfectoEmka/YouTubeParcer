package com.example.kotlinl5.remote

import com.example.kotlinl5.model.PlayList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApi {

    @GET("playlists")
    fun getPlayLists(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") apiKey: String,
    ): Call<PlayList>
}