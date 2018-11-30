package com.developersoul.yt

import android.media.Image

data class Schedule(
    val id: String,
    val name: String,
    val show: Show
)

data class Show(
    val id: String,
    val name: String,
    val image: ShowImage
)

data class ShowImage(
    val medium: String,
    val original: String
)

data class Episode(
    val id: String,
    val name: String,
    val airdate: String,
    val summary: String,
    val image: ShowImage?
)