package com.shivamkumarjha.bookstore.ui.displaybook

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.common.AppPreference
import com.shivamkumarjha.bookstore.model.Book
import com.shivamkumarjha.bookstore.model.WishItem
import com.shivamkumarjha.bookstore.repository.UserRepository
import com.shivamkumarjha.bookstore.ui.displaybook.adapter.ReviewAdapter
import com.shivamkumarjha.bookstore.ui.displaybook.adapter.SliderAdapter
import java.io.File

class DisplayBookFragment(private val book: Book) : Fragment() {

    private lateinit var toolbar: Toolbar
    private lateinit var bookTitle: TextView
    private lateinit var bookAuthor: TextView
    private lateinit var bookDescription: TextView
    private lateinit var bookPrice: TextView
    private lateinit var bookMRP: TextView
    private lateinit var bookDiscount: TextView
    private lateinit var bookRating: TextView
    private lateinit var bookRatingCount: TextView
    private lateinit var bookCategory: TextView
    private lateinit var bookDetail: TextView
    private lateinit var wishStatus: ToggleButton
    private lateinit var cartButton: Button
    private lateinit var viewPager: ViewPager
    private lateinit var sliderAdapter: SliderAdapter
    private lateinit var reviewAdapter: ReviewAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var displayBookViewModel: DisplayBookViewModel
    private lateinit var userRepository: UserRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_display_book, container, false)
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

    private fun initializer() {
        toolbar = requireView().findViewById(R.id.display_book_toolbar_id)
        bookTitle = requireView().findViewById(R.id.display_book_title_text_view_id)
        bookAuthor = requireView().findViewById(R.id.display_book_author_text_view_id)
        bookDescription = requireView().findViewById(R.id.display_book_description_text_view_id)
        bookPrice = requireView().findViewById(R.id.display_book_text_view_price)
        bookMRP = requireView().findViewById(R.id.display_book_text_view_mrp)
        bookDiscount = requireView().findViewById(R.id.display_book_text_view_discount)
        bookRating = requireView().findViewById(R.id.display_book_text_view_rating)
        bookRatingCount = requireView().findViewById(R.id.display_book_text_view_rating_count)
        bookCategory = requireView().findViewById(R.id.display_book_category_text_view_id)
        bookDetail = requireView().findViewById(R.id.display_book_detail_text_view_id)
        wishStatus = requireView().findViewById(R.id.display_book_toggle_wish_id)
        cartButton = requireView().findViewById(R.id.display_book_cart_button)
        viewPager = requireView().findViewById(R.id.display_book_view_pager)
        recyclerView = requireView().findViewById(R.id.display_book_review_recycler_view_id)
        val userFile = File(requireActivity().filesDir, resources.getString(R.string.file_users))
        userRepository = UserRepository(userFile, AppPreference(requireContext()).getUserEmail()!!)
        displayBookViewModel =
            ViewModelProvider(this, DisplayBookViewModelFactory(book, userRepository))
                .get(DisplayBookViewModel::class.java)
    }

    private fun setUpToolBar() {
        toolbar.title = book.title
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setUpViews() {
        //cart
        if (displayBookViewModel.isBookInCartItems())
            toggleCart()
        cartButton.setOnClickListener {
            displayBookViewModel.addToCart(AppPreference(requireContext()).newCartId())
            toggleCart()
        }
        // Strike MRP
        bookMRP.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        // wish toggle
        wishToggleAnimation()
        wishStatus.isChecked = displayBookViewModel.isBookInWishItems()
        wishStatus.setOnClickListener { onWishClick(wishStatus.isChecked) }
        // review recycler view
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        reviewAdapter = ReviewAdapter()
        recyclerView.adapter = reviewAdapter
    }

    private fun toggleCart() {
        cartButton.isEnabled = false
        cartButton.text = resources.getString(R.string.added_cart)
    }

    private fun setUpViewModel() {
        displayBookViewModel.bookTitle.observe(viewLifecycleOwner, Observer {
            bookTitle.text = it
        })
        displayBookViewModel.bookAuthor.observe(viewLifecycleOwner, Observer {
            bookAuthor.text = it
        })
        displayBookViewModel.bookDescription.observe(viewLifecycleOwner, Observer {
            bookDescription.text = it
        })
        displayBookViewModel.bookPrice.observe(viewLifecycleOwner, Observer {
            bookPrice.text = it
        })
        displayBookViewModel.bookMRP.observe(viewLifecycleOwner, Observer {
            bookMRP.text = it
        })
        displayBookViewModel.bookDiscount.observe(viewLifecycleOwner, Observer {
            bookDiscount.text = it
        })
        displayBookViewModel.bookCategory.observe(viewLifecycleOwner, Observer {
            bookCategory.text = it
        })
        displayBookViewModel.bookDetail.observe(viewLifecycleOwner, Observer {
            bookDetail.text = it
        })
        displayBookViewModel.bookRating.observe(viewLifecycleOwner, Observer {
            bookRating.text = it
        })
        displayBookViewModel.bookRatingCount.observe(viewLifecycleOwner, Observer {
            bookRatingCount.text = it
        })
        displayBookViewModel.getReviews().observe(viewLifecycleOwner, Observer {
            reviewAdapter.setReviews(it)
        })
        displayBookViewModel.getImages().observe(viewLifecycleOwner, Observer {
            sliderAdapter = SliderAdapter(it)
            viewPager.adapter = sliderAdapter
        })
    }

    private fun onWishClick(isChecked: Boolean) {
        val toastMessage: String
        if (isChecked) {
            toastMessage = "Добавлен ${book.title} к списку желаний."
            displayBookViewModel.addWishItem(
                WishItem(
                    AppPreference(requireContext()).newWishId(),
                    book
                )
            )
        } else {
            toastMessage = "Удаленный ${book.title} из списка желаний."
            displayBookViewModel.removeWishItem(book)
        }
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
}