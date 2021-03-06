package info.aliahmed.voxmartcountrycode.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bracbank.voxmartnumberparser.model.CountryCode

@Dao
interface CountryCodeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) //if some data is same/conflict, it'll be replace with new data.
    fun insertCountryCode(countryCode: CountryCode)

    @Update
    fun updateCountryCode(countryCode: CountryCode)

    @Delete
    fun deleteCountryCode(countryCode: CountryCode)

    @Query("SELECT * FROM CountryCode ORDER BY countryName ASC ")
    fun getAllCountryCode(): LiveData<List<CountryCode>>

    @Query("DELETE FROM CountryCode")
    fun clearCountryCode()

    @Query("DELETE FROM CountryCode WHERE id = :id") //you can use this too, for delete note by id.
    fun deleteCountryCodeById(id: Int)
}