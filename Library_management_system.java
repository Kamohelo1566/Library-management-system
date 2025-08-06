import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        Library library = new Library();

        // Adding books to the library
        library.addBook(new Book("1984", "George Orwell", 5));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", 3));

        // Adding patrons to the library
        Patron patron1 = new Patron("Alice");
        Patron patron2 = new Patron("Bob");
        library.addPatron(patron1);
        library.addPatron(patron2);

        // Borrowing books
        library.borrowBook(patron1, library.getBooks().get(0)); // Alice borrows "1984"
        library.borrowBook(patron2, library.getBooks().get(1)); // Bob borrows "To Kill a Mockingbird"

        // Displaying status
        library.displayBookStatus();
        library.displayPatronStatus();

        // Returning a book
        library.returnBook(patron1, library.getBooks().get(0)); // Alice returns "1984"

        // Displaying status again
        System.out.println("\nAfter returning a book:");
        library.displayBookStatus();
        library.displayPatronStatus();
    }
}

class Book {
    private String title;
    private String author;
    private int available;

    public Book(String title, String author, int available) {
        this.title = title;
        this.author = author;
        this.available = available;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}

class Patron {
    private String name;
    private ArrayList<Book> borrowedBooks;

    public Patron(String name) {
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }
}

class Library {
    private ArrayList<Book> books;
    private ArrayList<Patron> patrons;

    public Library() {
        books = new ArrayList<>();
        patrons = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addPatron(Patron patron) {
        patrons.add(patron);
    }

    public void borrowBook(Patron patron, Book book) {
        if (book.getAvailable() > 0) {
            patron.borrowBook(book);
            book.setAvailable(book.getAvailable() - 1);
            System.out.println(patron.getName() + " borrowed " + book.getTitle());
        } else {
            System.out.println("Sorry, " + book.getTitle() + " is not available.");
        }
    }

    public void returnBook(Patron patron, Book book) {
        if (patron.getBorrowedBooks().contains(book)) {
            patron.returnBook(book);
            book.setAvailable(book.getAvailable() + 1);
            System.out.println(patron.getName() + " returned " + book.getTitle());
        } else {
            System.out.println(patron.getName() + " did not borrow " + book.getTitle());
        }
    }

    public void displayBookStatus() {
        System.out.println("\nBooks in the library:");
        for (Book book : books) {
            System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", Available: " + book.getAvailable());
        }
    }

    public void displayPatronStatus() {
        System.out.println("\nPatrons and their borrowed books:");
        for (Patron patron : patrons) {
            System.out.print(patron.getName() + " has borrowed: ");
            if (patron.getBorrowedBooks().isEmpty()) {
                System.out.println("No books.");
            } else {
                for (Book book : patron.getBorrowedBooks()) {
                    System.out.print(book.getTitle() + " ");
                }
                System.out.println();
            }
        }
    }

    public ArrayList<Book> getBooks() {
        return books;
    }
}