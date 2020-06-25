package com.shivamkumarjha.bookstore.repository

import com.shivamkumarjha.bookstore.model.Book
import com.shivamkumarjha.bookstore.model.Review

class BooksGenerator {

    private var books: ArrayList<Book> = ArrayList()
    private var booksCount = 0

    private fun addBook(book: Book) {
        books.add(book)
        booksCount++
    }

    fun getBooks(): ArrayList<Book> {
        return books
    }

    private fun getDiscountPercentage(price: Float, maximumRetailPrice: Float): Float {
        return (100 - (price / maximumRetailPrice) * 100)
    }

    private fun getRandomNumber(start: Int, end: Int): Int {
        require(start <= end) { "Illegal Argument" }
        return (Math.random() * (end - start + 1)).toInt() + start
    }

    private fun getCommonReviews(): ArrayList<Review> {
        val commonReviews: ArrayList<Review> = ArrayList()
        commonReviews.add(
            Review(
                "Shivam Kumar Jha",
                4f,
                "Thanks for sharing."
            )
        )
        commonReviews.add(
            Review(
                "Nikhil Jain",
                5f,
                "Enjoyed it. Thanks a lot"
            )
        )
        commonReviews.add(
            Review(
                "Gunjan Sharma",
                3f,
                "Fine book."
            )
        )
        commonReviews.add(
            Review(
                "Bruce Wayne",
                4f,
                "Somehow I ended up liking this book."
            )
        )
        commonReviews.add(
            Review(
                "Thor Odinson",
                5f,
                "My new favourite book."
            )
        )
        commonReviews.add(
            Review(
                "Lucifer Morningstar",
                2f,
                "I did not enjoy it..."
            )
        )
        val loopSize = getRandomNumber(20, 50)
        for (index in 1 until loopSize) {
            val randomRating = getRandomNumber(3, 5)
            val randomReview: String
            randomReview = when (randomRating) {
                3 -> "Fine book"
                4 -> "Good book"
                else -> "Excellent book"
            }
            val review =
                Review(
                    "Random user $index",
                    randomRating.toFloat(),
                    randomReview
                )
            commonReviews.add(review)
        }
        return commonReviews
    }

