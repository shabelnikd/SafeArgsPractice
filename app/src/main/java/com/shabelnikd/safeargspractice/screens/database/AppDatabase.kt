package com.shabelnikd.safeargspractice.screens.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shabelnikd.safeargspractice.screens.database.entities.UserDbEntity

@Database(
    version = 1,
    entities = [
        UserDbEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUsersDao(): UsersDao
}