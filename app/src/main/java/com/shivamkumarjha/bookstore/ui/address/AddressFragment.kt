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
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.common.AppPreference
import com.shivamkumarjha.bookstore.common.afterTextChanged
import com.shivamkumarjha.bookstore.common.hideKeyboard
import com.shivamkumarjha.bookstore.model.Address
import com.shivamkumarjha.bookstore.repository.UserRepository
import java.io.File

class AddressFragment(private val address: Address?) : Fragment() {

    private lateinit var toolbar: Toolbar
    private lateinit var mobileEditText: EditText
    private lateinit var flatEditText: EditText
    private lateinit var streetEditText: EditText
    private lateinit var cityEditText: EditText
    private lateinit var stateEditText: EditText
    private lateinit var pinCodeEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var addressConstraintLayout: ConstraintLayout
    private lateinit var addressViewModel: AddressViewModel
    private var addressId: Int = 0

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
        setUpViewModel()
        viewListeners()
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
        mobileEditText = requireView().findViewById(R.id.address_mobile_id)
        flatEditText = requireView().findViewById(R.id.address_flat_id)
        streetEditText = requireView().findViewById(R.id.address_street_id)
        cityEditText = requireView().findViewById(R.id.address_city_id)
        stateEditText = requireView().findViewById(R.id.address_state_id)
        pinCodeEditText = requireView().findViewById(R.id.address_pin_code_id)
        submitButton = requireView().findViewById(R.id.address_submit_id)
        addressConstraintLayout = requireView().findViewById(R.id.address_constraint_layout_id)

        // restore data if edit
        if (address != null) {
            addressId = address.addressId!!
            mobileEditText.setText(address.mobile)
            flatEditText.setText(address.flat)
            streetEditText.setText(address.street)
            cityEditText.setText(address.city)
            stateEditText.setText(address.state)
            pinCodeEditText.setText(address.pinCode)
        } else
            addressId = AppPreference(requireContext()).newAddressId()
    }

    private fun setUpToolBar() {
        toolbar.title = resources.getString(R.string.address)
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener { exitFragment() }
    }

    private fun setUpViewModel() {
        val userFile = File(requireActivity().filesDir, resources.getString(R.string.file_users))
        val userRepository = UserRepository(userFile,AppPreference(requireContext()).getUserEmail()!!)
        addressViewModel = ViewModelProvider(this, AddressViewModelFactory(userRepository))
            .get(AddressViewModel::class.java)

        addressViewModel.addressFormState.observe(requireActivity(), Observer {
            val addressFormState = it ?: return@Observer
            submitButton.isEnabled = addressFormState.isDataValid
            if (addressFormState.mobileError != null)
                mobileEditText.error = getString(addressFormState.mobileError)
            if (addressFormState.flatError != null)
                flatEditText.error = getString(addressFormState.flatError)
            if (addressFormState.streetError != null)
                streetEditText.error = getString(addressFormState.streetError)
            if (addressFormState.cityError != null)
                cityEditText.error = getString(addressFormState.cityError)
            if (addressFormState.stateError != null)
                stateEditText.error = getString(addressFormState.stateError)
            if (addressFormState.pinError != null)
                pinCodeEditText.error = getString(addressFormState.pinError)
        })
    }

    private fun viewListeners() {
        mobileEditText.afterTextChanged { onDataChange() }
        flatEditText.afterTextChanged { onDataChange() }
        streetEditText.afterTextChanged { onDataChange() }
        cityEditText.afterTextChanged { onDataChange() }
        stateEditText.afterTextChanged { onDataChange() }
        pinCodeEditText.afterTextChanged { onDataChange() }
        submitButton.setOnClickListener { submitAddress() }
        addressConstraintLayout.setOnClickListener { hideKeyboard() }
    }

    private fun onDataChange() {
        addressViewModel.addressDataChanged(
            Address(
                null,
                mobileEditText.text.toString(),
                flatEditText.text.toString(),
                streetEditText.text.toString(),
                cityEditText.text.toString(),
                stateEditText.text.toString(),
                pinCodeEditText.text.toString()
            )
        )
    }

    private fun submitAddress() {
        val newAddress = Address(
            addressId,
            mobileEditText.text.toString(),
            flatEditText.text.toString(),
            streetEditText.text.toString(),
            cityEditText.text.toString(),
            stateEditText.text.toString(),
            pinCodeEditText.text.toString()
        )
        if (address != null)
            addressViewModel.updateAddress(newAddress)
        else
            addressViewModel.onSubmitClick(newAddress)
        exitFragment()
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
        hideKeyboard()
        requireActivity().supportFragmentManager.popBackStack()
    }
}