package com.example.twitchapp.models

import com.squareup.moshi.Json

data class TokenModelResponse(
    @Json(name = "access_token") val token: String,
    @Json(name = "expires_in") val expiration: Int,
    @Json(name = "token_type") val tokenType: String,
)
