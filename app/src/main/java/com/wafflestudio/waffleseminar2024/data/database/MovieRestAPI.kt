package com.wafflestudio.waffleseminar2024.data.database

import com.wafflestudio.waffleseminar2024.BuildConfig
import com.wafflestudio.waffleseminar2024.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieRestAPI {
    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("with_genres") genreId: Int,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("include_adult") includeAdult: Boolean = true,
        @Query("include_video") includeVideo: Boolean = true,
        @Query("page") page: Int = 1
    ): MovieResponse

    @GET("search/movie")
    suspend fun getMoviesByTitle(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("query") movieTitle: String,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("include_adult") includeAdult: Boolean = true,
        @Query("include_video") includeVideo: Boolean = true,
        @Query("page") page: Int = 1
    ): MovieResponse
}