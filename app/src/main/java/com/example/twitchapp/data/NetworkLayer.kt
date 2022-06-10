package com.example.twitchapp.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkLayer {

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val tokenRetrofit = Retrofit.Builder()
        .baseUrl("https://id.twitch.tv/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
//        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val gameRetrofit = Retrofit.Builder()
        .baseUrl("https://api.twitch.tv/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
//        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val tokenApi: TokenApiInterface by lazy {
        tokenRetrofit.create(TokenApiInterface::class.java)
    }

    val gameApi: GamesApiInterface by lazy {
        gameRetrofit.create(GamesApiInterface::class.java)
    }

    val apiClient = DataProviderImpl(tokenApi, gameApi)
}