package com.example.kotlinl5.domain.use_cases

import com.example.kotlinl5.domain.YoutubeRepository

class GetPlaylistItemsUseCase(private val youtubeRepository: YoutubeRepository) {
    fun getPlaylistItems(playlistId: String) = youtubeRepository.getPlaylistItems(playlistId)
}