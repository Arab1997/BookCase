package com.shivamkumarjha.bookstore.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.Book
import java.util.*
import kotlin.collections.ArrayList

class BookAdapter(
    private var books: ArrayList<Book>,
    private val clickListener: BookItemClickListener
) : RecyclerView.Adapter<BookViewHolder>(), Filterable {

    private val backupList = books

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.content_book_item, parent, false)
        return BookViewHolder(itemView, clickListener)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.initialize(books[position])
    }

    override fun getFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                var filteredList: ArrayList<Book>? = ArrayList()
                if (charSequence.toString().isEmpty()) {
                    filteredList = backupList
                } else {
                    for (book in books) {
                        if (book.title.toLowerCase(Locale.ROOT)
                                .contains(charSequence.toString().toLowerCase(Locale.ROOT)) ||
                            book.author.toLowerCase(Locale.ROOT)
                                .contains(charSequence.toString().toLowerCase(Locale.ROOT))
                        ) {
                            filteredList?.add(book)
                        }
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(
                charSequence: CharSequence?,
                filterResults: FilterResults?
            ) {
                books = filterResults?.values as ArrayList<Book>
                notifyDataSetChanged()
            }
        }
    }
}