package com.workintech.Interfaces;

import com.workintech.Book;
import com.workintech.enums.BookStatus;

public interface BookManageable {
    void addBook(Book book);
    void deleteBook(int bookId);
    void updateBook( int bookId, int stock, BookStatus status); //kitabın yalnızca stok bilgisi ve statusü değişebilir.

}
