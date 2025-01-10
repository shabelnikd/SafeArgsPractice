package com.shabelnikd.safeargspractice.screens.database

import com.shabelnikd.safeargspractice.screens.database.entities.UserDbEntity
import com.shabelnikd.safeargspractice.screens.database.entities.UserTuple
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val usersDao: UsersDao) {
    suspend fun insertNewUser(carDbEntity: UserDbEntity) {
        withContext(Dispatchers.IO) {
            usersDao.insertNewUser(carDbEntity)
        }
    }

    suspend fun getAllUsers(): List<UserTuple> {
        return withContext(Dispatchers.IO) {
            return@withContext usersDao.getAllUsers()
        }
    }

    suspend fun deleteUserById(userId: Long) {
        withContext(Dispatchers.IO) {
            usersDao.deleteUserById(userId)
        }
    }

}