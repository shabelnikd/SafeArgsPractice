package com.shabelnikd.safeargspractice.screens.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Users",
    indices = [Index("id")]
)
data class UserDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val email: String,
    val password: Int
)
