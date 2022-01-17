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
        if (validNumber(dialledNumber, userNumber)) {
            return if (internationalCode?.let { dialledNumber?.contains(it) } == true) {
                dialledNumber
            } else {
                val number = dialledNumber?.drop(1)
                return internationalCode + number
            }
        }
        return null
    }

    fun validNumber(dialledNumber: String?, userNumber: String?): Boolean {
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
}