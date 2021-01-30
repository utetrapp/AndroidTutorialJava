package de.h_da.fbi.demoarchitecturecomponents.ui.book

import androidx.lifecycle.ViewModel
import de.h_da.fbi.demoarchitecturecomponents.data.Book

class BookViewModel : ViewModel() {
    var book: Book = Book("id", "content", "details")
}