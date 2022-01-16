package com.bracbank.voxmartnumberparser.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Profile")
data class Profile(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val name: String?,
    val profilePhoto: String?,
    val mobileNumber: String?,
)
