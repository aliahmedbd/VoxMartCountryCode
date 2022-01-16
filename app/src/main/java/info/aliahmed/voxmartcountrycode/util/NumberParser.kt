package info.aliahmed.voxmartcountrycode.util

class NumberParser(
    countryCodes: Map<String?, Int?>?,
    nationalTrunkPrefixes: Map<String?, String?>?
) {

    private lateinit var countryCodes: Map<String?, Int?>
    private lateinit var namtionalTrunkPrefix: Map<String?, String?>

    init {
        countryCodes?.let { this.countryCodes = countryCodes }
        nationalTrunkPrefixes?.let { this.namtionalTrunkPrefix = nationalTrunkPrefixes }
    }

    fun parse(dialledNumber: String?, userNumber: String?): String? {
        return null
    }
}