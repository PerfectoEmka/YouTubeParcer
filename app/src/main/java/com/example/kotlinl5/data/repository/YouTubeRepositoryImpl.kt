package com.example.kotlinl5.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.kotlinl5.core.network.result.Resource
import com.example.kotlinl5.data.remote.RemoteDataSource
import com.example.kotlinl5.domain.YoutubeRepository
import com.example.kotlinl5.domain.models.PlayList
import com.example.kotlinl5.domain.models.Video
import kotlinx.coroutines.Dispatchers

class YouTubeRepositoryImpl(private val dataSource: RemoteDataSource): YoutubeRepository {

    override fun getPlaylist():
            LiveData<Resource<PlayList>> = liveData(Dispatchers.IO){
        emit(Resource.loading()) // emit observes thread
        val response = dataSource.getPlaylist()
        emit(response)
    }

    override fun getPlaylistItems(playlistId: String):
            LiveData<Resource<PlayList>> = liveData(Dispatchers.IO){
        emit(Resource.loading())
        val response = dataSource.getPlaylistItems(playlistId)
        emit(response)
    }

    override fun getVideo(videoId: String):
            LiveData<Resource<Video>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val response = dataSource.getVideo(videoId)
        emit(response)
    }
}