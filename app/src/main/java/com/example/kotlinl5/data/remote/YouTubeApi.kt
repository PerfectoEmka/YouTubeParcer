package com.example.kotlinl5.data.remote

import com.example.kotlinl5.data.remote.model.PlayList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApi {

    @GET("playlists")
    fun getPlaylists(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") apiKey: String,
        @Query("maxResults") maxResults: Int,
    ): Call<PlayList>

    @GET("playlistItems")
    fun getPlaylistItems(
        @Query("part") part: String,
        @Query("playlistId") id: String,
        @Query("key") apiKey: String,
        @Query("maxResults") maxResults: Int,
    ): Call<PlayList>
}