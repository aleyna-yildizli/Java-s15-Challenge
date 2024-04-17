package com.workintech;

import com.workintech.Interfaces.BookManageable;
import com.workintech.Interfaces.Listable;
import com.workintech.Interfaces.Searchable;
import com.workintech.enums.BookCategory;
import com.workintech.enums.BookStatus;
import com.workintech.people.Author;
import com.workintech.people.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Library implements BookManageable, Listable, Searchable {

    private Map<Long, Reader> readers;
    private Map<Long, Book> books;

    public Library() {
        this.readers = new TreeMap<>();
        this.books = new TreeMap<>();
    }

    // Getter ve Setter'lar
    public Map<Long, Reader> getReaders() {
        return readers;
    }

    public void setReaders(Map<Long, Reader> readers) {
        this.readers = readers;
    }

    public Map<Long, Book> getBooks() {
        return books;
    }

    public void setBooks(Map<Long, Book> books) {
        this.books = books;
    }



    // BookManageable interface metodları
    @Override
    public void addBook(Book book) {
        books.put(book.getBookId(), book);
    }

    @Override
    public void deleteBook(Long id) {
        books.remove((Long) id);
    }

    @Override
    public void updateBook( Long bookId, int stock, BookStatus status) {
        Book book = getBooks().get(bookId);
        if (book != null) {
            book.setStatus(status);
            book.setStock(stock);
        }
    }

    // Listable interface metodları
    @Override
    public List<Book> listBooksByCategory(BookCategory category) {
        List<Book> booksByCategory = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getCategory() == category) {
                booksByCategory.add(book);
            }
        }
        return booksByCategory;
    }

    @Override
    public List<Book> listBooksByAuthor(Author author) {
        List<Book> booksByAuthor = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getAuthor().equals(author)) {
                booksByAuthor.add(book);
            }
        }
        return booksByAuthor;
    }

    @Override
    public List<Book> listAllBooks() {
        return new ArrayList<>(books.values());
    }

    @Override
    public boolean searchBookById(long bookId) {
        if (books.containsKey(bookId)) {
            Book book = books.get(bookId);
            System.out.println("Aradığınız ID'ye göre kitabın adı: " + book.getBookId() + " = " + book.getBookName() );
            return true;
        } else {
            System.out.println("Aradığınız kitap id'si mevcut değil");
            return false;
        }
    }

    @Override
    public boolean searchBooksByName(String bookName) {
        for (Book book : books.values()) {
            if (book.getBookName().equalsIgnoreCase(bookName)) {
                System.out.println("Aradığınız kitap adı: " + book.getBookName());
                return true;
            }
        }
        System.out.println("Aradığınız kitap adı mevcut değil");
        return false;
    }

    @Override
    public boolean searchBooksByAuthor(Author author) {
        boolean authorFound = false;
        System.out.println("Yazar: " + author.getUserName() + " için bulunan kitaplar:");

        for (Book book : books.values()) {
            // `book` nesnesinin `getAuthor()` metodunu kullanarak `Author` nesnesini karşılaştırma
            if (book.getAuthor().equals(author)) {
                System.out.println("- " + book.getBookName());
                authorFound = true;
            }
        }

        if (!authorFound) {
            System.out.println("Aradığınız yazar mevcut değil.");
            return false;
        }

        return true;
    }
}