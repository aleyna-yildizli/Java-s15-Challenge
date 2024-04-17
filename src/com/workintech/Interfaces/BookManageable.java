package com.workintech.Interfaces;

import com.workintech.Book;

public interface BookManageable {
    void addBook(Book book); //kitap ekleme
    void deleteBook(Long id); // kitap silme

}
