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
            "A wry, tender portrait of a young woman—finally free to decide her own path",
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
        val book4 = Book(
            booksCount,
            "The Bride",
            "Wendy Clarke",
            "The moment Joanna told me she was engaged, I had this awful feeling that something was wrong.",
            "An addictive, clever and suspenseful thriller that will keep you totally absorbed " +
                    "and page turning from the very beginning to the climax",
            "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1583096810l/51974210._SY475_.jpg",
            "Crime",
            "3.53",
            "This book keeps me pulled in. The first chapter is curious enough to keep you reading.",
            4,
            10,
            true
        )
        addBook(book4)
        val book5 = Book(
            booksCount,
            "Little Whispers",
            "K.L. Slater",
            "You shared a secret with the wrong person.",
            "Janey faces an impossible choice. Stay quiet about what she saw that terrible day. " +
                    "Or speak up, and destroy the family she has worked so hard to protect…",
            "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1582557211l/51664923._SY475_.jpg",
            "Thriller",
            "4.10",
            "I'm always happy to read a new Slater book and this one is filled with secrets, gossip, and lies.",
            4,
            10,
            false
        )
        addBook(book5)
        val book6 = Book(
            booksCount,
            "One Mistake",
            "Rona Halsall",
            "How far would you go to protect your happy home?",
            "This year’s most gripping psychological thriller" +
                    " – perfect for fans of My Lovely Wife, The Girl on the Train, and Something in the Water.",
            "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1587990821l/51060039._SY475_.jpg",
            "Novel",
            "3.92",
            "The book is addictive. It is slow at the beginning, but picks up pace as it nears conclusion. " +
                    "Once it is in the top gear, there is no stopping it! A well crafted psychological thriller indeed.",
            8,
            10,
            false
        )
        addBook(book6)
    }

}