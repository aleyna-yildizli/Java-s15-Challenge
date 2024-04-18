package com.workintech.people;

import com.workintech.Book;
import com.workintech.enums.PersonRoles;

import java.util.Objects;
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
    public boolean login(String username, String password) {
    return false;
    }

    @Override
    public String toString() {
       return getUserName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Author author = (Author) o;
        return Objects.equals(books, author.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), books);
    }
}
