package com.workintech.people;

import com.workintech.Book;
import com.workintech.Interfaces.UserActionable;
import com.workintech.Library;
import com.workintech.enums.PersonRoles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Reader extends Person implements UserActionable {
    private final Long id;
    private String password;
    private Map<String, List<Book>> userBooks; // Kullanıcıların ödünç aldığı kitaplar
    private static final int BOOK_LIMIT = 5;

    public Reader(String userName, Long id, String password, Map<String, List<Book>> userBooks) {
        super(userName, PersonRoles.READER);
        this.id = id;
        this.password = password;
        this.userBooks = userBooks;
    }


    @Override
    public void borrowBook(int bookId, String userName) {
        // Kitabı ödünç alan kullanıcının listesine ekleme
        List<Book> borrowedBooks = userBooks.getOrDefault(userName, new ArrayList<>());

        // Kütüphaneden kitabı al
        Library library = new Library();
        Book book = library.getBooks().get((long) bookId);

        if (book != null) {
            borrowedBooks.add(book);
            userBooks.put(userName, borrowedBooks);
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }
    @Override
    public void returnBook(int bookId, String userName) {
        // Kullanıcının ödünç aldığı kitapları al
        List<Book> borrowedBooks = userBooks.get(userName);

        // Eğer kullanıcı ödünç aldığı kitapları bulduysa
        if (borrowedBooks != null) {
            // Listeden belirtilen kitapId'ye sahip kitabı çıkar
            boolean bookRemoved = borrowedBooks.removeIf(book -> book.getBookId().equals(bookId));

            // Eğer kitap çıkarıldıysa ve kullanıcının ödünç aldığı kitap listesi boş ise, kullanıcıyı haritadan kaldır
            if (bookRemoved) {
                if (borrowedBooks.isEmpty()) {
                    userBooks.remove(userName);
                } else {
                    // Kullanıcının ödünç aldığı kitap listesi boş değilse, haritada güncelle
                    userBooks.put(userName, borrowedBooks);
                }
            } else {
                // Kitap id'si bulunamadıysa kullanıcıyı bilgilendirin
                System.out.println("Kullanıcının ödünç aldığı kitaplar arasında belirtilen kitap bulunamadı.");
            }
        } else {
            // Kullanıcının ödünç aldığı kitap listesi bulunamadıysa kullanıcıyı bilgilendirin
            System.out.println("Kullanıcının ödünç aldığı kitaplar bulunamadı.");
        }
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
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", userBooks=" + userBooks +
                '}';
    }
}
