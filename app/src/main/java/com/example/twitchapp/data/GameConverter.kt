package com.example.twitchapp.data

import com.example.twitchapp.models.data.GameModelResponse
import com.example.twitchapp.models.domain.GameEntity

object GameConverter {

    fun convert(gameResponse: GameModelResponse): List<GameEntity> =
        gameResponse.data.map {
            GameEntity(
                it.id,
                it.name,
                it.image
            )
        }
}