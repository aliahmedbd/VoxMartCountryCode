package info.aliahmed.voxmartcountrycode.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bracbank.voxmartnumberparser.model.CountryCode
import info.aliahmed.voxmartcountrycode.R
import info.aliahmed.voxmartcountrycode.databinding.ItemCountryCodeBinding

class CountryListAdapter(private val items: List<CountryCode>) : RecyclerView.Adapter<CountryListAdapter.ViewHolder>() {
    // and return new ViewHolder object containing this layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCountryCodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    inner class ViewHolder(val binding: ItemCountryCodeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(items[position]) {
                binding.txtCountryName.text = "$countryName ($countryShortName)"
                binding.txtNationalPrefix.text = "National Prefix : $nationalPrefix"
                binding.txtInternationalCode.text = "International Code : $countryCode"
            }
        }
    }
}