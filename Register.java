import java.util.ArrayList;

public class Register implements Identity {
    private int id;
    private String name;
    private String email;
    private String password;
    private String address;
    
    private static ArrayList<String> registeredNames = new ArrayList<>(); // Menyimpan daftar nama yang sudah digunakan

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

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
        user.printInfo();
        scanner.close();
	}
}
