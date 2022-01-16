package info.aliahmed.voxmartcountrycode.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bracbank.voxmartnumberparser.model.CountryCode
import dagger.hilt.android.lifecycle.HiltViewModel
import info.aliahmed.voxmartcountrycode.repository.CountryCodeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryCodeViewModel @Inject constructor(private val countryCodeRepository: CountryCodeRepository) :
    ViewModel() {

    fun getCountryCode(): LiveData<List<CountryCode>> {
        return countryCodeRepository.countryCode
    }

    fun addCountryCode(countryCode: CountryCode){
        viewModelScope.launch(Dispatchers.IO) {
            countryCodeRepository.addCountryCode(countryCode)
        }
    }

}