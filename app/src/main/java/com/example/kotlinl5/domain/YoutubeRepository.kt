package com.example.kotlinl5.domain

import androidx.lifecycle.LiveData
import com.example.kotlinl5.core.network.result.Resource
import com.example.kotlinl5.domain.models.PlayList
import com.example.kotlinl5.domain.models.Video

interface YoutubeRepository {
    fun getPlaylist(): LiveData<Resource<PlayList>>

    fun getPlaylistItems(playlistId: String): LiveData<Resource<PlayList>>

    fun getVideo(videoId: String): LiveData<Resource<Video>>
}