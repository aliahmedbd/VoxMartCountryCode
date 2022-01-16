package info.aliahmed.voxmartcountrycode.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import info.aliahmed.voxmartcountrycode.R
import info.aliahmed.voxmartcountrycode.adapters.CountryListAdapter
import info.aliahmed.voxmartcountrycode.databinding.FragmentCountryCodeBinding
import info.aliahmed.voxmartcountrycode.databinding.FragmentDialBinding
import info.aliahmed.voxmartcountrycode.viewmodel.CountryCodeViewModel

class CountryCodeFragment : Fragment() {

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
    }
}