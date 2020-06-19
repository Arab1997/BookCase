package com.shivamkumarjha.bookstore.ui.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.Book
import com.shivamkumarjha.bookstore.ui.adapter.BookAdapter
import com.shivamkumarjha.bookstore.ui.adapter.BookItemClickListener
import com.shivamkumarjha.bookstore.ui.adapter.MarginItemDecoration
import com.shivamkumarjha.bookstore.utility.JsonUtility
import java.io.File

class BookFragment : Fragment(), BookItemClickListener {

    private lateinit var bookViewModel: BookViewModel
    private lateinit var bookAdapter: BookAdapter
    private val recyclerView by lazy {
        requireView().findViewById<RecyclerView>(R.id.book_recycler_view)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        setUpRecyclerView()
    }

    override fun onBookClick(book: Book) {
        Toast.makeText(requireContext(), book.title, Toast.LENGTH_LONG).show()
    }

    private fun setUpViewModel() {
        bookViewModel =
            ViewModelProvider(this).get(BookViewModel::class.java)
    }

    private fun setUpRecyclerView() {
        bookAdapter = BookAdapter(getBooks(), this)
        recyclerView.adapter = bookAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(
            MarginItemDecoration(
                resources.getDimension(R.dimen.action_bar_height).toInt()
            )
        )
        recyclerView.setHasFixedSize(true)
    }

    private fun getBooks(): ArrayList<Book> {
        val file = File(requireActivity().filesDir, "Books.json")
        val jsonUtility = JsonUtility(file)
        return jsonUtility.getBooks()
    }
}