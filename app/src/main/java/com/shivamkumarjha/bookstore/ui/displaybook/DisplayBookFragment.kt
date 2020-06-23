package com.shivamkumarjha.bookstore.ui.displaybook

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import android.widget.CompoundButton
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.Book
import com.shivamkumarjha.bookstore.ui.displaybook.adapter.ReviewAdapter
import com.shivamkumarjha.bookstore.ui.displaybook.adapter.SliderAdapter

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
    private lateinit var viewPager: ViewPager
    private lateinit var sliderAdapter: SliderAdapter
    private lateinit var reviewAdapter: ReviewAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var displayBookViewModel: DisplayBookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_display_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializer()
        backPressDispatcher()
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
        viewPager = requireView().findViewById(R.id.display_book_view_pager)
        recyclerView = requireView().findViewById(R.id.display_book_review_recycler_view_id)
        displayBookViewModel = ViewModelProvider(this, DisplayBookViewModelFactory(book))
            .get(DisplayBookViewModel::class.java)
    }

    private fun setUpToolBar() {
        toolbar.title = book.title
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener { exitFragment() }
    }

    private fun setUpViews() {
        // Strike MRP
        bookMRP.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        // wish toggle
        wishToggleAnimation()
        wishStatus.setOnClickListener { onWishClick(wishStatus.isChecked) }
        // review recycler view
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
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
        displayBookViewModel.bookReview.observe(viewLifecycleOwner, Observer {
            reviewAdapter = ReviewAdapter(it)
            recyclerView.adapter = reviewAdapter
        })
        displayBookViewModel.imageLink.observe(viewLifecycleOwner, Observer {
            sliderAdapter = SliderAdapter(it)
            viewPager.adapter = sliderAdapter
        })
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