package com.example.movieappsolera.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieappsolera.data.local.dao.MovieDao
import com.example.movieappsolera.data.local.entities.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {


    abstract fun movieDao(): MovieDao
}