import java.util.*;

public class Main {
    private static ArrayList<Register> daftarRegister = new ArrayList<>();
    private static User currentUser = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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

        while (true) {
            System.out.println("\n===== MENU UTAMA isiAir =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Reward");
            System.out.println("4. Galon");
            System.out.println("5. Delivery Status");
            System.out.println("6. Lupa Password");
            System.out.println("7. Keluar");
            System.out.print("Pilih menu: ");
            int menu = scanner.nextInt();
            scanner.nextLine();

            if (menu == 1) {
                // Register
                try {
                    System.out.print("Nama: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Alamat: ");
                    String address = scanner.nextLine();
                    System.out.print("Password: ");
                    String password = scanner.nextLine();
                    System.out.print("Nomor Telepon: ");
                    int phoneNumber = Integer.parseInt(scanner.nextLine());
                    System.out.print("Jenis Kelamin (L/P): ");
                    char gender = scanner.nextLine().isEmpty() ? 'U' : scanner.nextLine().charAt(0);
                    System.out.print("Tanggal Lahir (yyyy-mm-dd): ");
                    String birthday = scanner.nextLine();

                    Register reg = new Register(
                        daftarRegister.size() + 1, // id
                        name,
                        email,
                        password,
                        phoneNumber,
                        gender,
                        birthday,
                        address
                    );
                    daftarRegister.add(reg);
                    reg.printInfo();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (menu == 2) {
                // Login
                System.out.print("Nama: ");
                String name = scanner.nextLine();
                System.out.print("Password: ");
                String password = scanner.nextLine();
                boolean found = false;
                for (Register reg : daftarRegister) {
                    if (reg.getName().equals(name) && reg.getPassword().equals(password)) {
                        currentUser = reg;
                        found = true;
                        break;
                    }
                }
                if (found) {
                    System.out.println("Login berhasil. Selamat datang, " + currentUser.getName());
                } else {
                    System.out.println("Login gagal.");
                }
            } else if (menu == 3) {
                // Reward
                if (currentUser == null) {
                    System.out.println("Silakan login terlebih dahulu.");
                    continue;
                }
                // Cast ke User agar bisa akses points
                User userReward = currentUser;
                System.out.println("\n-------------- MENU REWARD --------------");
                System.out.println("Poin Anda saat ini: " + userReward.getPoints());
                for (int i = 0; i < rewards.length; i++) {
                    System.out.println((i+1) + ". " + rewards[i].getName() + " (" + rewards[i].getPointCost() + " poin)");
                }
                System.out.println("0. Kembali");
                System.out.print("Pilih reward yang ingin ditebus: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice > 0 && choice <= rewards.length) {
                    rewards[choice-1].redeem(userReward);
                    System.out.println("Sisa poin Anda: " + userReward.getPoints());
                }
            } else if (menu == 4) {
                // Galon
                while (true) {
                    System.out.println("\n================== MENU GALON ==================");
                    System.out.println("1. Lihat Daftar Galon");
                    System.out.println("2. Beli Galon Baru");
                    System.out.println("3. Refill Galon");
                    System.out.println("4. Keluar");
                    System.out.print("Pilih menu (1-4): ");
                    int pilihan = scanner.nextInt();
                    scanner.nextLine();

                    switch (pilihan) {
                        case 1:
                            System.out.println("\n========== DAFTAR GALON ==========");
                            System.out.printf("| %-3s | %-10s | %-7s | %-10s | %-5s |\n",
                                    "ID", "Brand", "Volume", "Harga", "Stok");
                            System.out.println("------------------------------------------------");
                            for (Galon g : galons) {
                                g.printInfo();
                            }
                            break;
                        case 2:
                            System.out.print("\nMasukkan ID galon yang ingin dibeli: ");
                            int idBeli = scanner.nextInt();
                            scanner.nextLine();
                            boolean ditemukan = false;
                            for (Galon g : galons) {
                                if (g.getId() == idBeli) {
                                    ditemukan = true;
                                    if (g.getStock() > 0) {
                                        g.reduceStock(1);
                                        System.out.println("✅ Pembelian berhasil. 1 galon " + g.getBrand() + " telah dibeli.");
                                    } else {
                                        System.out.println("❌ Stok habis.");
                                    }
                                }
                            }
                            if (!ditemukan) {
                                System.out.println("❌ Galon dengan ID tersebut tidak ditemukan.");
                            }
                            break;
                        case 3:
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
                        case 4:
                            // Kembali ke menu utama
                            System.out.println("Kembali ke menu utama.");
                            break;
                        default:
                            System.out.println("⚠️ Pilihan tidak valid. Silakan coba lagi.");
                    }
                    if (pilihan == 4) break;
                }
            } else if (menu == 5) {
                // Delivery Status
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
            } else if (menu == 6) {
                // Lupa Password (OTP)
                System.out.print("Masukkan nama user: ");
                String name = scanner.nextLine();
                Register reg = null;
                for (Register r : daftarRegister) {
                    if (r.getName().equals(name)) {
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
            } else if (menu == 7) {
                System.out.println("Terima kasih telah menggunakan isiAir!");
                break;
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }
        scanner.close();
    }
}
