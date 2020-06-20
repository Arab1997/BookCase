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
            4.01f,
            "Time is a slippery thing. Zambreno reckons with the problematic relationship writers have with time.",
            14f,
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
            3.77f,
            "This is really a wonderful book. I would say it is a “quiet” book, but that’s true only on the surface.",
            14f,
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
            3.73f,
            "This is another devouring with few bites, finishing at one sit, quick, fast pacing and exciting page-turner!",
            2f,
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
            3.53f,
            "This book keeps me pulled in. The first chapter is curious enough to keep you reading.",
            4f,
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
            4.10f,
            "I'm always happy to read a new Slater book and this one is filled with secrets, gossip, and lies.",
            4f,
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
            3.92f,
            "The book is addictive. It is slow at the beginning, but picks up pace as it nears conclusion. " +
                    "Once it is in the top gear, there is no stopping it! A well crafted psychological thriller indeed.",
            8f,
            10,
            false
        )
        addBook(book6)
        val book7 = Book(
            booksCount,
            "The House Guest",
            "Mark Edwards",
            "A perfect summer. A perfect stranger. A perfect nightmare.",
            "The House Guest is the chilling new psychological thriller from the three million " +
                    "copy bestselling author of Here to Stay and Follow You Home.",
            "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1584383149l/49073380._SY475_.jpg",
            "Thriller",
            4.00f,
            "A long-time fan of Mark Edwards and his writing, I turned to this book to see if " +
                    "it would bring the same psychological thrill. Edwards succeeds in delivering " +
                    "and spins a story with layers that the reader will discover as the plot progresses.",
            10f,
            10,
            false
        )
        addBook(book7)
        val book8 = Book(
            booksCount,
            "The Heatwave",
            "Katerina Diamond",
            "The heatwave is back. And so is the killer.",
            "One summer. One stranger. One killer…\nTwo bad things happened that summer:\n" +
                    "A stranger arrived. And the first girl disappeared.",
            "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589285385l/49082711.jpg",
            "Crime",
            4.12f,
            "I have to admit Katerina Diamond is one of my favourite authors, " +
                    "so I was jumping for joy to get the chance to review this book.",
            5f,
            10,
            false
        )
        addBook(book8)
        val book9 = Book(
            booksCount,
            "Hurry Home",
            "Roz Nay",
            "Blood is thicker than water... And it could cost you everything.",
            "Hurry Home is a tantalizing reflection of the chain-and-shackles relationship " +
                    "between sisters that asks: what lines wouldn't you cross for your own?",
            "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1571732733l/48552797._SY475_.jpg",
            "Thriller",
            4.03f,
            "This is my first completed book of 2020 and Roz Nay nailed it.\n" +
                    "Before this book I used to wish I had a sister. Not anymore.",
            3f,
            10,
            false
        )
        addBook(book9)
        val book10 = Book(
            booksCount,
            "Find Her Alive",
            "Lisa Regan",
            "Guess who's back, back again, Josie's back, tell a friend.",
            "Detective Josie Quinn hasn’t heard from her sister since Trinity stormed out of the house " +
                    "in the heat of an argument three weeks ago. So, when human remains are found at " +
                    "the remote hunting cabin where Trinity was last seen, Josie can only assume the worst.",
            "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1579807468l/50636220._SY475_.jpg",
            "Novel",
            4.63f,
            "This one is just as good as the previous seven titles in the series. " +
                    "I devoured the first three back-to-back and now I’ve eagerly read each new installment.",
            12f,
            10,
            true
        )
        addBook(book10)
    }

}