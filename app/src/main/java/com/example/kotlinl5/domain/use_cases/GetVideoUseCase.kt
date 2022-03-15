package com.example.kotlinl5.domain.use_cases

import com.example.kotlinl5.domain.YoutubeRepository

class GetVideoUseCase(private val youtubeRepository: YoutubeRepository) {
    fun getVideo(videoId: String) = youtubeRepository.getVideo(videoId)
}