package com.workintech.people;

import com.workintech.Book;
import com.workintech.Interfaces.UserActionable;
import com.workintech.Library;
import com.workintech.enums.BookStatus;
import com.workintech.enums.PersonRoles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Reader extends Person implements UserActionable {
    private Map<String, List<Book>> userBooks; // Kullanıcıların ödünç aldığı kitaplar
    private static final int BOOK_LIMIT = 5;
    private Library library;


    public Reader( String userName, String password, Map<String, List<Book>> userBooks, Library library) {
        super(userName, password, PersonRoles.READER);
        this.userBooks = userBooks;
        this.library = library;
    }

    @Override
    public void borrowBook(int bookId, String userName) {
        Library library = this.library;
        Book book = library.getBooks().get(bookId);
        if (book == null) {
            System.out.println("Aradığınız kitap ID'si mevcut değil.");
            return;
        }
        if (checkUserLimit(userName)) {
            System.out.println("Maksimum kitap limitine ulaştınız. Daha fazla kitap almak için en az 1 kitap iade ediniz.");
            return;
        }

        if (book.getStatus() == BookStatus.AVAILABLE) {    // -> Kitap ödünç alınabilir durumda ise
            book.setStatus(BookStatus.BORROWED);
            List<Book> borrowedBooks = userBooks.computeIfAbsent(userName, k -> new ArrayList<>());
            borrowedBooks.add(book);
            System.out.println("Kitap başarıyla ödünç alındı.");
        } else {
            System.out.println("Kitap ödünç alınamaz. Kitap is " + book.getStatus());
        }
    }
    @Override
    public void returnBook(int bookId, String userName) {
        List<Book> borrowedBooks = userBooks.get(userName);

        if (borrowedBooks != null) { //// Kitapları bul ->  listeden çıkar
            Book bookToReturn = borrowedBooks.stream()
                    .filter(book -> book.getBookId() == bookId)
                    .findFirst()
                    .orElse(null);

            if (bookToReturn != null) {
                borrowedBooks.remove(bookToReturn);
                bookToReturn.setStatus(BookStatus.AVAILABLE);
                System.out.println("Kitap iade edildi.");
            } else {
                System.out.println("Ödünç aldığınız kitaplar arasında belirtilen kitap bulunamadı.");
            }
        } else {
            System.out.println("Kullanıcının ödünç aldığı kitaplar bulunamadı.");
        }
    }


    public Map<String, List<Book>> getUserBooks() {
        return userBooks;
    }

    public void setUserBooks(Map<String, List<Book>> userBooks) {
        this.userBooks = userBooks;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    @Override
    public boolean checkUserLimit(String userName) {
        List<Book> borrowedBooks = userBooks.get(userName);
        return borrowedBooks != null && borrowedBooks.size() >= BOOK_LIMIT;
    }

    @Override
    public List<Book> getUserBooks(String userName) {
        return userBooks.getOrDefault(userName, new ArrayList<>());
    }

    @Override
    public boolean login(String username, String password) {
        return super.login(username, password);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public String toString() {
        return "Reader{" +
                ", userBooks=" + userBooks +
                '}';
    }
}
