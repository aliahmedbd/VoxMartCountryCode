package info.aliahmed.voxmartcountrycode.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bracbank.voxmartnumberparser.model.CountryCode
import com.bracbank.voxmartnumberparser.model.Profile

@Database(entities = [CountryCode::class, Profile::class], version = 2)
abstract class CountryCodeDatabase : RoomDatabase() {
    abstract val countryCodeDao: CountryCodeDao
}