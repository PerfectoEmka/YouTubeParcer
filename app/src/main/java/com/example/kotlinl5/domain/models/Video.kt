package com.example.kotlinl5.domain.models

data class Video(
    val etag: String,
    val items: List<Item>,
    val kind: String,
    val pageInfo: PageInfo
)

data class Standard(
    val height: Int,
    val url: String,
    val width: Int
)

data class Maxres(
    val height: Int,
    val url: String,
    val width: Int
)

data class Item(
    val contentDetails: ContentDetails,
    val etag: String,
    val id: String,
    val kind: String,
    val snippet: Snippet
)
