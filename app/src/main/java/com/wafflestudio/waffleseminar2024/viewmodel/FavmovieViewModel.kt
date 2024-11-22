package com.wafflestudio.waffleseminar2024.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wafflestudio.waffleseminar2024.data.database.FavmovieRepository
import com.wafflestudio.waffleseminar2024.data.database.MyEntity

class FavmovieViewModel(private val repository: FavmovieRepository) : ViewModel() {
    private val _favoriteMovies = MutableLiveData<List<MyEntity>>()
    val favoriteMovies: LiveData<List<MyEntity>> get() = _favoriteMovies

    suspend fun toggleFavoriteStatus(movie: MyEntity) {
        val newStatus = !(movie.isFavorite)
        repository.updateFavoriteStatus(movie.id!!, newStatus)
        loadFavoriteMovies()
    }

    suspend fun loadFavoriteMovies() {
        _favoriteMovies.value = repository.getFavoriteMovies()
    }
}