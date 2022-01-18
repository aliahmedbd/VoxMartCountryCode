package info.aliahmed.voxmartcountrycode.util

import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import com.google.common.truth.Truth.assertThat

/**
 * Here all the test case are written for Number Parser, I tried to write most of the test cases,
 *  more test case can be written but for time constraint I think these are enough for the time
 *  being.
 */
class NumberParserTest {

    private lateinit var countryCodes: Map<String?, String?>
    private lateinit var nationalTrunkPrefixes: Map<String?, Int?>
    lateinit var destination: String

    @Before
    fun setUp() {
        countryCodes = mutableMapOf("UK" to "+44", "US" to "+1", "FR" to "+33")
        nationalTrunkPrefixes = mutableMapOf("UK" to 0, "US" to 1, "FR" to 0)
    }

    @Test
    fun `check valid dial number`() {
        val result =
            NumberParser(countryCodes, nationalTrunkPrefixes, "UK").validNumber("+447833733777")
        assertThat(result).isTrue()
    }

    @Test
    fun `check valid dial number according to country`(){
        val result =
            NumberParser(countryCodes, nationalTrunkPrefixes, "UK").validCountryDialNumber("+447833733777")
        assertThat(result).isTrue()
    }

    @Test
    fun `check invalid dial number according to country`(){
        val result =
            NumberParser(countryCodes, nationalTrunkPrefixes, "US").validCountryDialNumber("+447833733777")
        assertThat(result).isFalse()
    }

    @Test
    fun `check valid user number`() {
        val result =
            NumberParser(countryCodes, nationalTrunkPrefixes, "UK").validNumber("07277822334")
        assertThat(result).isTrue()
    }


    @Test
    fun `check with invalid number`() {
        val result =
            NumberParser(countryCodes, nationalTrunkPrefixes, "UK").validNumber("ABCD7277822334")
        assertThat(result).isFalse()
    }

    @Test
    fun `is valid parsing`() {
        val result = NumberParser(
            countryCodes,
            nationalTrunkPrefixes,
            "UK"
        ).parse(userNumber = "+447277822334", dialledNumber = "07833733777")
        assertThat(result).isEqualTo("+447833733777")
    }

    @Test
    fun `parsing number in different country`() {
        val result = NumberParser(
            countryCodes,
            nationalTrunkPrefixes,
            "US"
        ).parse(userNumber = "+447277822334", dialledNumber = "+1312233244")
        assertThat(result).isEqualTo("+1312233244")
    }

    @Test
    fun `parsing with wrong data`() {
        val result = NumberParser(
            countryCodes,
            nationalTrunkPrefixes,
            "UK"
        ).parse(userNumber = "ACBDR", dialledNumber = "sdfdsdfdfgs")
        assertThat(result).isEqualTo(null)
    }

    @Test
    fun `parsing check with empty data`() {
        val result = NumberParser(
            countryCodes,
            nationalTrunkPrefixes,
            "UK"
        ).parse(userNumber = "", dialledNumber = "")
        assertThat(result).isEqualTo(null)
    }

    @Test
    fun `parsing with null value`() {
        val result = NumberParser(
            countryCodes,
            nationalTrunkPrefixes,
            "UK"
        ).parse(userNumber = null, dialledNumber = null)
        assertThat(result).isEqualTo(null)
    }

    @Test
    fun `test with negative value`() {
        val result =
            NumberParser(countryCodes, nationalTrunkPrefixes, "UK").validNumber("-7277822334")
        assertThat(result).isFalse()
    }

}