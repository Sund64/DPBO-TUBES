import java.util.ArrayList;
import java.util.Scanner;

public class Register {
    private String name;
    private String email;
    private String password;
    private String address;
    
    private static ArrayList<String> registeredNames = new ArrayList<>();

    public Register(String name, String email, String address, String password) throws Exception {
        if (!email.endsWith("@gmail.com")) {
            throw new Exception("Email harus menggunakan domain @gmail.com");
        }
        if (AlreadyUse(name)) {
            throw new Exception("Nama ini sudah digunakan.");
        }
        this.name = name;
        this.email = email;
        this.address = address;
        this.password = password;
        registeredNames.add(name); 
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
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        user.printInfo();
        scanner.close();
	}
}
