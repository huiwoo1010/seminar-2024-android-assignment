package com.wafflestudio.waffleseminar2024.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wafflestudio.waffleseminar2024.Movie

@Dao
interface MyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMyEntity(entity: MyEntity)

    @Query("SELECT * FROM example_table2")
    fun getAllMyEntities(): List<MyEntity>

    @Query("SELECT * FROM example_table2 WHERE id = :id")
    fun getMyEntityById(id:Int): MyEntity

    @Query("SELECT * FROM example_table2 WHERE title LIKE '%' || :titleWord || '%'")
    fun getMoviesByTitle(titleWord: String): List<MyEntity>

    @Query("SELECT * FROM example_table2 WHERE genre_ids LIKE '%' || :genreId || '%'")
    fun getMoviesByGenre(genreId: Int): List<MyEntity>


    @Query("SELECT * FROM example_table2 WHERE isFavorite = 1")
    suspend fun getFavoriteMovies(): List<MyEntity>

    @Query("UPDATE example_table2 SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteStatus(id: Int, isFavorite: Boolean)

    @Query("SELECT COUNT(*) FROM example_table2")
    suspend fun getCount(): Int

    @Query("SELECT isFavorite FROM example_table2 WHERE id = :movieId")
    suspend fun isFavoriteMovie(movieId: Int): Boolean

}