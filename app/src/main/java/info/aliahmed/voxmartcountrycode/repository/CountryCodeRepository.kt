package info.aliahmed.voxmartcountrycode.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.bracbank.voxmartnumberparser.model.CountryCode
import info.aliahmed.voxmartcountrycode.database.CountryCodeDatabase
import javax.inject.Inject

class CountryCodeRepository @Inject constructor(private val countryCodeDatabase: CountryCodeDatabase) {
    val countryCode: LiveData<List<CountryCode>> =
        Transformations.map(countryCodeDatabase.countryCodeDao.getAllCountryCode()) {
            it
        }

}