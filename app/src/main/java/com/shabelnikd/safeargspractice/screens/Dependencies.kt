package com.shabelnikd.safeargspractice.screens

import android.content.Context
import androidx.room.Room
import com.shabelnikd.safeargspractice.screens.database.AppDatabase
import com.shabelnikd.safeargspractice.screens.database.UserRepository

object Dependencies {

    private var _applicationContext: Context? = null
    private val applicationContext get() = _applicationContext!!

    fun init(context: Context) {
        _applicationContext = context
        applicationContext.applicationContext
    }

    private val appDatabase: AppDatabase by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "users.db").build()
    }

    val usersRepository: UserRepository by lazy { UserRepository(appDatabase.getUsersDao()) }


}