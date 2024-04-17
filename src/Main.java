import com.workintech.Book;
import com.workintech.Library;
import com.workintech.enums.BookCategory;
import com.workintech.enums.BookStatus;
import com.workintech.people.Author;
import com.workintech.people.Librarian;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Librarian librarian = new Librarian("JohnDoe", library, "password123");

        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.println("\nYapmak istediğiniz işlemi seçin:");
            System.out.println("0 - Çıkış");
            System.out.println("1 - Kitap ekleme");
            System.out.println("2 - Kitap silme");
            System.out.println("3 - Kitap Güncelleme işlemleri");
            System.out.println("4 - Tüm kitapları listeleme");
            System.out.println("5 - Arama işlemleri");

            System.out.print("Seçiminizi girin: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    System.out.println("Çıkış yapılıyor...");
                    scanner.close();
                    return;

                case 1:
                    // Kitap ekleme işlemi
                    System.out.println("Kitap ekleme işlemi için bilgileri girin:");

                    System.out.print("Kitap ID'sini girin: ");
                    long bookId = scanner.nextLong();
                    scanner.nextLine();

                    System.out.print("Kitap adını girin: ");
                    String bookName = scanner.nextLine();

                    System.out.print("Yazar adını girin: ");
                    String authorName = scanner.nextLine();
                    // `Author` nesnesini oluşturma
                    Set<Book> booksByAuthor = new HashSet<>();
                    Author author = new Author(authorName, booksByAuthor);

                    System.out.print("Stok sayısını girin: ");
                    int stock = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Mevcut Durumlar:");
                    for (BookStatus bookStatus : BookStatus.values()) {
                        System.out.println("- " + bookStatus);
                    }
                    System.out.print("Durum seçiminizi girin: ");
                    BookStatus status = BookStatus.valueOf(scanner.nextLine().toUpperCase());

                    System.out.println("Mevcut Kategoriler:");
                    for (BookCategory category : BookCategory.values()) {
                        System.out.println("- " + category);
                    }
                    System.out.print("Kategori seçiminizi girin: ");
                    BookCategory category = BookCategory.valueOf(scanner.nextLine().toUpperCase());

                    Book newBook = new Book(bookId, bookName, author, stock, status, category);
                    librarian.getLibrary().addBook(newBook);
                    System.out.println("Kitap başarıyla eklendi.");
                    break;

                case 2:
                    // Kitap silme işlemi
                    System.out.print("\nSilmek istediğiniz kitabın ID'sini girin: ");
                    long deleteBookId = scanner.nextLong();
                    library.deleteBook(deleteBookId);
                    System.out.println("Kitap başarıyla silindi.");
                    break;

                case 3:
                    System.out.println("Kitap bilgilerini güncelleme işlemi için bilgileri girin:");

                    System.out.print("Güncellenecek kitabın ID'sini girin: ");
                    long updateBookId = scanner.nextLong();
                    scanner.nextLine();

                    Book bookToUpdate = null;
                    for (Book book : librarian.getLibrary().listAllBooks()) {
                        if (book.getBookId() == updateBookId) {
                            bookToUpdate = book;
                            break;
                        }
                    }

                    if (bookToUpdate == null) {
                        System.out.println("Belirtilen ID'ye sahip bir kitap bulunamadı.");
                        return;
                    }

                    System.out.println( bookToUpdate.getBookId() + " ID'li " + bookToUpdate.getBookName()  + " Kitabının Stok Sayısı: " + bookToUpdate.getStock() +"," + " Mevcut Durumu: " + bookToUpdate.getStatus());
                    System.out.println("Hangi bilgileri güncellemek istiyorsunuz?");

                    System.out.println("1. Yeni stok sayısı");
                    System.out.println("2. Yeni durumu");
                    System.out.print("Seçiminizi yapın (1 veya 2): ");
                    int updateChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (updateChoice) {
                        case 1:
                            System.out.print("Yeni stok sayısını girin: ");
                            int newStock = scanner.nextInt();
                            scanner.nextLine();
                            bookToUpdate.setStock(newStock);
                            System.out.println("Kitap bilgileri güncellendi.");
                            break;
                        case 2:
                            System.out.println("Mevcut Durumlar:");
                            for (BookStatus bookStatus : BookStatus.values()) {
                                System.out.println("- " + bookStatus);
                            }
                            System.out.print("Yeni durumu seçin: ");
                            BookStatus newStatus = BookStatus.valueOf(scanner.nextLine().toUpperCase());
                            bookToUpdate.setStatus(newStatus);
                            System.out.println("Kitap bilgileri güncellendi.");
                            break;
                        default:
                            System.out.println("Geçersiz seçim.");
                            break;
                    }
                    break;



                case 4:
                    // Tüm kitapları listeleme
                    System.out.println("\nTüm kitaplar:");
                    List<Book> allBooks = librarian.getLibrary().listAllBooks();
                    for (Book book : allBooks) {
                        System.out.println(book.getBookId() + " numaralı kitap -> " + book.getBookName() + " - " + book.getAuthor());
                    }
                    break;

                case 5:
                    // Arama işlemleri
                    System.out.println("\nArama işlemleri:");
                    System.out.println("1 - Kitap ID'ye göre arama");
                    System.out.println("2 - Kitap adına göre arama");
                    System.out.println("3 - Kitap yazarına göre arama");
                    System.out.print("Seçiminizi girin: ");
                    int searchChoice = scanner.nextInt();
                    scanner.nextLine(); // scanner'ı temizlemek için

                    switch (searchChoice) {
                        case 1:
                            // Kitap ID'ye göre arama
                            System.out.print("Aramak istediğiniz kitabın ID'sini girin: ");
                            long searchBookId = scanner.nextLong();
                            boolean foundById = library.searchBookById(searchBookId);
                            if (foundById) {
                                System.out.println("Kitap bulundu.");
                            } else {
                                System.out.println("Kitap bulunamadı.");
                            }
                            break;

                        case 2:
                            // Kitap adına göre arama
                            System.out.print("Aramak istediğiniz kitabın adını girin: ");
                            String searchBookName = scanner.nextLine();
                            boolean foundByName = library.searchBooksByName(searchBookName);
                            if (foundByName) {
                                System.out.println("Kitap bulundu.");
                            } else {
                                System.out.println("Kitap bulunamadı.");
                            }
                            break;

                        case 3:
                            // Kitap yazarına göre arama
                            System.out.print("Aramak istediğiniz kitabın yazarını girin: ");
                            String searchAuthorName = scanner.nextLine();
                            Set<Book> emptyBooksSet = new HashSet<>();
                            Author searchAuthor = new Author(searchAuthorName, emptyBooksSet);
                            boolean foundByAuthor = library.searchBooksByAuthor(searchAuthor);
                            if (foundByAuthor) {
                                System.out.println("Kitap bulundu.");
                            } else {
                                System.out.println("Kitap bulunamadı.");
                            }
                            break;

                        default:
                            System.out.println("Geçersiz seçim. Lütfen tekrar deneyin.");
                            break;
                    }
                    break;

                default:
                    System.out.println("Geçersiz seçim. Lütfen tekrar deneyin.");
                    break;
            }
        }
    }
}
