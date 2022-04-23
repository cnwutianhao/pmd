package com.tyhoo.android.compose.pmd.api

import com.tyhoo.android.compose.pmd.data.NewsResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PMDService {

    @GET("json/newslist/news")
    suspend fun news(
        @Query("r") r: String
    ): NewsResponse

    companion object {
        private const val BASE_URL = "https://api.ithome.com/"

        fun create(): PMDService {
            val logger = HttpLoggingInterceptor().apply { level = Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PMDService::class.java)
        }
    }
}