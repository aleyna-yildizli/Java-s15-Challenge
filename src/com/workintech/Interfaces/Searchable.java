package com.workintech.Interfaces;

import com.workintech.people.Author;

public interface Searchable {
    boolean searchBookById(long bookId); //id book search
   boolean searchBooksByName(String bookName);//name book search
   boolean searchBooksByAuthor(Author author); //author book search
}
