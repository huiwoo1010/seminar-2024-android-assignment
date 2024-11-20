package com.wafflestudio.waffleseminar2024.data.database

import com.wafflestudio.waffleseminar2024.MovieItem


class MovieRepository(private val apiService: MovieRestAPI) {

    /*fun getMovieById(id: Int): MyEntity {
        return myDao.getMyEntityById(id)
    }*/


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
                status = ""
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
                status = ""
            )
        }
    }
}
