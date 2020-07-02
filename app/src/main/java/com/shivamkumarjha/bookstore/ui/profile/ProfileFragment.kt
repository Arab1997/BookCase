package com.shivamkumarjha.bookstore.ui.profile

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
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
    private lateinit var userImage: ImageView
    private lateinit var loggedInUserView: LoggedInUserView
    private lateinit var addressAdapter: AddressAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var profileViewModel: ProfileViewModel
    private val pickImage = 1
    private var imagePath: String? = null

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
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar!!.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_CANCELED) {
            val selectedImage: Uri? = data!!.data
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            if (selectedImage != null) {
                val cursor: Cursor? = requireActivity().contentResolver.query(
                    selectedImage,
                    filePathColumn,
                    null,
                    null,
                    null
                )
                if (cursor != null) {
                    cursor.moveToFirst()
                    val columnIndex: Int = cursor.getColumnIndex(filePathColumn[0])
                    imagePath = cursor.getString(columnIndex)
                    userImage.setImageBitmap(BitmapFactory.decodeFile(imagePath))
                    profileViewModel.updateProfilePicture(imagePath)
                    cursor.close()
                }
            }
        }
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
        userImage = requireView().findViewById(R.id.user_image)
        loggedInUserView = (activity as DashboardActivity).getUser()
    }

    private fun setUpToolBar() {
        toolbar = requireView().findViewById(R.id.profile_toolbar_id)
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        toolbar.title = resources.getString(R.string.profile)
    }

    private fun setUpViews() {
        // recyclerView
        recyclerView = requireView().findViewById(R.id.profile_address_recycler_view_id)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        addressAdapter = AddressAdapter(this)
        recyclerView.adapter = addressAdapter
        // view model
        val userFile = File(requireActivity().filesDir, resources.getString(R.string.file_users))
        val userRepository =
            UserRepository(userFile, AppPreference(requireContext()).getUserEmail()!!)
        profileViewModel =
            ViewModelProvider(this, UserViewModelFactory(userRepository))
                .get(ProfileViewModel::class.java)
        // fields
        addAddressButton.setOnClickListener {
            (activity as DashboardActivity).callAddressFragment(null)
        }
        nameTextView.text = AppPreference(requireContext()).getUserName()
        emailTextView.text = AppPreference(requireContext()).getUserEmail()
        // profile picture
        userImage.setOnClickListener {
            val gallery = Intent()
            gallery.type = "image/*"
            gallery.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(
                    gallery,
                    resources.getString(R.string.select_image)
                ), pickImage
            )
        }
        imagePath = profileViewModel.getProfilePicture()
        if (imagePath != null)
            userImage.setImageBitmap(BitmapFactory.decodeFile(imagePath))
        else
            userImage.setBackgroundResource(R.drawable.ic_profile_image)
    }

    private fun setUpViewModel() {
        profileViewModel.getAddress().observe(viewLifecycleOwner, Observer {
            addressAdapter.setAddress(it)
        })
    }
}