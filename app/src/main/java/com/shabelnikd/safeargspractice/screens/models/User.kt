package com.shabelnikd.safeargspractice.screens.models

import com.shabelnikd.safeargspractice.screens.database.entities.UserDbEntity
import java.io.Serializable


data class User(
    val name: String,
    val email: String,
    val password: Int
) : Serializable {

    fun toUsersDbEntity(): UserDbEntity = UserDbEntity(
        id = 0,
        name = name,
        email = email,
        password = password
    )

}