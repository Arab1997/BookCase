package com.shivamkumarjha.bookstore.ui.booklist

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.common.AppPreference
import com.shivamkumarjha.bookstore.model.Book
import com.shivamkumarjha.bookstore.model.WishItem
import com.shivamkumarjha.bookstore.repository.BookRepository
import com.shivamkumarjha.bookstore.repository.UserRepository
import com.shivamkumarjha.bookstore.ui.DashboardActivity
import com.shivamkumarjha.bookstore.ui.booklist.adapter.BookAdapter
import com.shivamkumarjha.bookstore.ui.booklist.adapter.BookItemClickListener
import java.io.File

class BookListFragment : Fragment(), BookItemClickListener {

    private lateinit var bookListViewModel: BookListViewModel
    private lateinit var bookAdapter: BookAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        searchBooks()
        setUpRecyclerView()
        setUpViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        requireActivity().menuInflater.inflate(R.menu.book_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.cart_menu_id -> (activity as DashboardActivity).callCartFragment()
            R.id.profile_menu -> (activity as DashboardActivity).callProfileFragment()
            R.id.sign_out -> (activity as DashboardActivity).doSignOut()
        }
        return true
    }

    override fun onBookClick(book: Book) {
        (activity as DashboardActivity).callDisplayBookFragment(book)
    }

    override fun onWishClick(book: Book, isChecked: Boolean) {
        val toastMessage: String
        if (isChecked) {
            toastMessage = "Added ${book.title} to wish list."
            bookListViewModel.addWishItem(
                WishItem(
                    AppPreference(requireContext()).newWishId(),
                    book
                )
            )
        } else {
            toastMessage = "Removed ${book.title} from wish list."
            bookListViewModel.removeWishItem(book)
        }
        Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_SHORT).show()
    }

    private fun searchBooks() {
        val searchView: SearchView = requireView().findViewById(R.id.book_search)
        val searchIcon = searchView.findViewById<ImageView>(R.id.search_mag_icon)
        searchIcon.setColorFilter(Color.WHITE)
        val cancelIcon = searchView.findViewById<ImageView>(R.id.search_close_btn)
        cancelIcon.setColorFilter(Color.WHITE)
        val searchTextView = searchView.findViewById<TextView>(R.id.search_src_text)
        searchTextView.setTextColor(Color.WHITE)
        searchTextView.hint = resources.getString(R.string.search_books)
        searchTextView.setHintTextColor(ContextCompat.getColor(requireContext(), R.color.gray_300))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                bookAdapter.filter.filter(newText)
                return false
            }
        })
    }

    private fun setUpViewModel() {
        val booksFile = File(requireActivity().filesDir, resources.getString(R.string.file_books))
        val bookRepository = BookRepository(booksFile)
        val userFile = File(requireActivity().filesDir, resources.getString(R.string.file_users))
        val userRepository =
            UserRepository(userFile, AppPreference(requireContext()).getUserEmail()!!)
        bookListViewModel =
            ViewModelProvider(this, BookListViewModelFactory(bookRepository, userRepository))
                .get(BookListViewModel::class.java)
        bookListViewModel.getBooks().observe(requireActivity(), Observer {
            bookAdapter.setBooks(it)
        })
        bookListViewModel.getWishItems().observe(requireActivity(), Observer {
            bookAdapter.setWishItems(it)
        })
    }

    private fun setUpRecyclerView() {
        recyclerView = requireView().findViewById(R.id.book_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        bookAdapter = BookAdapter(this)
        recyclerView.adapter = bookAdapter
    }
}