import com.workintech.Book;
import com.workintech.Library;
import com.workintech.enums.BookCategory;
import com.workintech.people.Librarian;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Kütüphane ve kütüphaneci oluşturma
        Library library = new Library();
        Librarian librarian = new Librarian("JohnDoe", library, "password123");

        // Scanner'ı başlat
        Scanner scanner = new Scanner(System.in);

        // Menüyü sürekli tekrar etmek için sonsuz döngü
        while (true) {
            // Kullanıcıya menü seçeneklerini sunma
            System.out.println("\nYapmak istediğiniz işlemi seçin:");
            System.out.println("0 - Çıkış");
            System.out.println("1 - Kitap ekleme");
            System.out.println("2 - Kitap silme");
            System.out.println("3 - Tüm kitapları listeleme");
            System.out.println("4 - Arama işlemleri");

            // Kullanıcıdan seçim yapmasını isteyin
            System.out.print("Seçiminizi girin: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // scanner'ı temizlemek için

            // Kullanıcının seçimlerine göre işlemleri gerçekleştirme
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
                    scanner.nextLine(); // scanner'ı temizlemek için

                    System.out.print("Kitap adını girin: ");
                    String bookName = scanner.nextLine();

                    System.out.print("Yazar adını girin: ");
                    String authorName = scanner.nextLine();

                    System.out.print("Stok sayısını girin: ");
                    int stock = scanner.nextInt();
                    scanner.nextLine(); // scanner'ı temizlemek için

                    System.out.println("Mevcut Kategoriler:");
                    for (BookCategory category : BookCategory.values()) {
                        System.out.println("- " + category);
                    }
                    System.out.print("Kategori seçiminizi girin: ");
                    BookCategory category = BookCategory.valueOf(scanner.nextLine().toUpperCase());

                    Book newBook = new Book(bookId, bookName, authorName, stock, category);
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
                    // Tüm kitapları listeleme
                    System.out.println("\nTüm kitaplar:");
                    List<Book> allBooks = librarian.getLibrary().listAllBooks();
                    for (Book book : allBooks) {
                        System.out.println(book.getBookName() + " - " + book.getAuthorName());
                    }
                    break;

                case 4:
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
                            boolean foundByAuthor = library.searchBooksByAuthor(searchAuthorName);
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
