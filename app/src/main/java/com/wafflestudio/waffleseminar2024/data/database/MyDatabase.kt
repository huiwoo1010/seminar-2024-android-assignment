package com.wafflestudio.waffleseminar2024.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.migration.Migration

@Database(entities = [MyEntity::class], version = 2)
@TypeConverters(MyConverters::class)  // MyConverters에서 TypeConverter 적용
abstract class MyDatabase : RoomDatabase() {
    abstract fun myDao(): MyDao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        // Migration 정의
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE example_table2 ADD COLUMN genre_ids TEXT")
                database.execSQL("ALTER TABLE example_table2 ADD COLUMN isFavorite INTEGER NOT NULL DEFAULT 0")
            }
        }

        fun getDatabase(context: Context): MyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "example_database"
                )
                    .createFromAsset("database/prepopulated_db.db")
                    .addMigrations(MIGRATION_1_2)  // 마이그레이션 추가
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}
