package com.wafflestudio.waffleseminar2024.data.database

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavmovieRepository(private val myDao: MyDao) {
    suspend fun getFavoriteMovies(): List<MyEntity> {
        return myDao.getFavoriteMovies()
    }

    suspend fun updateFavoriteStatus(id: Int, isFavorite: Boolean) {
        myDao.updateFavoriteStatus(id, isFavorite)
    }

    suspend fun isFavoriteMovie(movieId: Int): Boolean {
        return myDao.isFavoriteMovie(movieId)
    }

    suspend fun getCount(): Int{
        return myDao.getCount()
    }
}