package com.shivamkumarjha.bookstore.ui.address

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.shivamkumarjha.bookstore.R

class AddressFragment : Fragment() {

    private lateinit var toolbar: Toolbar
    private lateinit var addressEditText: EditText
    private lateinit var flatEditText: EditText
    private lateinit var streetEditText: EditText
    private lateinit var cityEditText: EditText
    private lateinit var stateEditText: EditText
    private lateinit var pinCodeEditText: EditText
    private lateinit var submitButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_address, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializer()
        setUpToolBar()
        backPressDispatcher()
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar!!.show()
    }

    private fun initializer() {
        toolbar = requireView().findViewById(R.id.address_toolbar_id)
        addressEditText = requireView().findViewById(R.id.address_mobile_id)
        flatEditText = requireView().findViewById(R.id.address_flat_id)
        streetEditText = requireView().findViewById(R.id.address_street_id)
        cityEditText = requireView().findViewById(R.id.address_city_id)
        stateEditText = requireView().findViewById(R.id.address_state_id)
        pinCodeEditText = requireView().findViewById(R.id.address_pin_code_id)
        submitButton = requireView().findViewById(R.id.address_submit_id)
    }

    private fun setUpToolBar() {
        toolbar.title = resources.getString(R.string.add_address)
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener { exitFragment() }
    }

    private fun backPressDispatcher() {
        val callBackObject = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                exitFragment()
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callBackObject)
    }

    private fun exitFragment() {
        requireActivity().supportFragmentManager.popBackStack()
    }
}