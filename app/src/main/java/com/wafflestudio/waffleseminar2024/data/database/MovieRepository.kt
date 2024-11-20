package com.wafflestudio.waffleseminar2024.data.database

import com.wafflestudio.waffleseminar2024.MovieItem


class MovieRepository(private val apiService: MovieRestAPI) {

    suspend fun getMovieById(id: Int): MyEntity {
        val response = apiService.getMovieById(movieId = id)

        return MyEntity(
            id = response.id,
            title = response.title,
            original_title = response.original_title,
            backdrop_path = response.backdrop_path ?: "",
            overview = response.overview ?: "",
            poster_path = response.poster_path ?: "",
            release_date = response.release_date ?: "",
            vote_average = response.vote_average ?: 0.0,
            genres = response.genres,
            budget = response.budget ?: 0,
            revenue = response.revenue ?: 0,
            status = response.status,
            runtime = response.runtime,
            homepage = "",
            original_language = "",
            popularity = 0.0f,
            production_companies = "",
            production_countries = "",
            spoken_languages = "",
            tagline = "",
            vote_count = 0,
            genre_ids = emptyList()
        )
    }


    suspend fun getMoviesByTitle(titleWord: String): List<MyEntity> {
        val response = apiService.getMoviesByTitle(movieTitle = titleWord)

        return response.results.map { movieResponse ->
            MyEntity(
                id = movieResponse.id,
                title = movieResponse.title,
                original_title = movieResponse.original_title,
                backdrop_path = movieResponse.backdrop_path ?: "",
                overview = movieResponse.overview ?: "",
                poster_path = movieResponse.poster_path ?: "",
                release_date = movieResponse.release_date ?: "",
                vote_average = movieResponse.vote_average ?: 0.0,
                genre_ids = movieResponse.genre_ids,
                budget = 0,
                revenue = 0,
                homepage = "",
                original_language = "",
                popularity = 0.0f,
                production_companies = "",
                production_countries = "",
                spoken_languages = "",
                tagline = "",
                vote_count = 0,
                runtime = 0,
                status = "",
                genres = emptyList()
            )
        }
    }


    suspend fun getMoviesByGenre(genreId: Int): List<MyEntity> {
        val response = apiService.getMoviesByGenre(genreId = genreId)

        return response.results.map { movieResponse ->
            MyEntity(
                id = movieResponse.id,
                title = movieResponse.title,
                original_title = movieResponse.original_title,
                backdrop_path = movieResponse.backdrop_path ?: "",
                overview = movieResponse.overview ?: "",
                poster_path = movieResponse.poster_path ?: "",
                release_date = movieResponse.release_date ?: "",
                vote_average = movieResponse.vote_average ?: 0.0,
                genre_ids = movieResponse.genre_ids,
                budget = 0,
                revenue = 0,
                homepage = "",
                original_language = "",
                popularity = 0.0f,
                production_companies = "",
                production_countries = "",
                spoken_languages = "",
                tagline = "",
                vote_count = 0,
                runtime = 0,
                status = "",
                genres = emptyList()
            )
        }
    }
}
