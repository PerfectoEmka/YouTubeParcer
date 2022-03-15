package com.example.kotlinl5.data.remote

import com.example.kotlinl5.BuildConfig
import com.example.kotlinl5.core.network.BaseDataSource
import com.example.kotlinl5.utils.`object`.Constant
import org.koin.dsl.module

val remoteDataSource = module {
    factory { RemoteDataSource(get()) }
}

class RemoteDataSource(private val youTubeApi: YouTubeApi): BaseDataSource() {

    suspend fun getPlaylist() = getResult {
        youTubeApi.getPlaylists(
            Constant.PART,
            Constant.CHANNEL_ID,
            BuildConfig.API_KEY,
            20)
    }

    suspend fun getPlaylistItems(playlistId: String) = getResult {
        youTubeApi.getPlaylistItems(
            Constant.PART,
            playlistId,
            BuildConfig.API_KEY,
            20)
    }

    suspend fun getVideo(videoId: String) = getResult {
        youTubeApi.getVideo(
            Constant.PART,
            BuildConfig.API_KEY,
            videoId)
    }
}