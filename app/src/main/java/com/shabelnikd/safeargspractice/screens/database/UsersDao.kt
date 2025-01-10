package com.shabelnikd.safeargspractice.screens.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.shabelnikd.safeargspractice.screens.database.entities.UserDbEntity
import com.shabelnikd.safeargspractice.screens.database.entities.UserTuple

@Dao
interface UsersDao {

    @Insert(entity = UserDbEntity::class)
    fun insertNewUser(user: UserDbEntity)

    @Query("SELECT id, name, email, password FROM Users")
    fun getAllUsers(): List<UserTuple>


    @Query("DELETE FROM Users WHERE id = :userId")
    fun deleteUserById(userId: Long)

}