package info.aliahmed.voxmartcountrycode.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.app.Activity
import android.view.inputmethod.InputMethodManager
import info.aliahmed.voxmartcountrycode.databinding.FragmentDialBinding


class DialFragment : Fragment() {

    lateinit var binding: FragmentDialBinding

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
    }

    fun showSoftKeyboard(view: View) {
        val inputMethodManager: InputMethodManager =
            activity!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        view.requestFocus()
        inputMethodManager.showSoftInput(view, 0)
    }

    private fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
    }

}