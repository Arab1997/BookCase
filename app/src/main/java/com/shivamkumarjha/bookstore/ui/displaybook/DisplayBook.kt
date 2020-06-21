package com.shivamkumarjha.bookstore.ui.displaybook

import android.annotation.SuppressLint
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.Book
import com.squareup.picasso.Picasso

class DisplayBook(private val book: Book) : Fragment() {

    private lateinit var toolbar: Toolbar
    private lateinit var bookImage: ImageView
    private lateinit var bookTitle: TextView
    private lateinit var bookAuthor: TextView
    private lateinit var bookDescription: TextView
    private lateinit var bookPrice: TextView
    private lateinit var bookMRP: TextView
    private lateinit var bookDiscount: TextView
    private lateinit var bookRating: TextView
    private lateinit var bookCategory: TextView
    private lateinit var bookDetail: TextView
    private lateinit var wishStatus: ToggleButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_display_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolBar()
        backPressDispatcher()
        setUpViews()
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
        toolbar = requireView().findViewById(R.id.display_book_toolbar_id)
        toolbar.title = book.title
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener { exitFragment() }
    }

    @SuppressLint("SetTextI18n")
    private fun setUpViews() {
        // init view items
        bookImage = requireView().findViewById(R.id.display_book_image)
        bookTitle = requireView().findViewById(R.id.display_book_title_text_view_id)
        bookAuthor = requireView().findViewById(R.id.display_book_author_text_view_id)
        bookDescription = requireView().findViewById(R.id.display_book_description_text_view_id)
        bookPrice = requireView().findViewById(R.id.display_book_text_view_price)
        bookMRP = requireView().findViewById(R.id.display_book_text_view_mrp)
        bookDiscount = requireView().findViewById(R.id.display_book_text_view_discount)
        bookRating = requireView().findViewById(R.id.display_book_text_view_rating)
        bookCategory = requireView().findViewById(R.id.display_book_category_text_view_id)
        bookDetail = requireView().findViewById(R.id.display_book_detail_text_view_id)
        wishStatus = requireView().findViewById(R.id.display_book_toggle_wish_id)

        // set value
        Picasso.get().load(book.imageLink)
            .resize(500, 0).centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(bookImage)
        bookTitle.text = book.title
        bookAuthor.text = "By ${book.author}"
        bookDescription.text = book.description
        bookPrice.text = "Rs " + book.price * 76.25f // Price USD to INR
        bookMRP.text = "Rs " + book.maximumRetailPrice * 76.25f // Price USD to INR
        bookMRP.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        bookDiscount.text = book.discount.toInt().toString() + "% off"
        bookRating.text = "%.2f".format(book.review.map { it.rating }.average()) // Average rating
        bookCategory.text = book.category
        bookDetail.text = book.detail

        // wish toggle
        wishStatus.isChecked = book.inWishList
        wishToggleAnimation()
        wishStatus.setOnClickListener { onWishClick(wishStatus.isChecked) }
    }

    private fun onWishClick(isChecked: Boolean) {
        val toastMessage = if (isChecked)
            "Added ${book.title} to wish list."
        else
            "Removed ${book.title} from wish list."
        Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_SHORT).show()
    }

    private fun wishToggleAnimation() {
        val scaleAnimation = ScaleAnimation(
            0.7f,
            1.0f,
            0.7f,
            1.0f,
            Animation.RELATIVE_TO_SELF,
            0.7f,
            Animation.RELATIVE_TO_SELF,
            0.7f
        )
        scaleAnimation.duration = 500
        scaleAnimation.interpolator = BounceInterpolator()
        wishStatus.setOnCheckedChangeListener(object : View.OnClickListener,
            CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(compoundButton: CompoundButton?, status: Boolean) {
                compoundButton?.startAnimation(scaleAnimation)
            }

            override fun onClick(p0: View?) {}
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