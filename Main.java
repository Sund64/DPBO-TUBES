import java.util.*;

public class Main {
    private static ArrayList<Register> daftarRegister = new ArrayList<>();
    private static User currentUser = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Tambahkan akun test ke daftarRegister
        try {
            daftarRegister.add(new Register(
                999, // id
                "Test User",
                "testuser@gmail.com",
                "test123",
                8123456789012L, // nomor telepon contoh
                'L',
                "2000-01-01",
                "Jl. Test No. 1"
            ));
        } catch (Exception e) {
            // Tidak perlu aksi, hanya untuk inisialisasi test
        }

        // Data galon & refill
        Galon[] galons = {
            new Galon(1, 19.0, "Aqua", 50000, 10),
            new Galon(2, 19.0, "Club", 45000, 7),
            new Galon(3, 19.0, "ron 88", 45000, 11)
        };
        Refill[] refills = {
            new Refill(1, 19.0, "Aqua", 50000, 10, 12000),
            new Refill(2, 19.0, "Club", 45000, 7, 10000),
            new Refill(3, 19.0, "ron 88", 45000, 7, 7000),
        };

        // Data reward
        Reward[] rewards = {
            new Reward(101, "Gratis Ongkir", 100, "Berlaku untuk 1x refill galon"),
            new Reward(102, "Gratis Refil", 50, "Refil galon gratis 1x"),
            new Reward(103, "Promo Diskon", 25, "Diskon 10% untuk isi ulang berikutnya")
        };

        // Data delivery
        HashMap<Integer, Delivery> orders = new HashMap<>();

        // Tambahkan keranjang global
        Keranjang keranjang = new Keranjang();

        // ArrayList untuk menampung data pembelian dari menu beli galon
        ArrayList<String> daftarPembelianGalon = new ArrayList<>();

