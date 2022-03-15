package com.example.kotlinl5.domain.use_cases

import com.example.kotlinl5.domain.YoutubeRepository

class GetPlaylistUseCase(private val youtubeRepository: YoutubeRepository) {
    fun getPlaylist() = youtubeRepository.getPlaylist()
}