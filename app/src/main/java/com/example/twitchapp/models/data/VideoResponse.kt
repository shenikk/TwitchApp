package com.example.twitchapp.models.data

import com.squareup.moshi.Json

data class VideoResponse(
    val data: List<VideoModel>,
    val pagination: Pagination
)

data class VideoModel(
    @Json(name = "id") val id: String,
    @Json(name = "stream_id") val streamId: String?,
    @Json(name = "user_id") val userId: String,
    @Json(name = "user_login") val userLogin: String,
    @Json(name = "user_name") val userName: String,
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String,
    @Json(name = "created_at") val created: String,
    @Json(name = "published_at") val published: String,
    @Json(name = "url") val url: String,
    @Json(name = "thumbnail_url") val thumbnailUrl: String,
    @Json(name = "viewable") val viewable: String,
    @Json(name = "view_count") val viewCount: Long,
    @Json(name = "language") val language: String,
    @Json(name = "type") val type: String,
    @Json(name = "duration") val duration: String,
    @Json(name = "muted_segments") val mutedSegments: List<Segment>?

)

data class Segment(
    @Json(name = "duration") val duration: Long,
    @Json(name = "offset") val offset: Long
)

data class Pagination(
    @Json(name = "cursor") val cursor: String
)