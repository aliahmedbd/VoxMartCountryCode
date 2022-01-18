package info.aliahmed.voxmartcountrycode.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import info.aliahmed.voxmartcountrycode.adapters.CountryListAdapter
import info.aliahmed.voxmartcountrycode.databinding.FragmentCountryCodeBinding
import info.aliahmed.voxmartcountrycode.databinding.FragmentDialBinding
import info.aliahmed.voxmartcountrycode.viewmodel.CountryCodeViewModel
import android.widget.EditText

import android.widget.LinearLayout
import android.widget.Toast
import com.bracbank.voxmartnumberparser.model.CountryCode

import com.google.android.material.bottomsheet.BottomSheetDialog
import info.aliahmed.voxmartcountrycode.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class CountryCodeFragment : Fragment() {

    private lateinit var edtCountyName: EditText
    private lateinit var edtCountryCode: EditText
    private lateinit var edtCountryShort: EditText
    private lateinit var edtNationalPrefix: EditText
    private lateinit var cancel: Button
    private lateinit var submit: Button
    lateinit var binding: FragmentCountryCodeBinding
    private val viewModel: CountryCodeViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryCodeBinding.inflate(inflater, container, false)
        initialize()
        return binding.root
    }

    private fun initialize() {
        binding.rvCountyList.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getCountryCode().observe(viewLifecycleOwner, {
            val adapter = CountryListAdapter(it)
            binding.rvCountyList.adapter = adapter
        })

        binding.floatingActionButton.setOnClickListener {
            showBottomSheetDialog()
        }
    }

    private fun showBottomSheetDialog() {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog)
        edtCountyName = bottomSheetDialog.findViewById(R.id.edtCountyName)!!
        edtCountryCode = bottomSheetDialog.findViewById(R.id.edtCountryCode)!!
        edtCountryShort = bottomSheetDialog.findViewById(R.id.edtCountryShort)!!
        edtNationalPrefix = bottomSheetDialog.findViewById(R.id.edtNationalPrefiix)!!
        cancel = bottomSheetDialog.findViewById(R.id.btnCancel)!!
        submit = bottomSheetDialog.findViewById(R.id.btnSubmit)!!

        cancel.setOnClickListener {
            bottomSheetDialog.cancel()
        }

        submit.setOnClickListener {
            if (formValidation()) {
                submitCountryCode()
            }
        }

        bottomSheetDialog.show()
    }

    private fun submitCountryCode() {
        val countryCode = CountryCode(
            countryName = edtCountyName.text.toString(),
            countryShortName = edtCountryShort.text.toString(),
            countryCode = edtCountryCode.text.toString()
        )
        viewModel.addCountryCode(countryCode)
        Toast.makeText(requireContext(), "Country code added successfully.", Toast.LENGTH_LONG).show()
    }

    private fun formValidation(): Boolean {
        when {
            edtCountyName.text.toString().isEmpty() -> {
                edtCountyName.error = "Please enter name"
                return false
            }
            edtCountryCode.text.toString().isEmpty() -> {
                edtCountryCode.error = "Please country code"
                return false
            }
            edtCountryShort.text.toString().isEmpty() -> {
                edtCountryShort.error = "Please country short name (ex: UK)"
                return false
            }
            edtNationalPrefix.text.toString().isEmpty() -> {
                edtNationalPrefix.error = "Please national prefix"
                return false
            }
            else -> {
                return true
            }
        }
    }
}