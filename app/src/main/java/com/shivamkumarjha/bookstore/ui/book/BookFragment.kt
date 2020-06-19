package com.shivamkumarjha.bookstore.ui.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.utility.JsonUtility
import java.io.File

class BookFragment : Fragment() {

    private lateinit var bookViewModel: BookViewModel
    private val textView by lazy {
        requireView().findViewById<TextView>(R.id.text_home)
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
        setUpJSON()
        setUpViewModel()
    }

    private fun setUpViewModel() {
        bookViewModel =
            ViewModelProvider(this).get(BookViewModel::class.java)
        bookViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
    }

    private fun setUpJSON() {
        val fileName = "Books.json"
        val file = File(requireActivity().filesDir, fileName)
        val jsonUtility = JsonUtility(file)
        jsonUtility.getBooks()
    }
}