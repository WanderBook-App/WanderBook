package com.example.wanderbook.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.wanderbook.R
import com.example.wanderbook.model.Book

class BooksViewModel : ViewModel() {
    var searchText by mutableStateOf("")
        private set

    private val _books = mutableStateListOf<Book>()
    val books: List<Book> get() = _books

    var selectedBook by mutableStateOf<Book?>(null)
        private set

    fun onSearchTextChange(newText: String) {
        searchText = newText
    }

    fun getBookById(bookId: Int): Book? {
        return books.find { it.id == bookId }
    }

    fun selectBook(book: Book) {
        selectedBook = book
    }

    init {
        loadBooks()
    }

    fun loadBooks() {
        // Пример наполнения книгами, данные можешь заменить на реальные
        Log.d("MyLog", "Loading books...")
        _books.addAll(
            listOf(
                Book(
                    1,
                    "Сияние",
                    "Стивен Кинг",
                    "Хоррор",
                    2,
                    "Отличное",
                    "Роман о семье Торренсов, заснежённом отеле «Оверлук» и мрачных силах, которые поглощают разум его жителей.",
                    R.drawable.book1
                ),
                Book(
                    2,
                    "Отцы и дети",
                    "Тургенев Иван Сергеевич",
                    "Роман",
                    1,
                    "Хорошее",
                    "\"Отцы и дети\" — знаменитый роман Тургенева, ставший чуть ли не самым  значительным произведением в истории о взаимоотношениях поколений.",
                    R.drawable.book2
                ),
                Book(
                    3,
                    "Портрет Дориана Грея",
                    "Оскар Уайльд",
                    "Классика",
                    3,
                    "Хорошее",
                    "Роман о молодом человеке, чья внешность остаётся неизменной, несмотря на его порочные поступки. Загадочный портрет скрывает все его грехи.",
                    R.drawable.book3
                ),
                Book(
                    4,
                    "Цветы для Элджернона",
                    "Дэниел Киз",
                    "Фантастика",
                    4,
                    "Очень хорошее",
                    "История Чарли Гордона, умственно отсталого человека, который становится объектом научного эксперимента по повышению интеллекта.",
                    R.drawable.book4
                )
            )
        )
        Log.d("MyLog", "Books loaded: ${_books.size}")
    }
}
