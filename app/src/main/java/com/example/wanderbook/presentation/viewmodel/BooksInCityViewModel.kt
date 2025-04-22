package com.example.wanderbook.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.wanderbook.R
import com.example.wanderbook.model.Book
import androidx.compose.runtime.State


import androidx.lifecycle.viewModelScope
import com.example.wanderbook.data.local.BookDao
import com.example.wanderbook.data.local.LocalBook
import kotlinx.coroutines.launch
import androidx.compose.runtime.*


class BooksViewModel(private val bookDao: BookDao) : ViewModel() {

    var searchText by mutableStateOf("")
        private set

    private val _books = mutableStateListOf<LocalBook>()
    val books: List<LocalBook> get() = _books

    private val _myBooks = mutableStateListOf<LocalBook>()
    val myBooks: List<LocalBook> get() = _myBooks

    private val _favBooks = mutableStateListOf<LocalBook>()
    val favBooks: List<LocalBook> get() = _favBooks

    var selectedBook by mutableStateOf<Book?>(null)
        private set

    private val _showFavorites = mutableStateOf(false)
    val showFavorites: State<Boolean> = _showFavorites

    fun toggleFavorites() {
        _showFavorites.value = !_showFavorites.value
    }

    fun onSearchTextChange(newText: String) {
        searchText = newText
    }

    fun getBookById(bookId: String): LocalBook? {
        return books.find { it.id == bookId }
    }

    fun selectBook(book: Book) {
        selectedBook = book
    }

    init {
        loadBooks()
    }



//    fun loadBooks() {
//        // Пример наполнения книгами, данные можешь заменить на реальные
//        Log.d("MyLog", "Loading books...")
//        val lala = listOf(
//            Book(
//                1,
//                "Сияние",
//                "Стивен Кинг",
//                "Хоррор",
//                2,
//                "Отличное",
//                "Роман о семье Торренсов, заснежённом отеле «Оверлук» и мрачных силах, которые поглощают разум его жителей.",
//                R.drawable.book1
//            ),
//            Book(
//                2,
//                "Отцы и дети",
//                "Тургенев Иван Сергеевич",
//                "Роман",
//                1,
//                "Хорошее",
//                "\"Отцы и дети\" — знаменитый роман Тургенева, ставший чуть ли не самым  значительным произведением в истории о взаимоотношениях поколений.",
//                R.drawable.book2
//            ),
//            Book(
//                3,
//                "Портрет Дориана Грея",
//                "Оскар Уайльд",
//                "Классика",
//                3,
//                "Хорошее",
//                "Роман о молодом человеке, чья внешность остаётся неизменной, несмотря на его порочные поступки. Загадочный портрет скрывает все его грехи.",
//                R.drawable.book3
//            ),
//            Book(
//                4,
//                "Цветы для Элджернона",
//                "Дэниел Киз",
//                "Фантастика",
//                4,
//                "Очень хорошее",
//                "История Чарли Гордона, умственно отсталого человека, который становится объектом научного эксперимента по повышению интеллекта.",
//                R.drawable.book4
//            )
//        )
//        _books.addAll(lala)
//        _myBooks.addAll(lala)
//        _favBooks.addAll(lala.reversed())
//        Log.d("MyLog", "Books loaded: ${_books.size}")
//    }
    private fun loadBooks() {
        viewModelScope.launch {
            val all = bookDao.getAllBooks()
            val my = bookDao.getUserBooks()
            val fav = bookDao.getFavorites()

            _books.clear()
            _books.addAll(all)

            _myBooks.clear()
            _myBooks.addAll(my)

            _favBooks.clear()
            _favBooks.addAll(fav)
        }
    }


    private fun fakeBooks(): List<LocalBook> {
        return listOf(
            LocalBook("1", "Сияние", "Стивен Кинг", "Фантастика", "Отличное","Роман о семье Торренсов...", "cover1.png", false, 51.5, 46.0, true, true, System.currentTimeMillis()),
            LocalBook("2", "1984", "Джордж Оруэлл", "Фантастика", "Отличное","Антиутопия о тоталитаризме", "cover2.png", true, 51.7, 45.9, false, true, System.currentTimeMillis()),
            LocalBook("3", "Пикник на обочине", "Братья Стругацкие","Фантастика", "Отличное", "Фантастика про зону", "cover3.png", false, null, null, true, true, System.currentTimeMillis()),
        )
    }
}

