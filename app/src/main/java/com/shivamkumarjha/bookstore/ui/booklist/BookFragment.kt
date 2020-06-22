package com.shivamkumarjha.bookstore.ui.booklist

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.Book
import com.shivamkumarjha.bookstore.ui.booklist.adapter.BookAdapter
import com.shivamkumarjha.bookstore.ui.booklist.adapter.BookItemClickListener
import com.shivamkumarjha.bookstore.ui.cart.CartFragment
import com.shivamkumarjha.bookstore.ui.displaybook.DisplayBook
import com.shivamkumarjha.bookstore.utility.JsonUtility
import java.io.File

class BookFragment : Fragment(), BookItemClickListener {

    private lateinit var bookViewModel: BookViewModel
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
        setUpRecyclerView()
        setUpViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        requireActivity().menuInflater.inflate(R.menu.book_menu, menu)

        // Search books
        val searchItem: MenuItem = menu.findItem(R.id.list_search_id)
        val searchView: SearchView = searchItem.actionView as SearchView
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.card_menu_id -> {
                callCartFragment()
                return true
            }
        }
        return true
    }

    override fun onBookClick(book: Book) {
        requireActivity().supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.fragment_holder, DisplayBook(book))
            .addToBackStack(null)
            .commit()
    }

    override fun onWishClick(book: Book, isChecked: Boolean) {
        val toastMessage = if (isChecked)
            "Added ${book.title} to wish list."
        else
            "Removed ${book.title} from wish list."
        Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_SHORT).show()
    }

    private fun setUpViewModel() {
        val booksFile = File(requireActivity().filesDir, "Books.json")
        val jsonUtility = JsonUtility(booksFile)
        bookViewModel = ViewModelProvider(this, BookViewModelFactory(jsonUtility))
            .get(BookViewModel::class.java)
        bookViewModel.getBooks().observe(requireActivity(), Observer { books ->
            bookAdapter = BookAdapter(books, this)
            recyclerView.adapter = bookAdapter
        })
    }

    private fun setUpRecyclerView() {
        recyclerView = requireView().findViewById(R.id.book_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
    }

    private fun callCartFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.fragment_holder, CartFragment())
            .addToBackStack(null)
            .commit()
    }
}