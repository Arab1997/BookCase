package com.shivamkumarjha.bookstore.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.LoggedInUserView
import com.shivamkumarjha.bookstore.ui.DashboardActivity

class ProfileFragment : Fragment() {

    private lateinit var toolbar: Toolbar
    private lateinit var nameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var loggedInUserView: LoggedInUserView
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

    private fun initializer() {
        nameTextView = requireView().findViewById(R.id.profile_name_text_view_id)
        emailTextView = requireView().findViewById(R.id.profile_email_text_view_id)
        loggedInUserView = (activity as DashboardActivity).getUser()
        profileViewModel = ViewModelProvider(this, ProfileViewModelFactory(loggedInUserView))
            .get(ProfileViewModel::class.java)
    }

    private fun setUpToolBar() {
        toolbar = requireView().findViewById(R.id.profile_toolbar_id)
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener { exitFragment() }
        toolbar.title = resources.getString(R.string.profile)
    }

    private fun setUpViewModel() {
        profileViewModel.getName.observe(viewLifecycleOwner, Observer {
            nameTextView.text = it
        })
        profileViewModel.getEmail.observe(viewLifecycleOwner, Observer {
            emailTextView.text = it
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