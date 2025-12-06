public class Member extends User{
    private String[] borrowedBooks;
    private int borrowCount;
    private static final int MAX_BORROW = 3;

    public Member(String userId, String name, String email) {
        super(userId, name, email);
    }

    @Override
    public void displayInfo() {
        if (borrowedBooks == null) {
            borrowedBooks = new String[MAX_BORROW];
            borrowCount = 0;
        }
        System.out.println("========== INFO MEMBER ==========");
        System.out.println("ID User  : " + userId);
        System.out.println("Nama     : " + name);
        System.out.println("Email    : " + email);
        System.out.println("Role     : Member Perpustakaan");
        System.out.println("Hak Akses: Pinjam & Kembalikan Buku");
        System.out.println("Buku Dipinjam: " + borrowCount + "/" + MAX_BORROW);
        if (borrowCount > 0) {
            System.out.println("Daftar Buku yang Dipinjam:");
            for (int i = 0; i < borrowCount; i++) {
                System.out.println("  " + (i + 1) + ". " + borrowedBooks[i]);
            }
        }
        System.out.println("=================================");
    }

    @Override
    public void interact(Library library) {
        System.out.println("\n[MEMBER MODE] " + name + " mengakses perpustakaan");
    }

    public boolean borrowBook(Library library, String bookId) {
        if (borrowedBooks == null) {
            borrowedBooks = new String[MAX_BORROW];
            borrowCount = 0;
        }

        if (borrowCount >= MAX_BORROW) {
            System.out.println("Maaf, " + name + " sudah mencapai batas maksimal peminjaman!");
            return false;
        }

        Book book = library.findBookById(bookId);
        if (book == null) {
            System.out.println("Buku dengan ID " + bookId + " tidak ditemukan!");
            return false;
        }

        if (!book.isAvailable()) {
            System.out.println("Maaf, buku '" + book.getTitle() + "' sedang dipinjam!");
            return false;
        }

        book.setAvailable(false);
        borrowedBooks[borrowCount] = book.getBookId() + " - " + book.getTitle();
        borrowCount++;
        System.out.println("[MEMBER] " + name + " berhasil meminjam: " + book.getTitle());
        return true;
    }

    public boolean returnBook(Library library, String bookId) {
        if (borrowedBooks == null) {
            borrowedBooks = new String[MAX_BORROW];
            borrowCount = 0;
        }

        Book book = library.findBookById(bookId);
        if (book == null) {
            System.out.println("Buku dengan ID " + bookId + " tidak ditemukan!");
            return false;
        }

        boolean found = false;
        int index = -1;
        for (int i = 0; i < borrowCount; i++) {
            if (borrowedBooks[i] != null && borrowedBooks[i].startsWith(bookId + " - ")) {
                found = true;
                index = i;
                break;
            }
        }

        if (!found) {
            System.out.println("Buku '" + book.getTitle() + "' tidak ada dalam daftar pinjaman Anda!");
            return false;
        }

        for (int i = index; i < borrowCount - 1; i++) {
            borrowedBooks[i] = borrowedBooks[i + 1];
        }
        borrowedBooks[borrowCount - 1] = null;
        borrowCount--;

        book.setAvailable(true);
        System.out.println("[MEMBER] " + name + " berhasil mengembalikan: " + book.getTitle());
        return true;
    }

    public void viewAvailableBooks(Library library) {
        System.out.println("\n[MEMBER] " + name + " melihat buku yang tersedia:");
        library.displayAvailableBooks();
    }

    public int getBorrowCount() {
        return borrowCount;
    }
}
