package halgalon;
import  java.util.Scanner;

public class MainTest {
	public static void main( String[] args )
    {
		        Scanner input = new Scanner(System.in);

		       
		        galonisi[] galons = {
		                new galonisi(1, 19.0, "Aqua", 50000, 10),
		                new galonisi(2, 19.0, "Club", 45000, 7),
		                new galonisi(3, 19.0, "ron 88", 45000, 11)
		        };

		        Refill[] refills = {
		                new Refill(1, 19.0, "Aqua", 50000, 10, 12000),
		                new Refill(2, 19.0, "Club", 45000, 7, 10000),
		                new Refill(3, 19.0, "ron 88", 45000, 7, 7000),
		        };

		        int pilihan;

		        do {
		            System.out.println("\n================== MENU GALON ==================");
		            System.out.println("1. Lihat Daftar Galon");
		            System.out.println("2. Beli Galon Baru");
		            System.out.println("3. Refill Galon");
		            System.out.println("4. Keluar");
		            System.out.print("Pilih menu (1-4): ");
		            pilihan = input.nextInt();

		            switch (pilihan) {
		                case 1:
		                    System.out.println("\n========== DAFTAR GALON ==========");
		                    System.out.printf("| %-3s | %-10s | %-7s | %-10s | %-5s |\n",
		                            "ID", "Brand", "Volume", "Harga", "Stok");
		                    System.out.println("------------------------------------------------");
		                    for (galonisi g : galons) {
		                        g.printInfo();
		                    }
		                    break;

		                case 2:
		                    System.out.print("\nMasukkan ID galon yang ingin dibeli: ");
		                    int idBeli = input.nextInt();
		                    boolean ditemukan = false;

		                    for (galonisi g : galons) {
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
		                    int idRefill = input.nextInt();
		                    System.out.print("Jumlah galon yang ingin direfill: ");
		                    int jumlahRefill = input.nextInt();

		                    System.out.println("Berhasil Refill galon!");
		                    break;

		                case 4:
		                    System.out.println("Terima kasih telah menggunakan layanan GalonApp!");
		                    break;

		                default:
		                    System.out.println("⚠️ Pilihan tidak valid. Silakan coba lagi.");
		            }

		        } while (pilihan != 4);

		        input.close();
		    }
		}

    

