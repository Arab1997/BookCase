package com.shivamkumarjha.bookstore.model

class PopulateBooks {

    private var books: ArrayList<Book> = ArrayList()
    private var booksCount = 0

    private fun addBook(book: Book) {
        books.add(book)
        booksCount++
    }

    fun getBooks(): ArrayList<Book> {
        return books
    }

    fun populate() {
        val book1 = Book(
            booksCount,
            "Drifts",
            "Kate Zambreno",
            "A restlessly brilliant novel of creative crisis and transformation",
            "A story of artistic ambition, personal crisis, and the possibilities and " +
                    "failures of literature, Drifts is a dramatic step forward for one of our " +
                    "most daring writers.",
            "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1578676099l/48585697.jpg",
            "Novel",
            "4.01",
            "Time is a slippery thing. Zambreno reckons with the problematic relationship writers have with time.",
            14,
            10,
            false
        )
        addBook(book1)
        val book2 = Book(
            booksCount,
            "Days of Distraction",
            "Alexandra Chang",
            "A wry, tender portrait of a young woman—finally free to decide her own path" +
                    ", but unsure if she knows herself well enough to choose wisely",
            "Equal parts tender and humorous, and told in spare but powerful prose, " +
                    "Days of Distraction is an offbeat coming-of-adulthood tale, a touching family story, " +
                    "and a razor-sharp appraisal of our times.",
            "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1561993078l/52973514._SX318_SY475_.jpg",
            "Novel",
            "3.77",
            "This is really a wonderful book. I would say it is a “quiet” book, but that’s true only on the surface.",
            14,
            10,
            true
        )
        addBook(book2)
        val book3 = Book(
            booksCount,
            "The Apartment",
            "K.L. Slater",
            "They say every cloud has a silver lining. . .",
            "Old secrets refuse to stay buried and someone is determined to keep a terrible past, very much alive.",
            "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1591095350l/51140631._SX318_.jpg",
            "Thriller",
            "3.73",
            "This is another devouring with few bites, finishing at one sit, quick, fast pacing and exciting page-turner!",
            2,
            20,
            false
        )
        addBook(book3)
    }

}