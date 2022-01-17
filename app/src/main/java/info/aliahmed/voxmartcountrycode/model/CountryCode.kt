package com.bracbank.voxmartnumberparser.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CountryCode")
data class CountryCode(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val countryName: String? = null,
    val countryShortName: String? = null,
    val countryCode: String? = null,
    val nationalPrefix: Int? = null,
    val countryIcon: String?= null
){
    override fun toString(): String {
        return "$countryShortName"
    }
}