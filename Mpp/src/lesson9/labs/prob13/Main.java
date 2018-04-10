package lesson9.labs.prob13;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Book b = new Book("test",3);
        List<BookCopy> copies = b.getCopies();
        copies.forEach(BookCopy::changeAvailability);

        System.out.println(b.isAvailable());
    }
}
