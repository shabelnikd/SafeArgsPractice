package com.shabelnikd.safeargspractice.screens.database.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserTuple(
    val id: Long,
    val name: String,
    val email: String,
    val password: Int
) : Parcelable