        // === LOGIN/REGISTER LOOP ===
        while (currentUser == null) {
            System.out.println("\n===== SELAMAT DATANG DI isiAir =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Lupa Password");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");
            String menuInput = scanner.nextLine();
            int menu;
            try {
                menu = Integer.parseInt(menuInput);
            } catch (NumberFormatException e) {
                System.out.println("Pilihan anda harus angka sesuai yang ada di menu");
                continue;
            }

            if (menu == 1) {
                // Register
                try {
                    String name;
                    while (true) {
                        System.out.print("Nama: ");
                        name = scanner.nextLine();
                        if (name.isEmpty()) {
                            System.out.println("Nama tidak boleh kosong, Harap isi terlebih dahulu");
                        } else {
                            break;
                        }
                    }
                    String email;
                    while (true) {
                        System.out.print("Email (gunakan @gmail.com): ");
                        email = scanner.nextLine();
                        if (email.isEmpty()) {
                            System.out.println("Email tidak boleh kosong, Harap isi terlebih dahulu");
                        } else if (!email.endsWith("@gmail.com")) {
                            System.out.println("Email harus menggunakan domain @gmail.com");
                        } else {
                            boolean emailExists = false;
                            for (Register reg : daftarRegister) {
                                if (reg.getEmail().equals(email)) {
                                    emailExists = true;
                                    break;
                                }
                            }
                            if (emailExists) {
                                System.out.println("Email sudah terdaftar, silakan gunakan email lain.");
                            } else {
                                break;
                            }
                        }
                    }
                    String address;
                    while (true) {
                        System.out.print("Alamat: ");
                        address = scanner.nextLine();
                        if (address.isEmpty()) {
                            System.out.println("Alamat tidak boleh kosong, Harap isi terlebih dahulu");
                        } else {
                            break;
                        }
                    }
                    String password;
                    while (true) {
                        System.out.print("Password: ");
                        password = scanner.nextLine();
                        if (password.isEmpty()) {
                            System.out.println("Password tidak boleh kosong, Harap isi terlebih dahulu");
                        } else {
                            break;
                        }
                    }
                    String phoneInput;
                    long phoneNumber;
                    while (true) {
                        System.out.print("Nomor Telepon: ");
                        phoneInput = scanner.nextLine();
                        if (phoneInput.isEmpty()) {
                            System.out.println("Nomor Telepon tidak boleh kosong, Harap isi terlebih dahulu");
                            continue;
                        }
                        if (!phoneInput.matches("\\d+")) {
                            System.out.println("Harap masukan nomor telepon yang benar");
                            continue;
                        }
                        if (phoneInput.length() > 13) {
                            System.out.println("Maksimal nomor telepon hanya 13 digit");
                            continue;
                        }
                        boolean phoneExists = false;
                        for (Register reg : daftarRegister) {
                            if (String.valueOf(reg.getPhoneNumber()).equals(phoneInput)) {
                                phoneExists = true;
                                break;
                            }
                        }
                        if (phoneExists) {
                            System.out.println("nomor telepon sudah digunakan");
                            continue;
                        }
                        try {
                            phoneNumber = Long.parseLong(phoneInput);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Nomor telepon tidak valid. Gunakan hanya angka.");
                        }
                    }
                    String genderInput;
                    char gender;
                    while (true) {
                        System.out.print("Jenis Kelamin (L/P): ");
                        genderInput = scanner.nextLine();
                        if (genderInput.isEmpty()) {
                            System.out.println("Jenis Kelamin tidak boleh kosong, Harap isi terlebih dahulu");
                            continue;
                        }
                        char genderChar = Character.toUpperCase(genderInput.charAt(0));
                        if (genderChar != 'L' && genderChar != 'P') {
                            System.out.println("Yang anda masukan tidak valid, Harap masukaln (L/P)");
                            continue;
                        }
                        gender = genderChar;
                        break;
                    }
                    String birthday;
                    while (true) {
                        System.out.print("Tanggal Lahir (yyyy-mm-dd): ");
                        birthday = scanner.nextLine();
                        if (birthday.isEmpty()) {
                            System.out.println("Tanggal Lahir tidak boleh kosong, Harap isi terlebih dahulu");
                            continue;
                        }
                        // Hilangkan semua karakter selain angka
                        String digitsOnly = birthday.replaceAll("[^0-9]", "");
                        if (digitsOnly.length() != 8) {
                            System.out.println("Tanggal lahir harus 8 digit angka (format: yyyy-mm-dd atau yyyymmdd)");
                            continue;
                        }
                        if (!digitsOnly.matches("\\d{8}")) {
                            System.out.println("Tanggal lahir harus berupa angka");
                            continue;
                        }
                        // Jika user tidak pakai '-', formatkan ke yyyy-mm-dd
                        if (!birthday.contains("-")) {
                            birthday = digitsOnly.substring(0, 4) + "-" + digitsOnly.substring(4, 6) + "-" + digitsOnly.substring(6, 8);
                        }
                        break;
                    }

                    Register reg = new Register(
                        daftarRegister.size() + 1, // id
                        name,
                        email,
                        password,
                        phoneNumber, // gunakan long, jangan cast ke int
                        gender,
                        birthday,
                        address
                    );
                    daftarRegister.add(reg);
                    reg.printInfo();
                    System.out.println("Silakan login untuk melanjutkan.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (menu == 2) {
                // Login
                while (true) {
                    System.out.println("Ketik 0 pada Email atau Password untuk kembali.");
                    String email;
                    while (true) {
                        System.out.print("Email: ");
                        email = scanner.nextLine();
                        if (email.equals("0")) break;
                        if (email.isEmpty()) {
                            System.out.println("email tidak boleh kosong");
                            continue;
                        }
                        if (!email.endsWith("@gmail.com")) {
                            System.out.println("harus menggunakan domain @gmail.com");
                            continue;
                        }
                        break;
                    }
                    if (email.equals("0")) break;
                    String password;
                    while (true) {
                        System.out.print("Password: ");
                        password = scanner.nextLine();
                        if (password.equals("0")) break;
                        if (password.isEmpty()) {
                            System.out.println("password tidak boleh kosong");
                            continue;
                        }
                        break;
                    }
                    if (password.equals("0")) break;
                    Register regFound = null;
                    for (Register reg : daftarRegister) {
                        if (reg.getEmail().equals(email)) {
                            regFound = reg;
                            break;
                        }
                    }
                    if (regFound == null) {
                        System.out.println("Email belum terdaftar.");
                        continue;
                    } else if (!regFound.getPassword().equals(password)) {
                        System.out.println("Email atau password salah.");
                        continue;
                    } else {
                        currentUser = regFound;
                        System.out.println("Login berhasil. Selamat datang, " + currentUser.getName());
                        break;
                    }
                }
            } else if (menu == 3) {
                // Lupa Password (OTP)
                System.out.print("Masukkan email user: ");
                String email = scanner.nextLine();
                Register reg = null;
                for (Register r : daftarRegister) {
                    if (r.getEmail().equals(email)) {
                        reg = r;
                        break;
                    }
                }
                if (reg == null) {
                    System.out.println("User tidak ditemukan.");
                    continue;
                }
                ForgotPassword fp = new ForgotPassword(reg.getId(), reg.getName(), reg.getEmail(), reg.getPassword(), reg.getPhoneNumber(), reg.getGender(), reg.getBirthday());
                System.out.println("Kode OTP Anda: " + fp.getOTPCode());
                System.out.print("Masukkan OTP: ");
                int otp = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Masukkan password baru: ");
                String newPass = scanner.nextLine();
                fp.resetPasswordWithOTP(otp, newPass);
                reg.setPassword(newPass);
            } else if (menu == 4) {
                System.out.println("Terima kasih telah menggunakan isiAir!");
                scanner.close();
                return;
            } else {
                System.out.println("Menu tidak tersedia, silahkan pilih sesuai nomor pada menu");
            }
        }

        // === MENU UTAMA SETELAH LOGIN ===
        while (true) {
            System.out.println("\n===== MENU UTAMA isiAir =====");
            System.out.println("1. Beli galon");
            System.out.println("2. Refil galon");
            System.out.println("3. Pesanan Saya");
            System.out.println("4. Keranjang");
            System.out.println("5. Chat");
            System.out.println("6. Notifikasi");
            System.out.println("7. Logout");
            System.out.println("8. Keluar");
            System.out.print("Pilih menu: ");
            String menuInput = scanner.nextLine();
            int menu;
            try {
                menu = Integer.parseInt(menuInput);
            } catch (NumberFormatException e) {
                System.out.println("Pilihan anda harus angka sesuai yang ada di menu");
                continue;
            }

            if (menu == 1) {
                // Beli galon
                while (true) {
                    System.out.println("\n========== DAFTAR GALON ==========");
                    System.out.printf("| %-3s | %-10s | %-7s | %-18s | %-18s | %-5s |\n",
                            "ID", "Brand", "Volume", "Harga dengan Galon", "Harga tanpa Galon", "Stok");
                    System.out.println("-------------------------------------------------------------------------------");
                    for (Galon g : galons) {
                        int hargaDenganGalon = g.getPrice();
                        int hargaTukarGalon = hargaDenganGalon - 30000;
                        System.out.printf("| %-3d | %-10s | %-7.1f | Rp%-16d | Rp%-16d | %-5d |\n",
                                g.getId(), g.getBrand(), g.getVolume(), hargaDenganGalon, hargaTukarGalon, g.getStock());
                    }
                    System.out.println("\nPilih galon yang ingin dibeli:");
                    System.out.println("1. Aqua");
                    System.out.println("2. Club");
                    System.out.println("3. Ron 88");
                    System.out.println("4. Lemineral");
                    System.out.println("5. Kembali ke menu utama");
                    System.out.print("Pilih menu: ");
                    int pilihan = scanner.nextInt();
                    scanner.nextLine();
                    if (pilihan >= 1 && pilihan <= 4) {
                        int idBeli = pilihan;
                        if (pilihan == 4) {
                            System.out.println("❌ Galon Lemineral belum tersedia.");
                            continue;
                        }
                        System.out.println("1. Dengan galon baru");
                        System.out.println("2. Tukar galon");
                        System.out.print("Pilih jenis transaksi: ");
                        int jenisTransaksi = scanner.nextInt();
                        scanner.nextLine();
                        if (jenisTransaksi != 1 && jenisTransaksi != 2) {
                            System.out.println("Pilihan tidak valid.");
                            continue;
                        }
                        System.out.print("Masukkan jumlah yang ingin dibeli: ");
                        int jumlahBeli = scanner.nextInt();
                        scanner.nextLine();
                        boolean ditemukan = false;
                        for (Galon g : galons) {
                            if (g.getId() == idBeli) {
                                ditemukan = true;
                                if (g.getStock() >= jumlahBeli) {
                                    g.reduceStock(jumlahBeli);
                                    String jenis = (jenisTransaksi == 1) ? "Dengan galon baru" : "Tukar galon";
                                    String item = g.getBrand() + " x" + jumlahBeli + " (" + jenis + ")";
                                    keranjang.tambahItem(item);
                                    daftarPembelianGalon.add(item); // Tambahkan ke daftar pembelian
                                    System.out.println("pesanan berhasil di tambahkan ke keranjang");
                                    keranjang.tampilkanKeranjang();
                                } else {
                                    System.out.println("❌ Stok tidak mencukupi.");
                                }
                            }
                        }
                        if (!ditemukan) {
                            System.out.println("❌ Galon dengan ID tersebut tidak ditemukan.");
                        }
                    } else if (pilihan == 5) {
                        break;
                    } else {
                        System.out.println("⚠️ Pilihan tidak valid. Silakan coba lagi.");
                    }
                }
            } else if (menu == 2) {
                // Refil galon
                while (true) {
                    System.out.println("\n========== REFILL GALON ==========");
                    System.out.printf("| %-3s | %-10s | %-7s | %-10s |\n",
                            "ID", "Brand", "Volume", "Harga Refill");
                    System.out.println("------------------------------------------------");
                    for (Refill r : refills) {
                        r.printRefillInfo();
                    }
                    System.out.print("\nMasukkan ID galon untuk refill: ");
                    int idRefill = scanner.nextInt();
                    System.out.print("Jumlah galon yang ingin direfill: ");
                    int jumlahRefill = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Berhasil Refill galon!");
                    break;
                }
            } else if (menu == 3) {
                // Pesanan Saya
                while (true) {
                    System.out.print("Masukkan ID Pesanan (tekan ENTER untuk kembali): ");
                    String input = scanner.nextLine();
                    if (input.isEmpty()) break;
                    int id;
                    try {
                        id = Integer.parseInt(input);
                    } catch (NumberFormatException e) {
                        System.out.println("ID harus berupa angka! Silakan coba lagi.");
                        continue;
                    }
                    if (orders.containsKey(id) && orders.get(id).isCompleted()) {
                        System.out.println("Pesanan ID " + id + " sudah sampai.");
                    } else {
                        Delivery delivery = orders.getOrDefault(id, new Delivery(id));
                        delivery.updateStatus();
                        delivery.printInfo();
                        if (!delivery.isCompleted()) {
                            orders.put(id, delivery);
                        }
                    }
                    System.out.println("Terima kasih!");
                }
            } else if (menu == 4) {
                // Keranjang
                while (true) {
                    System.out.println("\n===== KERANJANG ANDA =====");
                    keranjang.tampilkanKeranjang(daftarPembelianGalon, galons);
                    if (daftarPembelianGalon.isEmpty()) {
                        System.out.println("keranjang kosong");
                    }
                    System.out.println("1. Checkout");
                    System.out.println("2. Kembali");
                    System.out.print("Pilih menu: ");
                    String pilihKeranjang = scanner.nextLine();
                    if (pilihKeranjang.equals("1")) {
                        if (daftarPembelianGalon.isEmpty()) {
                            System.out.println("keranjang kosong");
                        } else {
                            System.out.println("Checkout berhasil. Terima kasih telah berbelanja!");
                            daftarPembelianGalon.clear();
                        }
                    } else if (pilihKeranjang.equals("2")) {
                        break;
                    } else {
                        System.out.println("Pilihan tidak valid.");
                    }
                }
            } else if (menu == 5) {
                // Chat
                System.out.println("Fitur chat belum tersedia.");
            } else if (menu == 6) {
                // Notifikasi
                System.out.println("Fitur notifikasi belum tersedia.");
            } else if (menu == 7) {
                // Logout
                System.out.println("Anda telah logout.");
                currentUser = null;
                // Kembali ke login/register
                while (currentUser == null) {
                    System.out.println("\n===== SELAMAT DATANG DI isiAir =====");
                    System.out.println("1. Register");
                    System.out.println("2. Login");
                    System.out.println("3. Lupa Password");
                    System.out.println("4. Keluar");
                    System.out.print("Pilih menu: ");
                    String menuInputLogout = scanner.nextLine();
                    int menuLogout;
                    try {
                        menuLogout = Integer.parseInt(menuInputLogout);
                    } catch (NumberFormatException e) {
                        System.out.println("Pilihan anda harus angka sesuai yang ada di menu");
                        continue;
                    }

                    if (menuLogout == 1) {
                        // Register
                        try {
                            String name;
                            while (true) {
                                System.out.print("Nama: ");
                                name = scanner.nextLine();
                                if (name.isEmpty()) {
                                    System.out.println("Nama tidak boleh kosong, Harap isi terlebih dahulu");
                                } else {
                                    break;
                                }
                            }
                            String email;
                            while (true) {
                                System.out.print("Email (gunakan @gmail.com): ");
                                email = scanner.nextLine();
                                if (email.isEmpty()) {
                                    System.out.println("Email tidak boleh kosong, Harap isi terlebih dahulu");
                                } else if (!email.endsWith("@gmail.com")) {
                                    System.out.println("Email harus menggunakan domain @gmail.com");
                                } else {
                                    boolean emailExists = false;
                                    for (Register reg : daftarRegister) {
                                        if (reg.getEmail().equals(email)) {
                                            emailExists = true;
                                            break;
                                        }
                                    }
                                    if (emailExists) {
                                        System.out.println("Email sudah terdaftar, silakan gunakan email lain.");
                                    } else {
                                        break;
                                    }
                                }
                            }
                            String address;
                            while (true) {
                                System.out.print("Alamat: ");
                                address = scanner.nextLine();
                                if (address.isEmpty()) {
                                    System.out.println("Alamat tidak boleh kosong, Harap isi terlebih dahulu");
                                } else {
                                    break;
                                }
                            }
                            String password;
                            while (true) {
                                System.out.print("Password: ");
                                password = scanner.nextLine();
                                if (password.isEmpty()) {
                                    System.out.println("Password tidak boleh kosong, Harap isi terlebih dahulu");
                                } else {
                                    break;
                                }
                            }
                            String phoneInput2;
                            long phoneNumber2;
                            while (true) {
                                System.out.print("Nomor Telepon: ");
                                phoneInput2 = scanner.nextLine();
                                if (phoneInput2.isEmpty()) {
                                    System.out.println("Nomor Telepon tidak boleh kosong, Harap isi terlebih dahulu");
                                    continue;
                                }
                                if (!phoneInput2.matches("\\d+")) {
                                    System.out.println("Harap masukan nomor telepon yang benar");
                                    continue;
                                }
                                if (phoneInput2.length() > 13) {
                                    System.out.println("Maksimal nomor telepon hanya 13 digit");
                                    continue;
                                }
                                boolean phoneExists = false;
                                for (Register reg : daftarRegister) {
                                    if (String.valueOf(reg.getPhoneNumber()).equals(phoneInput2)) {
                                        phoneExists = true;
                                        break;
                                    }
                                }
                                if (phoneExists) {
                                    System.out.println("nomor telepon sudah digunakan");
                                    continue;
                                }
                                try {
                                    phoneNumber2 = Long.parseLong(phoneInput2);
                                    break;
                                } catch (NumberFormatException e) {
                                    System.out.println("Nomor telepon tidak valid. Gunakan hanya angka.");
                                }
                            }
                            String genderInput;
                            char gender;
                            while (true) {
                                System.out.print("Jenis Kelamin (L/P): ");
                                genderInput = scanner.nextLine();
                                if (genderInput.isEmpty()) {
                                    System.out.println("Jenis Kelamin tidak boleh kosong, Harap isi terlebih dahulu");
                                    continue;
                                }
                                char genderChar = Character.toUpperCase(genderInput.charAt(0));
                                if (genderChar != 'L' && genderChar != 'P') {
                                    System.out.println("Yang anda masukan tidak valid, Harap masukaln (L/P)");
                                    continue;
                                }
                                gender = genderChar;
                                break;
                            }
                            String birthday;
                            while (true) {
                                System.out.print("Tanggal Lahir (yyyy-mm-dd): ");
                                birthday = scanner.nextLine();
                                if (birthday.isEmpty()) {
                                    System.out.println("Tanggal Lahir tidak boleh kosong, Harap isi terlebih dahulu");
                                    continue;
                                }
                                // Hilangkan semua karakter selain angka
                                String digitsOnly = birthday.replaceAll("[^0-9]", "");
                                if (digitsOnly.length() != 8) {
                                    System.out.println("Tanggal lahir harus 8 digit angka (format: yyyy-mm-dd atau yyyymmdd)");
                                    continue;
                                }
                                if (!digitsOnly.matches("\\d{8}")) {
                                    System.out.println("Tanggal lahir harus berupa angka");
                                    continue;
                                }
                                // Jika user tidak pakai '-', formatkan ke yyyy-mm-dd
                                if (!birthday.contains("-")) {
                                    birthday = digitsOnly.substring(0, 4) + "-" + digitsOnly.substring(4, 6) + "-" + digitsOnly.substring(6, 8);
                                }
                                break;
                            }

                            Register reg = new Register(
                                daftarRegister.size() + 1, // id
                                name,
                                email,
                                password,
                                phoneNumber2, // gunakan long, jangan cast ke int
                                gender,
                                birthday,
                                address
                            );
                            daftarRegister.add(reg);
                            reg.printInfo();
                            System.out.println("Silakan login untuk melanjutkan.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } else if (menuLogout == 2) {
                        // Login
                        while (true) {
                            System.out.println("Ketik 0 pada Email atau Password untuk kembali.");
                            String email;
                            while (true) {
                                System.out.print("Email: ");
                                email = scanner.nextLine();
                                if (email.equals("0")) break;
                                if (email.isEmpty()) {
                                    System.out.println("email tidak boleh kosong");
                                    continue;
                                }
                                if (!email.endsWith("@gmail.com")) {
                                    System.out.println("harus menggunakan domain @gmail.com");
                                    continue;
                                }
                                break;
                            }
                            if (email.equals("0")) break;
                            String password;
                            while (true) {
                                System.out.print("Password: ");
                                password = scanner.nextLine();
                                if (password.equals("0")) break;
                                if (password.isEmpty()) {
                                    System.out.println("password tidak boleh kosong");
                                    continue;
                                }
                                break;
                            }
                            if (password.equals("0")) break;
                            Register regFound = null;
                            for (Register reg : daftarRegister) {
                                if (reg.getEmail().equals(email)) {
                                    regFound = reg;
                                    break;
                                }
                            }
                            if (regFound == null) {
                                System.out.println("Email belum terdaftar.");
                                continue;
                            } else if (!regFound.getPassword().equals(password)) {
                                System.out.println("Email atau password salah.");
                                continue;
                            } else {
                                currentUser = regFound;
                                System.out.println("Login berhasil. Selamat datang, " + currentUser.getName());
                                break;
                            }
                        }
                    } else if (menuLogout == 3) {
                        // Lupa Password (OTP)
                        System.out.print("Masukkan email user: ");
                        String email = scanner.nextLine();
                        Register reg = null;
                        for (Register r : daftarRegister) {
                            if (r.getEmail().equals(email)) {
                                reg = r;
                                break;
                            }
                        }
                        if (reg == null) {
                            System.out.println("User tidak ditemukan.");
                            continue;
                        }
                        ForgotPassword fp = new ForgotPassword(reg.getId(), reg.getName(), reg.getEmail(), reg.getPassword(), reg.getPhoneNumber(), reg.getGender(), reg.getBirthday());
                        System.out.println("Kode OTP Anda: " + fp.getOTPCode());
                        System.out.print("Masukkan OTP: ");
                        int otp = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Masukkan password baru: ");
                        String newPass = scanner.nextLine();
                        fp.resetPasswordWithOTP(otp, newPass);
                        reg.setPassword(newPass);
                    } else if (menuLogout == 4) {
                        System.out.println("Terima kasih telah menggunakan isiAir!");
                        scanner.close();
                        return;
                    } else {
                        System.out.println("Menu tidak tersedia, silahkan pilih sesuai nomor pada menu");
                    }
                }
            } else if (menu == 8) {
                System.out.println("Terima kasih telah menggunakan isiAir!");
                break;
            } else {
                System.out.println("Menu tidak tersedia, silahkan pilih sesuai nomor pada menu");
            }
        }
        scanner.close();
    }
}
