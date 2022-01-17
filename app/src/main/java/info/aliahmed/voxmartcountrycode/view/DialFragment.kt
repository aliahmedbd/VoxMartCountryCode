package info.aliahmed.voxmartcountrycode.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.app.Activity
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bracbank.voxmartnumberparser.model.CountryCode
import info.aliahmed.voxmartcountrycode.databinding.FragmentDialBinding
import info.aliahmed.voxmartcountrycode.util.NumberParser
import info.aliahmed.voxmartcountrycode.viewmodel.CountryCodeViewModel


class DialFragment : Fragment() {

    private lateinit var nationalTrunkPrefixes: MutableMap<String, Int?>
    private lateinit var countryCodeMap: MutableMap<String, String?>
    lateinit var binding: FragmentDialBinding
    private val viewModel: CountryCodeViewModel by activityViewModels()
    private lateinit var numberParser: NumberParser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDialBinding.inflate(inflater, container, false)
        initialize()
        return binding.root
    }

    private fun initialize() {
        showSoftKeyboard(binding.edtDialNumber)
        setSpinnerData()

        binding.btnParse.setOnClickListener {
            numberParser = NumberParser(
                countryCodeMap.toMap(),
                nationalTrunkPrefixes.toMap(),
                binding.spinnerFromCountry.text.toString()
            )

            if (binding.spinnerToCountry.text.toString().isEmpty()) {
                binding.spinnerToCountry.error = "Please select country"
            } else if (binding.spinnerFromCountry.text.toString().isEmpty()) {
                binding.spinnerFromCountry.error = "Please select county"
            } else if (binding.edtUserNumber.text.toString().isEmpty()) {
                binding.edtUserNumber.error = "Please enter user number"
            } else if (binding.edtDialNumber.text.toString().isEmpty()) {
                binding.edtDialNumber.error = "Please enter dial number"
            } else {
                val dialNumber = "${binding.edtDialNumber.text}"
                val userNumber = "${binding.edtUserNumber.text}"
                if (numberParser.validNumber(dialNumber, userNumber)) {
                    binding.edtParseNumber.setText(numberParser.parse(dialNumber, userNumber))
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Please enter the valid number",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun setSpinnerData() {
        viewModel.getCountryCode().observe(viewLifecycleOwner, {

            countryCodeMap = mutableMapOf<String, String?>()
            nationalTrunkPrefixes = mutableMapOf<String, Int?>()

            it.forEach { countryCode ->
                countryCode.countryShortName?.let { it1 ->
                    countryCodeMap.put(
                        it1,
                        countryCode.countryCode
                    )
                }
                countryCode.countryShortName?.let { it1 ->
                    nationalTrunkPrefixes.put(
                        it1,
                        countryCode.nationalPrefix
                    )
                }
            }
            val countryCodes = it
            val toArrayAdapter = ArrayAdapter<CountryCode>(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                countryCodes
            )
            val fromArrayAdapter = ArrayAdapter<CountryCode>(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                countryCodes
            )
            binding.apply {
                spinnerFromCountry.setAdapter(fromArrayAdapter)
                spinnerToCountry.setAdapter(toArrayAdapter)
            }
        })
    }

    private fun showSoftKeyboard(view: View) {
        val inputMethodManager: InputMethodManager =
            requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        view.requestFocus()
        inputMethodManager.showSoftInput(view, 0)
    }

    private fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
    }

}