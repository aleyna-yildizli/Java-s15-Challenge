package com.workintech;

import com.workintech.enums.BookCategory;
import com.workintech.enums.BookStatus;
import com.workintech.people.Author;

public class Book{
    private int bookId; //Kitabın id'si
    private String bookName; //Kitabın adı
    private Author author; //Kitabın yazarı
    private int stock; //Kitabın stok durumu
    private BookStatus status; //Kitabın durumu
    private BookCategory category; //Kitabın kategorisi

    public Book(int bookId, String bookName, Author author, int stock, BookStatus status, BookCategory category) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.stock = stock;
        this.status = status;
        this.category = category;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }
}
