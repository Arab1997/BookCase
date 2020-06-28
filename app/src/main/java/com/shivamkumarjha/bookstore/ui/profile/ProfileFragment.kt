package com.shivamkumarjha.bookstore.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.common.AppPreference
import com.shivamkumarjha.bookstore.common.UserViewModelFactory
import com.shivamkumarjha.bookstore.model.Address
import com.shivamkumarjha.bookstore.model.LoggedInUserView
import com.shivamkumarjha.bookstore.repository.UserRepository
import com.shivamkumarjha.bookstore.ui.DashboardActivity
import com.shivamkumarjha.bookstore.ui.profile.adapter.AddressAdapter
import com.shivamkumarjha.bookstore.ui.profile.adapter.AddressItemClickListener
import java.io.File

class ProfileFragment : Fragment(), AddressItemClickListener {

    private lateinit var toolbar: Toolbar
    private lateinit var nameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var addAddressButton: Button
    private lateinit var loggedInUserView: LoggedInUserView
    private lateinit var addressAdapter: AddressAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializer()
        setUpToolBar()
        setUpViews()
        setUpViewModel()
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

    override fun onEditClick(address: Address) {
        (activity as DashboardActivity).callAddressFragment(address)
    }

    override fun onDeleteClick(address: Address, position: Int) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(resources.getString(R.string.address_confirm_title))
        builder.setMessage(resources.getString(R.string.address_confirm_message))
        builder.setPositiveButton(R.string.yes) { _, _ ->
            profileViewModel.removeAddress(address)
            val list: ArrayList<Address> = addressAdapter.getAddress()
            list.removeAt(position)
            addressAdapter.setAddress(list)
            addressAdapter.notifyItemRemoved(position)
        }
        builder.setNegativeButton(R.string.no) { _, _ -> }
        builder.show()
    }

    private fun initializer() {
        nameTextView = requireView().findViewById(R.id.profile_name_text_view_id)
        emailTextView = requireView().findViewById(R.id.profile_email_text_view_id)
        addAddressButton = requireView().findViewById(R.id.profile_address_button)
        loggedInUserView = (activity as DashboardActivity).getUser()
    }

    private fun setUpToolBar() {
        toolbar = requireView().findViewById(R.id.profile_toolbar_id)
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener { exitFragment() }
        toolbar.title = resources.getString(R.string.profile)
    }

    private fun setUpViews() {
        recyclerView = requireView().findViewById(R.id.profile_address_recycler_view_id)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        addressAdapter = AddressAdapter(this)
        recyclerView.adapter = addressAdapter

        addAddressButton.setOnClickListener {
            (activity as DashboardActivity).callAddressFragment(null)
        }

        nameTextView.text = AppPreference(requireContext()).getUserName()
        emailTextView.text = AppPreference(requireContext()).getUserEmail()
    }

    private fun setUpViewModel() {
        val userFile = File(requireActivity().filesDir, resources.getString(R.string.file_users))
        val userRepository =
            UserRepository(userFile, AppPreference(requireContext()).getUserEmail()!!)
        profileViewModel =
            ViewModelProvider(this, UserViewModelFactory(userRepository))
                .get(ProfileViewModel::class.java)
        profileViewModel.getAddress().observe(viewLifecycleOwner, Observer {
            addressAdapter.setAddress(it)
        })
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