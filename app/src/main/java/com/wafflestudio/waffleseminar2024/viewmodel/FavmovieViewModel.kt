package com.wafflestudio.waffleseminar2024.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wafflestudio.waffleseminar2024.data.database.FavmovieRepository
import com.wafflestudio.waffleseminar2024.data.database.MyEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FavmovieViewModel(private val repository: FavmovieRepository) : ViewModel() {
    private val _favoriteMovies = MutableLiveData<List<MyEntity>>()
    val favoriteMovies: LiveData<List<MyEntity>> get() = _favoriteMovies

    private val _isFavoriteMovie = MutableLiveData<Boolean>()
    val isFavoriteMovie: LiveData<Boolean> get() = _isFavoriteMovie

    suspend fun toggleFavoriteStatus(movie: MyEntity) {
        val newStatus = movie.isFavorite
        Log.d("MyApp","newStatus")
        Log.d("MyApp",newStatus.toString())
        repository.updateFavoriteStatus(movie.id!!, newStatus)
        loadFavoriteMovies()
        Log.d("MyApp",getFavoriteMoviesCount().toString())
    }

    fun isFavoriteMovie(movieId: Int) {
        viewModelScope.launch {
            val result = repository.isFavoriteMovie(movieId)
            _isFavoriteMovie.postValue(result)
        }
    }

    suspend fun loadFavoriteMovies() {
        _favoriteMovies.value = repository.getFavoriteMovies()
    }

    suspend fun getCount(): Int{
        return repository.getCount()
    }

    fun getFavoriteMoviesCount(): Int {
        return _favoriteMovies.value?.size ?: 0
    }
}