package com.workintech.Interfaces;

import com.workintech.Book;
import com.workintech.enums.BookCategory;
import com.workintech.people.Author;

import java.util.List;

public interface Listable {

    List<Book> listBooksByCategory(BookCategory category); // Kategorilere göre kitaplar listelenebilir

    List<Book> listBooksByAuthor(Author author); // Yazarlara göre kitaplar listelenebilir
    List<Book> listAllBooks(); // Tüm kitaplar listelenebilir
}
