import java.util.ArrayList;

public class Register extends User implements Identity {
    private String address;
    
    private static ArrayList<String> registeredNames = new ArrayList<>(); // Menyimpan daftar nama yang sudah digunakan

    public Register(int id, String name, String email, String password, int phoneNumber, char gender, String birthday, String address) throws Exception {
        super(id, name, email, password, phoneNumber, gender, birthday);
        if (!email.endsWith("@gmail.com")) {
            throw new Exception("Email harus menggunakan domain @gmail.com");
        }
        if (AlreadyUse(name)) {
            throw new Exception("Nama ini sudah digunakan.");
        }
        this.address = address;
        registeredNames.add(name); // Menyimpan nama ke dalam daftar
        System.out.println("Registrasi berhasil.");
    }

    public static boolean AlreadyUse(String name) {
        return registeredNames.contains(name);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void printInfo() {
        System.out.println("===== INFO REGISTRASI =====");
        System.out.println("Nama: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Alamat: " + address);
        System.out.println("Nomor Telepon: " + getPhoneNumber());
        System.out.println("Jenis Kelamin: " + getGender());
        System.out.println("Tanggal Lahir: " + getBirthday());
        System.out.println("===========================");
    }
}

import java.util.Scanner;

public class MainRegister {
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
