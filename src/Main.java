import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Library library = new Library();
    private static User currentUser = null;

    private static User[] registeredUsers = new User[10];
    private static int userCount = 0;

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║     SISTEM MANAJEMEN PERPUSTAKAAN - OOP & ARRAY      ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");

        initializeSampleData();
        initializeUsers();

        while (true) {
            if (currentUser == null) {
                showLoginMenu();
            } else {
                showMainMenu();
            }
        }
    }

    private static void initializeSampleData() {
        library.addBook(new Book("B001", "Pemrograman Java", "James Gosling"));
        library.addBook(new Book("B002", "Struktur Data", "Robert Sedgewick"));
        library.addBook(new Book("B003", "Database System", "Ramez Elmasri"));
        library.addBook(new Book("B004", "Pemrograman Web", "Jon Duckett"));
        library.addBook(new Book("B005", "Artificial Intelligence", "Stuart Russell"));
    }

    private static void initializeUsers() {
        registeredUsers[userCount++] = new Admin("ADM001", "Admin", "admin@gmail.com");
        registeredUsers[userCount++] = new Member("MBR001", "Member 1", "member@gmail.com");
    }

    private static User findUserByEmail(String email) {
        for (int i = 0; i < userCount; i++) {
            if (registeredUsers[i] != null &&
                    registeredUsers[i].getEmail().equalsIgnoreCase(email)) {
                return registeredUsers[i];
            }
        }
        return null;
    }

    private static void showLoginMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                    LOGIN MENU");
        System.out.println("=".repeat(60));
        System.out.println("1. Login sebagai Admin");
        System.out.println("2. Login sebagai Member");
        System.out.println("3. Exit");
        System.out.println("=".repeat(60));
        System.out.print("Pilih menu (1-4): ");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                loginAsAdmin();
                break;
            case 2:
                loginAsMember();
                break;
            case 3:
                System.out.println("\n╔════════════════════════════════════════════════════════╗");
                System.out.println("║         Terima kasih telah menggunakan sistem!        ║");
                System.out.println("╚════════════════════════════════════════════════════════╝");
                scanner.close();
                System.exit(0);
            default:
                System.out.println("Pilihan tidak valid!");
        }
    }

    private static void loginAsAdmin() {
        System.out.println("\n--- LOGIN ADMIN ---");
        System.out.print("Email: ");
        String email = scanner.nextLine();

        User user = findUserByEmail(email);
        if (!(user instanceof Admin)) {
            System.out.println("Email tidak terdaftar!");
            return;
        }

        System.out.println("\nLogin berhasil!");
        currentUser = user;

        System.out.println("\nLogin berhasil!");
        currentUser.displayInfo();
    }

    private static void loginAsMember() {
        System.out.println("\n--- LOGIN MEMBER ---");
        System.out.print("Email: ");
        String email = scanner.nextLine();

        User user = findUserByEmail(email);
        if (!(user instanceof Member)) {
            System.out.println("Email tidak terdaftar!");
            return;
        }

        System.out.println("\nLogin berhasil!");
        currentUser = user;

        System.out.println("\nLogin berhasil!");
        currentUser.displayInfo();
    }

    private static void showMainMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                    MAIN MENU");
        System.out.println("=".repeat(60));

        currentUser.interact(library);

        System.out.println("\n" + "=".repeat(60));

        if (currentUser instanceof Admin) {
            showAdminMenu();
        } else if (currentUser instanceof Member) {
            showMemberMenu();
        }
    }

    private static void showAdminMenu() {
        System.out.println("1. Tambah Buku");
        System.out.println("2. Hapus Buku");
        System.out.println("3. Cari Buku");
        System.out.println("4. Lihat Semua Buku");
        System.out.println("5. Lihat Buku Tersedia");
        System.out.println("6. Lihat Info Admin");
        System.out.println("7. Logout");
        System.out.println("8. Exit");
        System.out.println("=".repeat(60));
        System.out.print("Pilih menu (1-8): ");

        int choice = getIntInput();
        Admin admin = (Admin) currentUser;

        switch (choice) {
            case 1:
                addBookMenu(admin);
                break;
            case 2:
                removeBookMenu(admin);
                break;
            case 3:
                searchBookMenu();
                break;
            case 4:
                admin.viewAllBooks(library);
                break;
            case 5:
                library.displayAvailableBooks();
                break;
            case 6:
                currentUser.displayInfo();
                break;
            case 7:
                currentUser = null;
                System.out.println("\nLogout berhasil!");
                break;
            case 8:
                System.out.println("\n╔════════════════════════════════════════════════════════╗");
                System.out.println("║         Terima kasih telah menggunakan sistem!        ║");
                System.out.println("╚════════════════════════════════════════════════════════╝");
                scanner.close();
                System.exit(0);
            default:
                System.out.println("Pilihan tidak valid!");
        }
    }

    private static void showMemberMenu() {
        System.out.println("1. Lihat Buku Tersedia");
        System.out.println("2. Cari Buku");
        System.out.println("3. Pinjam Buku");
        System.out.println("4. Kembalikan Buku");
        System.out.println("5. Lihat Info Member");
        System.out.println("6. Logout");
        System.out.println("7. Exit");
        System.out.println("=".repeat(60));
        System.out.print("Pilih menu (1-7): ");

        int choice = getIntInput();
        Member member = (Member) currentUser;

        switch (choice) {
            case 1:
                member.viewAvailableBooks(library);
                break;
            case 2:
                searchBookMenu();
                break;
            case 3:
                borrowBookMenu(member);
                break;
            case 4:
                returnBookMenu(member);
                break;
            case 5:
                currentUser.displayInfo();
                break;
            case 6:
                currentUser = null;
                System.out.println("\nLogout berhasil!");
                break;
            case 7:
                System.out.println("\n╔════════════════════════════════════════════════════════╗");
                System.out.println("║         Terima kasih telah menggunakan sistem!        ║");
                System.out.println("╚════════════════════════════════════════════════════════╝");
                scanner.close();
                System.exit(0);
            default:
                System.out.println("Pilihan tidak valid!");
        }
    }

    private static void addBookMenu(Admin admin) {
        System.out.println("\n--- TAMBAH BUKU ---");
        System.out.print("ID Buku: ");
        String bookId = scanner.nextLine();
        System.out.print("Judul: ");
        String title = scanner.nextLine();
        System.out.print("Pengarang: ");
        String author = scanner.nextLine();

        Book newBook = new Book(bookId, title, author);
        admin.addBook(library, newBook);
    }

    private static void removeBookMenu(Admin admin) {
        System.out.println("\n--- HAPUS BUKU ---");
        System.out.print("Masukkan ID Buku yang akan dihapus: ");
        String bookId = scanner.nextLine();
        admin.removeBook(library, bookId);
    }

    private static void searchBookMenu() {
        System.out.println("\n--- CARI BUKU ---");
        System.out.print("Masukkan judul buku: ");
        String title = scanner.nextLine();
        library.searchBookByTitle(title);
    }

    private static void borrowBookMenu(Member member) {
        System.out.println("\n--- PINJAM BUKU ---");
        library.displayAvailableBooks();
        System.out.print("\nMasukkan ID Buku yang akan dipinjam: ");
        String bookId = scanner.nextLine();
        member.borrowBook(library, bookId);
    }

    private static void returnBookMenu(Member member) {
        System.out.println("\n--- KEMBALIKAN BUKU ---");
        member.displayInfo();
        System.out.print("\nMasukkan ID Buku yang akan dikembalikan: ");
        String bookId = scanner.nextLine();
        member.returnBook(library, bookId);
    }

    private static int getIntInput() {
        try {
            int input = Integer.parseInt(scanner.nextLine());
            return input;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}