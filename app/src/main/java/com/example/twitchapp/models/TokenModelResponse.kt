package com.example.twitchapp.models

import com.google.gson.annotations.SerializedName

data class TokenModelResponse(
    @SerializedName("access_token") val token: String,
    @SerializedName("expires_in") val expiration: Int,
    @SerializedName("token_type") val tokenType: Int,
)
