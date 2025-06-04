import java.util.ArrayList;
import java.util.Scanner;

public class Register {
    private String name;
    private String email;
    private String password;
    private String address;
    
    private static ArrayList<String> registeredNames = new ArrayList<>(); // Menyimpan daftar nama yang sudah digunakan

    // Custom exception untuk email tidak valid
    static class InvalidEmailException extends Exception {
        public InvalidEmailException(String message) {
            super(message);
        }
    }

    // Custom exception untuk nama yang sudah digunakan
    static class NameAlreadyUsedException extends Exception {
        public NameAlreadyUsedException(String message) {
            super(message);
        }
    }

    public Register(String name, String email, String address, String password) throws InvalidEmailException, NameAlreadyUsedException {
        if (!email.endsWith("@gmail.com")) {
            throw new InvalidEmailException("Email harus menggunakan domain @gmail.com");
        }
        if (AlreadyUse(name)) {
            throw new NameAlreadyUsedException("Nama ini sudah digunakan.");
        }
        this.name = name;
        this.email = email;
        this.address = address;
        this.password = password;
        registeredNames.add(name); // Menyimpan nama ke dalam daftar
        System.out.println("Registrasi berhasil.");
    }

    public static boolean AlreadyUse(String name) {
        return registeredNames.contains(name);
    }

    public void printInfo() {
        System.out.println("===== INFO REGISTRASI =====");
        System.out.println("Nama: " + name);
        System.out.println("Email: " + email);
        System.out.println("Alamat: " + address);
        System.out.println("===========================");
    }

    	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        Register user = null;
        while (user == null) {
            System.out.print("Masukkan nama: ");
            String name = scanner.nextLine();
            System.out.print("Masukkan email: ");
            String email = scanner.nextLine();
            System.out.print("Masukkan alamat: ");
            String address = scanner.nextLine();
            System.out.print("Masukkan password: ");
            String password = scanner.nextLine();

            try {
                user = new Register(name, email, address, password);
            } catch (InvalidEmailException | NameAlreadyUsedException e) {
                System.out.println(e.getMessage());
            }
        }
        user.printInfo();
        scanner.close();
	}
}
