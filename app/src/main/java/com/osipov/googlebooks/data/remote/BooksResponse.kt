package com.osipov.googlebooks.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class BooksResponse(
    val totalItems: Int,
    val items: List<Item>
)

@Serializable
data class Item(
    val volumeInfo: VolumeInfo
)

@Serializable
data class VolumeInfo(
    val title: String,
    val authors: List<String>? = emptyList(),
    val imageLinks: ImageLinks? = null,
    val previewLink: String
)

@Serializable
data class ImageLinks(
    val smallThumbnail: String,
    val thumbnail: String
)