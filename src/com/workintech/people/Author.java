package com.workintech.people;

import com.workintech.Book;
import com.workintech.enums.PersonRoles;

import java.util.Set;

public class Author extends Person {
    private final Set<Book> books;

    public Author(String userName, Set<Book> books) {
        super(userName, PersonRoles.AUTHOR);
        this.books = books;
    }

    public Set<Book> getBooks() { //yazarın kitapları
        return books;
    }

    @Override
    public String toString() {
       return getUserName();
    }
}
