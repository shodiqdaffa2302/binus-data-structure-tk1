public class Book {
    private String bookId;
    private String title;
    private String author;
    private boolean available = true;

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void displayInfo() {
        System.out.println("ID Buku  : " + bookId);
        System.out.println("Judul    : " + title);
        System.out.println("Pengarang: " + author);
        System.out.println("Status   : " + (available ? "Tersedia" : "Dipinjam"));
        System.out.println("----------------------------------------");
    }
}
