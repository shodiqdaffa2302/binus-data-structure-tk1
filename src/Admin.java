public class Admin extends User {
    public Admin(String userId , String name, String email) {
        super(userId , name , email);
    }

    @Override
    public void displayInfo() {
        System.out.println("========== INFO ADMIN ==========");
        System.out.println("ID User  : " + userId);
        System.out.println("Nama     : " + name);
        System.out.println("Email    : " + email);
        System.out.println("Role     : Administrator");
        System.out.println("Hak Akses: Mengelola Buku (Tambah, Hapus, Lihat)");
        System.out.println("================================");
    }

    @Override
    public void interact(Library library) {
        System.out.println("\n[ADMIN MODE] " + name + " sedang mengelola perpustakaan");
    }

    public void addBook(Library library, Book book) {
        library.addBook(book);
        System.out.println("[ADMIN] " + name + " menambahkan buku: " + book.getTitle());
    }

    public void removeBook(Library library, String bookId) {
        boolean success = library.removeBook(bookId);
        if (success) {
            System.out.println("[ADMIN] " + name + " menghapus buku dengan ID: " + bookId);
        }
    }

    public void viewAllBooks(Library library) {
        System.out.println("\n[ADMIN] " + name + " melihat semua buku:");
        library.displayAllBooks();
    }
}