    fun populate() {
        val reviewList1: ArrayList<Review> = ArrayList()
        reviewList1.add(
            Review(
                "Lauren",
                4.5f,
                "This is by no stretch of even the most liberal definition of the term a novel " +
                        "- and call me cynical, but the author and publishing company attempting to foist " +
                        "it off as such belies their knowledge that if they marketed it as what it IS, few would want to read it."
            )
        )
        reviewList1.add(
            Review(
                "Doug",
                2.5f,
                "Time is a slippery thing. Zambreno reckons with the problematic relationship writers have with time."
            )
        )
        reviewList1.addAll(getCommonReviews())
        val book1 = Book(
            booksCount,
            "Drifts",
            "Kate Zambreno",
            "A restlessly brilliant novel of creative crisis and transformation",
            "A story of artistic ambition, personal crisis, and the possibilities and " +
                    "failures of literature, Drifts is a dramatic step forward for one of our " +
                    "most daring writers.",
            arrayListOf(
                "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1578676099l/48585697.jpg",
                "https://downtown-brooklyn.imgix.net/imgr/DRIFTS-cover-1059x1600.jpg",
                "https://pbs.twimg.com/media/EZ1V_D_WAAE6OFn?format=jpg",
                "https://pbs.twimg.com/media/EYYiqBTXsAE9oMl?format=jpg"
            ),
            "Novel",
            reviewList1,
            10f,
            14f,
            getDiscountPercentage(10f, 14f)
        )
        addBook(book1)

        val reviewList2: ArrayList<Review> = ArrayList()
        reviewList2.add(
            Review(
                "Pam",
                3.77f,
                "This is really a wonderful book. I would say it is a “quiet” book, but that’s true only on the surface."
            )
        )
        reviewList2.addAll(getCommonReviews())
        val book2 = Book(
            booksCount,
            "Days of Distraction",
            "Alexandra Chang",
            "A wry, tender portrait of a young woman—finally free to decide her own path",
            "Equal parts tender and humorous, and told in spare but powerful prose, " +
                    "Days of Distraction is an offbeat coming-of-adulthood tale, a touching family story, " +
                    "and a razor-sharp appraisal of our times.",
            arrayListOf(
                "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1561993078l/52973514._SX318_SY475_.jpg",
                "https://pbs.twimg.com/media/EAVPiqOXYAA7Loa?format=jpg",
                "https://pbs.twimg.com/media/EZB94zzXgAEydQH?format=jpg"
            ),
            "Novel",
            reviewList2,
            12f,
            14f,
            getDiscountPercentage(12f, 14f)
        )
        addBook(book2)

        val reviewList3: ArrayList<Review> = ArrayList()
        reviewList3.add(
            Review(
                "Jim",
                3.73f,
                "This is another devouring with few bites, finishing at one sit, quick, fast pacing and exciting page-turner!"
            )
        )
        reviewList3.addAll(getCommonReviews())
        val book3 = Book(
            booksCount,
            "The Apartment",
            "K.L. Slater",
            "They say every cloud has a silver lining. . .",
            "Old secrets refuse to stay buried and someone is determined to keep a terrible past, very much alive.",
            arrayListOf(
                "https://pbs.twimg.com/media/EWj9MAEXkAEBTCR?format=jpg",
                "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1591095350l/51140631._SX318_.jpg"
            ),
            "Thriller",
            reviewList3,
            2f,
            4f,
            getDiscountPercentage(2f, 4f)
        )
        addBook(book3)

        val reviewList4: ArrayList<Review> = ArrayList()
        reviewList4.add(
            Review(
                "Dwight Schrute",
                3.53f,
                "This book keeps me pulled in. The first chapter is curious enough to keep you reading."
            )
        )
        reviewList4.addAll(getCommonReviews())
        val book4 = Book(
            booksCount,
            "The Bride",
            "Wendy Clarke",
            "The moment Joanna told me she was engaged, I had this awful feeling that something was wrong.",
            "An addictive, clever and suspenseful thriller that will keep you totally absorbed " +
                    "and page turning from the very beginning to the climax",
            arrayListOf(
                "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1583096810l/51974210._SY475_.jpg",
                "https://i2.wp.com/onceuponatimebookblog.com/wp-content/uploads/2020/05/F8D0B8A6-1980-4EB7-882B-786CF418029E.png"
            ),
            "Crime",
            reviewList4,
            3f,
            4f,
            getDiscountPercentage(3f, 4f)
        )
        addBook(book4)

        val reviewList5: ArrayList<Review> = ArrayList()
        reviewList5.add(
            Review(
                "Michael Scott",
                4.10f,
                "I'm always happy to read a new Slater book and this one is filled with secrets, gossip, and lies."
            )
        )
        reviewList5.addAll(getCommonReviews())
        val book5 = Book(
            booksCount,
            "Little Whispers",
            "K.L. Slater",
            "You shared a secret with the wrong person.",
            "Janey faces an impossible choice. Stay quiet about what she saw that terrible day. " +
                    "Or speak up, and destroy the family she has worked so hard to protect…",
            arrayListOf(
                "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1582557211l/51664923._SY475_.jpg",
                "https://orlandobooksblog.files.wordpress.com/2020/06/1f438e02-633d-428e-b9ed-018218b85016.jpeg"
            ),
            "Thriller",
            reviewList5,
            4f,
            6f,
            getDiscountPercentage(4f, 6f)
        )
        addBook(book5)

        val reviewList6: ArrayList<Review> = ArrayList()
        reviewList6.add(
            Review(
                "Erin",
                3.92f,
                "The book is addictive. It is slow at the beginning, but picks up pace as it nears conclusion. " +
                        "Once it is in the top gear, there is no stopping it! A well crafted psychological thriller indeed."
            )
        )
        reviewList6.addAll(getCommonReviews())
        val book6 = Book(
            booksCount,
            "One Mistake",
            "Rona Halsall",
            "How far would you go to protect your happy home?",
            "This year’s most gripping psychological thriller" +
                    " – perfect for fans of My Lovely Wife, The Girl on the Train, and Something in the Water.",
            arrayListOf(
                "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1587990821l/51060039._SY475_.jpg",
                "https://i1.wp.com/onceuponatimebookblog.com/wp-content/uploads/2020/05/40F25859-CCC7-48F3-B078-D71B97AA7338.png"
            ),
            "Novel",
            reviewList6,
            5f,
            8f,
            getDiscountPercentage(5f, 8f)
        )
        addBook(book6)

        val reviewList7: ArrayList<Review> = ArrayList()
        reviewList7.add(
            Review(
                "Marcus",
                4.00f,
                "A long-time fan of Mark Edwards and his writing, I turned to this book to see if " +
                        "it would bring the same psychological thrill. Edwards succeeds in delivering " +
                        "and spins a story with layers that the reader will discover as the plot progresses."
            )
        )
        reviewList7.addAll(getCommonReviews())
        val book7 = Book(
            booksCount,
            "The House Guest",
            "Mark Edwards",
            "A perfect summer. A perfect stranger. A perfect nightmare.",
            "The House Guest is the chilling new psychological thriller from the three million " +
                    "copy bestselling author of Here to Stay and Follow You Home.",
            arrayListOf(
                "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1584383149l/49073380._SY475_.jpg",
                "https://pbs.twimg.com/media/EZkv-ClWoAATJw2?format=jpg"
            ),
            "Thriller",
            reviewList7,
            7f,
            10f,
            getDiscountPercentage(7f, 10f)
        )
        addBook(book7)

        val reviewList8: ArrayList<Review> = ArrayList()
        reviewList8.add(
            Review(
                "Bruno",
                4.12f,
                "I have to admit Katerina Diamond is one of my favourite authors, " +
                        "so I was jumping for joy to get the chance to review this book."
            )
        )
        reviewList8.addAll(getCommonReviews())
        val book8 = Book(
            booksCount,
            "The Heatwave",
            "Katerina Diamond",
            "The heatwave is back. And so is the killer.",
            "One summer. One stranger. One killer…\nTwo bad things happened that summer:\n" +
                    "A stranger arrived. And the first girl disappeared.",
            arrayListOf(
                "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1589285385l/49082711.jpg",
                "https://pbs.twimg.com/media/EZW6huVXsAABkiK?format=jpg",
                "https://pbs.twimg.com/media/ET8oCerXkAEZM9M?format=jpg"
            ),
            "Crime",
            reviewList8,
            8f,
            10f,
            getDiscountPercentage(8f, 10f)
        )
        addBook(book8)

        val reviewList9: ArrayList<Review> = ArrayList()
        reviewList9.add(
            Review(
                "David",
                4.03f,
                "This is my first completed book of 2020 and Roz Nay nailed it.\n" +
                        "Before this book I used to wish I had a sister. Not anymore."
            )
        )
        reviewList9.addAll(getCommonReviews())
        val book9 = Book(
            booksCount,
            "Hurry Home",
            "Roz Nay",
            "Blood is thicker than water... And it could cost you everything.",
            "Hurry Home is a tantalizing reflection of the chain-and-shackles relationship " +
                    "between sisters that asks: what lines wouldn't you cross for your own?",
            arrayListOf(
                "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1571732733l/48552797._SY475_.jpg",
                "https://pbs.twimg.com/media/EakQ7DSWAAgBm6f?format=jpg",
                "https://pbs.twimg.com/media/ETjhPx0WsAEImGz?format=jpg"
            ),
            "Thriller",
            reviewList9,
            2f,
            3f,
            getDiscountPercentage(2f, 3f)
        )
        addBook(book9)

        val reviewList10: ArrayList<Review> = ArrayList()
        reviewList10.add(
            Review(
                "Dean Henderson",
                4.63f,
                "This one is just as good as the previous seven titles in the series. " +
                        "I devoured the first three back-to-back and now I’ve eagerly read each new installment."
            )
        )
        reviewList10.addAll(getCommonReviews())
        val book10 = Book(
            booksCount,
            "Find Her Alive",
            "Lisa Regan",
            "Guess who's back, back again, Josie's back, tell a friend.",
            "Detective Josie Quinn hasn’t heard from her sister since Trinity stormed out of the house " +
                    "in the heat of an argument three weeks ago. So, when human remains are found at " +
                    "the remote hunting cabin where Trinity was last seen, Josie can only assume the worst.",
            arrayListOf(
                "https://pbs.twimg.com/media/EVtP3zDWoAAB9Cv?format=jpg",
                "https://pbs.twimg.com/media/EVo_745XYAALTmC?format=jpg"
            ),
            "Novel",
            reviewList10,
            10f,
            12f,
            getDiscountPercentage(10f, 12f)
        )
        addBook(book10)
    }

}