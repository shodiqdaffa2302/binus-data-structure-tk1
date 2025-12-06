public class Library {
    private Book[] books;
    private int bookCount;
    private static final int INITIAL_CAPACITY = 100;

    public Library() {
        this.books = new Book[INITIAL_CAPACITY];
        this.bookCount = 0;
    }

    public void addBook(Book book) {
        if (bookCount >= books.length) {
            expandCapacity();
        }
        books[bookCount++] = book;
        bookCount++;
        System.out.println("Buku berhasil ditambahkan ke perpustakaan: " + book.getTitle());
    }

    public boolean removeBook(String bookId) {
        int index = findBookIndex(bookId);
        if (index == -1) {
            System.out.println("Buku dengan ID " + bookId + " tidak ditemukan!");
            return false;
        }

        if (!books[index].isAvailable()) {
            System.out.println("Buku sedang dipinjam, tidak dapat dihapus!");
            return false;
        }

        for (int i = index; i < bookCount - 1; i++) {
            books[i] = books[i + 1];
        }
        books[bookCount - 1] = null;
        bookCount--;

        compactArray();

        System.out.println("Buku dengan ID " + bookId + " berhasil dihapus!");
        return true;
    }

    public void searchBookByTitle(String title) {
        System.out.println("\n========== HASIL PENCARIAN ==========");
        System.out.println("Mencari: \"" + title + "\"");
        System.out.println("======================================");

        boolean found = false;
        int count = 0;

        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null && books[i].getTitle().toLowerCase().contains(title.toLowerCase())) {  // Null check
                count++;
                System.out.println("\nHasil #" + count + ":");
                books[i].displayInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Tidak ada buku yang cocok dengan pencarian.");
        } else {
            System.out.println("Total " + count + " buku ditemukan.");
        }
        System.out.println("======================================\n");
    }

    public void displayAllBooks() {
        System.out.println("\n========== SEMUA BUKU DI PERPUSTAKAAN ==========");
        int actualCount = 0;
        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null) {
                actualCount++;
            }
        }
        System.out.println("Total buku: " + actualCount);
        System.out.println("================================================");

        if (bookCount == 0) {
            System.out.println("Perpustakaan masih kosong.");
        } else {
            for (int i = 0; i < bookCount; i++) {
                if (books[i] != null) {
                    books[i].displayInfo();
                }
            }
        }
        System.out.println("================================================\n");
    }

    public void displayAvailableBooks() {
        System.out.println("\n========== BUKU TERSEDIA ==========");
        int count = 0;

        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null && books[i].isAvailable()) {
                count++;
                System.out.println("\nBuku Tersedia #" + count + ":");
                books[i].displayInfo();
            }
        }

        if (count == 0) {
            System.out.println("Tidak ada buku yang tersedia saat ini.");
        } else {
            System.out.println("Total: " + count + " buku tersedia");
        }
        System.out.println("===================================\n");
    }

    public Book findBookById(String bookId) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null && books[i].getBookId().equals(bookId)) {
                return books[i];
            }
        }
        return null;
    }

    private int findBookIndex(String bookId) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null && books[i].getBookId().equals(bookId)) {
                return i;
            }
        }
        return -1;
    }

    private void expandCapacity() {
        int newCapacity = books.length * 2;
        Book[] newArray = new Book[newCapacity];

        for (int i = 0; i < bookCount; i++) {
            newArray[i] = books[i];
        }

        books = newArray;
        System.out.println("[INFO] Kapasitas perpustakaan diperbesar menjadi " + newCapacity);
    }

    public int getBookCount() {
        return bookCount;
    }

    private int getActualBookCount() {
        int count = 0;
        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null) {
                count++;
            }
        }
        return count;
    }

    private void compactArray() {
        int writeIndex = 0;
        for (int readIndex = 0; readIndex < bookCount; readIndex++) {
            if (books[readIndex] != null) {
                if (writeIndex != readIndex) {
                    books[writeIndex] = books[readIndex];
                    books[readIndex] = null;
                }
                writeIndex++;
            }
        }
        bookCount = writeIndex;
    }
}
