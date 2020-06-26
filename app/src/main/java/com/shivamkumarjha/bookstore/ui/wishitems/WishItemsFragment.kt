package com.shivamkumarjha.bookstore.ui.wishitems

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
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
import com.shivamkumarjha.bookstore.repository.UserRepository
import com.shivamkumarjha.bookstore.ui.wishitems.adapter.WishItemsAdapter
import java.io.File

class WishItemsFragment : Fragment() {

    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView
    private lateinit var wishItemsAdapter: WishItemsAdapter
    private lateinit var wishItemsViewModel: WishItemsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wish_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolBar()
        backPressDispatcher()
        setupViews()
        setUpViewModel()
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar!!.show()
    }

    private fun setUpToolBar() {
        toolbar = requireView().findViewById(R.id.wish_toolbar_id)
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener { exitFragment() }
        toolbar.title = resources.getString(R.string.wish)
    }

    private fun setupViews() {
        recyclerView = requireView().findViewById(R.id.wish_list_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        wishItemsAdapter = WishItemsAdapter()
        recyclerView.adapter = wishItemsAdapter
    }

    private fun setUpViewModel() {
        val userFile = File(requireActivity().filesDir, resources.getString(R.string.file_users))
        val userRepository =
            UserRepository(userFile, AppPreference(requireContext()).getUserEmail()!!)
        wishItemsViewModel =
            ViewModelProvider(this, UserViewModelFactory(userRepository))
                .get(WishItemsViewModel::class.java)
        wishItemsViewModel.getAddress().observe(viewLifecycleOwner, Observer {
            wishItemsAdapter.setWishItems(it)
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