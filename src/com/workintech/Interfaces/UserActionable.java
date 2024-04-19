package com.workintech.Interfaces;
import java.util.List;
import com.workintech.Book;



public interface UserActionable {
    void borrowBook(int bookId, String userName); //ödünç alma

    void returnBook(int bookId, String userName); //iade etme

    boolean checkUserLimit(String userName); // Kullanıcının kitap limiti kontrolü

    List<Book> getUserBooks(String userName);// Kullanıcıların mevcut kitapları: hangi kitabın hangi kullanıcıda olduğunu bulabilmek için
}
