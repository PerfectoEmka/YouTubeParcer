package com.example.kotlinl5.data.remote

import com.example.kotlinl5.domain.models.PlayList
import com.example.kotlinl5.domain.models.Video
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApi {

    @GET("playlists")
   suspend fun getPlaylists( // suspend управляет поочередно
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") apiKey: String,
        @Query("maxResults") maxResults: Int,
    ): Response<PlayList>

    @GET("playlistItems")
    suspend fun getPlaylistItems(
        @Query("part") part: String,
        @Query("playlistId") id: String,
        @Query("key") apiKey: String,
        @Query("maxResults") maxResults: Int,
    ): Response<PlayList>

    @GET("videos")
    suspend fun getVideo(
        @Query("part") part: String,
        @Query("key") apiKey: String,
        @Query("id") id: String
    ): Response<Video>
}