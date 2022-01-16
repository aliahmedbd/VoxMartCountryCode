package info.aliahmed.voxmartcountrycode.view

import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.bracbank.voxmartnumberparser.model.CountryCode
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import info.aliahmed.voxmartcountrycode.R
import info.aliahmed.voxmartcountrycode.databinding.ActivityMainBinding
import info.aliahmed.voxmartcountrycode.viewmodel.CountryCodeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

     private val  viewModel : CountryCodeViewModel by viewModels()
//    private lateinit var viewModel: CountryCodeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setBottomNavigationBar()
        val actionbar = supportActionBar
        initialize()
    }

    private fun initialize() {
//        viewModel = ViewModelProvider(this)[CountryCodeViewModel::class.java]
        getAllCountryCode()
    }

    /**
     * Set bottom navigation with Nav controller as well the menu item also tagged with the
     * navigation
     */
    private fun setBottomNavigationBar() {
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        AppBarConfiguration(
            setOf(
                R.id.dialFragment,
                R.id.countryCodeFragment
            )
        )
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        showDialog()
    }

    /** Show an exit dialog to user confirmation */
    private fun showDialog() {
       val myQuittingDialogBox: AlertDialog = AlertDialog.Builder(this)
            .setTitle("Exit")
            .setIcon(R.mipmap.ic_launcher)
            .setMessage("Are you sure you want to exit the application?")
            .setPositiveButton(
                "Exit"
            ) { dialog: DialogInterface, _: Int ->
                moveTaskToBack(true)
                dialog.dismiss()
            }
            .setNegativeButton(
                "No"
            ) { dialog: DialogInterface, which: Int -> dialog.dismiss() }
            .create()
        myQuittingDialogBox.setOnShowListener {
            myQuittingDialogBox.getButton(AlertDialog.BUTTON_NEGATIVE)
                .setTextColor(Color.parseColor("#2f6699"))
            myQuittingDialogBox.getButton(AlertDialog.BUTTON_POSITIVE)
                .setTextColor(Color.parseColor("#90FF4500"))
        }
        myQuittingDialogBox.show()
    }

    /**
     * Inter Initial Country code function will insert some startup data for future use
     */
    private fun insertInitialCountryCodes() {
        val ukCode = CountryCode(
            countryName = "United Kingdom",
            countryShortName = "UK",
            countryCode = "+44",
            nationalPrefix = "0"
        )

        val frCode = CountryCode(
            countryName = "France",
            countryShortName = "FR",
            countryCode = "+33",
            nationalPrefix = "0"
        )

        val usCode = CountryCode(
            countryName = "United States",
            countryShortName = "US",
            countryCode = "+1",
            nationalPrefix = "1"
        )

        viewModel.addCountryCode(countryCode = ukCode)
        viewModel.addCountryCode(countryCode = usCode)
        viewModel.addCountryCode(countryCode = frCode)
    }

    /**
     *  GET All country code
     */
    private fun getAllCountryCode() {
        viewModel.getCountryCode().observe(this, {
            if(it.isEmpty()){
                insertInitialCountryCodes()
            }
        })
    }
}