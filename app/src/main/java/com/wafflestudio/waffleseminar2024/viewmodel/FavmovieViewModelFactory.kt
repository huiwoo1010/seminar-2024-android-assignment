package com.wafflestudio.waffleseminar2024.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wafflestudio.waffleseminar2024.data.database.FavmovieRepository
import com.wafflestudio.waffleseminar2024.data.database.MyDatabase.Companion.getDatabase

class FavmovieViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavmovieViewModel::class.java)) {
            val myDao = getDatabase(context).myDao()
            val repository = FavmovieRepository(myDao)
            return FavmovieViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}