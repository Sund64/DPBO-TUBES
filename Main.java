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
            System.out.println("7. Demo Notifikasi & Pembayaran");
            System.out.println("8. Demo Galon");
            System.out.println("9. Keluar");
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
                    String genderInput = scanner.nextLine();
                    char gender = genderInput.isEmpty() ? 'U' : genderInput.charAt(0);
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
                int choice;
                do {
                    System.out.println("\n-------------- MENU REWARD --------------");
                    System.out.println("Selamat Datang Dihalaman Reward Kami " + currentUser.getName());
                    System.out.println("Poin Anda saat ini: " + currentUser.getPoints());
                    System.out.println("1. Gratis Ongkir (100 poin)");
                    System.out.println("2. Gratis Refil (50 poin)");
                    System.out.println("3. Promo Diskon (25 poin)");
                    System.out.println("0. Keluar");
                    System.out.print("Pilih reward yang ingin ditebus (0/1/2/3): ");
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    Reward selectedReward = null;
                    switch (choice) {
                        case 1:
                            selectedReward = rewards[0];
                            break;
                        case 2:
                            selectedReward = rewards[1];
                            break;
                        case 3:
                            selectedReward = rewards[2];
                            break;
                        case 0:
                            System.out.println("Terima kasih telah menggunakan sistem reward.");
                            break;
                        default:
                            System.out.println("Pilihan tidak valid.");
                            continue;
                    }

                    if (selectedReward != null) {
                        System.out.println("\n----------- Proses Redeem ---------------");
                        selectedReward.redeem(currentUser);
                        System.out.println("Sisa poin Anda: " + currentUser.getPoints());
                    }
                } while (choice != 0);
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
                // Demo Notifikasi & Pembayaran (gabungan MainNotifikasiPembayaran)
                System.out.println("--- Menguji Notification ---");
                Notification notif1 = new Notification(1, "Budi", "Pesanan Anda telah dikirim.", new Date());
                notif1.send();
                notif1.printInfo();

                Notification notif2 = new Notification(2, "Ani", "Ada promo spesial untuk Anda!", null);
                notif2.send();
                notif2.printInfo();

                System.out.println("\n--- Menguji Pembayaran ---");
                Pembayaran pembayaran1 = new Pembayaran(150000, "Transfer Bank", "2024-06-01", 1);
                pembayaran1.prosesPembayaran();
                System.out.println("Harga: " + pembayaran1.getPrice());
                System.out.println("Metode Pembayaran: " + pembayaran1.getMetodePembayaran());
                System.out.println("Status: " + pembayaran1.getStatus());
                pembayaran1.printInfo();

                System.out.println("\n--- Menguji QRIS ---");
                QRIS qris1 = new QRIS(75000, "QRIS", "2024-06-01", 2, 123456789);
                qris1.prosesPembayaran();
                qris1.tampilkanQr();
                qris1.verifikasiPembayaran();
                qris1.printInfo();
                System.out.println("Harga QRIS: " + qris1.getPrice());
                System.out.println("Metode Pembayaran QRIS: " + qris1.getMetodePembayaran());
                System.out.println("Status QRIS: " + qris1.getStatus());
            } else if (menu == 8) {
                // Demo Galon (gabungan Maingalon.java)
                int pilihan;
                do {
                    System.out.println("\n================== DEMO MENU GALON ==================");
                    System.out.println("1. Lihat Daftar Galon");
                    System.out.println("2. Beli Galon Baru");
                    System.out.println("3. Refill Galon");
                    System.out.println("4. Keluar");
                    System.out.print("Pilih menu (1-4): ");
                    pilihan = scanner.nextInt();
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
                            System.out.println("Berhasil Refill galon!");
                            break;
                        case 4:
                            System.out.println("Terima kasih telah menggunakan layanan Demo Galon!");
                            break;
                        default:
                            System.out.println("⚠️ Pilihan tidak valid. Silakan coba lagi.");
                    }
                } while (pilihan != 4);
            } else if (menu == 9) {
                System.out.println("Terima kasih telah menggunakan isiAir!");
                break;
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }
        scanner.close();
    }
}
