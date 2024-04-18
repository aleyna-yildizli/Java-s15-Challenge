package com.workintech.Interfaces;

import com.workintech.people.Author;

public interface Searchable {
    boolean searchBookById(int bookId);
    boolean searchBooksByName(String bookName);
    boolean  searchBooksByAuthor(Author author);
}
