package com.wafflestudio.waffleseminar2024.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wafflestudio.waffleseminar2024.Movie
import com.wafflestudio.waffleseminar2024.MovieItem
import com.wafflestudio.waffleseminar2024.data.database.MovieRepository
import com.wafflestudio.waffleseminar2024.data.database.MyEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movie = MutableLiveData<MyEntity>()
    val movie: LiveData<MyEntity> get() = _movie

    private val _searchResults = MutableLiveData<List<MyEntity>>()
    val searchResults: LiveData<List<MyEntity>> get() = _searchResults

    fun updateMovie(movie: MyEntity) {
        _movie.value = movie
    }

    fun fetchMovieDetails(id: Int) {
        viewModelScope.launch {
            val movieDetails = withContext(Dispatchers.IO) {
                repository.getMovieById(id)
            }
            _movie.value = movieDetails
        }
    }

    fun titleQuery(titleWord: String) {
        viewModelScope.launch {
            val movies = withContext(Dispatchers.IO) {
                repository.getMoviesByTitle(titleWord)
            }
            _searchResults.value = movies
        }
    }

    fun genreQuery(genreId: Int) {
        viewModelScope.launch {
            val movies = withContext(Dispatchers.IO) {
                repository.getMoviesByGenre(genreId)
            }
            _searchResults.value = movies
        }
    }
}