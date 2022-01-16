package info.aliahmed.voxmartcountrycode.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.app.Activity
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bracbank.voxmartnumberparser.model.CountryCode
import info.aliahmed.voxmartcountrycode.databinding.FragmentDialBinding
import info.aliahmed.voxmartcountrycode.viewmodel.CountryCodeViewModel


class DialFragment : Fragment() {

    lateinit var binding: FragmentDialBinding
    private val  viewModel : CountryCodeViewModel by activityViewModels()

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
        showSoftKeyboard(binding.edtDialedNumber)
        setSpinnerData()
    }

    private fun setSpinnerData(){
        viewModel.getCountryCode().observe(viewLifecycleOwner, {
            val countryCodes = it
            val toArrayAdapter = ArrayAdapter<CountryCode> (requireContext(), android.R.layout.simple_spinner_dropdown_item, countryCodes)
            val fromArrayAdapter = ArrayAdapter<CountryCode> (requireContext(), android.R.layout.simple_spinner_dropdown_item, countryCodes)
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