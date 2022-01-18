package info.aliahmed.voxmartcountrycode.util

class NumberParser(
    countryCodes: Map<String?, String?>?,
    nationalTrunkPrefixes: Map<String?, Int?>?,
    destination: String?
) {

    private lateinit var countryCodes: Map<String?, String?>
    private lateinit var nationalTrunkPrefix: Map<String?, Int?>
    private lateinit var destination: String

    init {
        countryCodes?.let { this.countryCodes = it }
        nationalTrunkPrefixes?.let { this.nationalTrunkPrefix = it }
        destination?.let { this.destination = it }
    }

    fun parse(dialledNumber: String?, userNumber: String?): String? {
        val internationalCode = countryCodes[destination]
        if (validNumber(dialledNumber, userNumber) && dialledNumber?.let { validCountryDialNumber(it) } == true) {
            return if (internationalCode?.let { dialledNumber.contains(it) } == true) {
                dialledNumber
            } else {
                val number = dialledNumber?.drop(1)
                return internationalCode + number
            }
        }
        return null
    }

    private fun validNumber(dialledNumber: String?, userNumber: String?): Boolean {
        return when {
            dialledNumber.isNullOrEmpty() -> {
                false
            }
            userNumber.isNullOrEmpty() -> {
                false
            }
            userNumber.checkSqlInjection() || dialledNumber.checkSqlInjection() -> {
                false
            }
            userNumber.checkValidNumber() && dialledNumber.checkValidNumber() -> {
                true
            }
            else -> {
                false
            }
        }
    }

    fun validNumber(number: String): Boolean {
        return when {
            number.isNullOrEmpty() -> {
                false
            }
            number.checkSqlInjection() -> {
                false
            }
            number.checkValidNumber() -> {
                true
            }
            else -> {
                false
            }
        }
    }

    fun validCountryDialNumber(number: String): Boolean {
        val countryCode = countryCodes[destination]
        val nationalPrefix = nationalTrunkPrefix[destination].toString()
        if (countryCode?.let { number.contains(it) } == true) {
            return true
        } else if (number.startsWith(nationalPrefix)) {
            return true
        }
        return false
    }
}