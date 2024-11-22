package com.wafflestudio.waffleseminar2024.data.database

class FavmovieRepository(private val myDao: MyDao) {
    suspend fun getFavoriteMovies(): List<MyEntity> {
        return myDao.getFavoriteMovies()
    }

    suspend fun updateFavoriteStatus(id: Int, isFavorite: Boolean) {
        myDao.updateFavoriteStatus(id, isFavorite)
    }

    suspend fun saveMovie(movie: MyEntity) {
        myDao.insertOrUpdateMovie(movie)
    }
}