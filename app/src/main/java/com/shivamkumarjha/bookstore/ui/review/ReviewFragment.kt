package com.shivamkumarjha.bookstore.ui.review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.common.AppPreference
import com.shivamkumarjha.bookstore.common.hideKeyboard
import com.shivamkumarjha.bookstore.model.Book
import com.shivamkumarjha.bookstore.model.Review
import com.shivamkumarjha.bookstore.repository.BookRepository
import java.io.File

class ReviewFragment(private val book: Book) : Fragment() {

    private lateinit var toolbar: Toolbar
    private lateinit var ratingBar: AppCompatRatingBar
    private lateinit var submitButton: AppCompatButton
    private lateinit var reviewEditText: AppCompatEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolBar()
        backPressDispatcher()
        val bookFile = File(requireActivity().filesDir, resources.getString(R.string.file_books))
        val bookRepository = BookRepository(bookFile)
        // initialize views
        ratingBar = requireView().findViewById(R.id.review_rating_bar_id)
        submitButton = requireView().findViewById(R.id.review_submit_button)
        reviewEditText = requireView().findViewById(R.id.review_text_view_id)
        // button
        submitButton.setOnClickListener {
            bookRepository.addBookReview(
                book,
                Review(
                    AppPreference(requireContext()).getUserName()!!,
                    ratingBar.rating,
                    reviewEditText.text.toString()
                )
            )
            exitFragment()
        }
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
        toolbar = requireView().findViewById(R.id.review_toolbar_id)
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener { exitFragment() }
        toolbar.title = resources.getString(R.string.review)
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