import java.util.ArrayList;

public class Keranjang {
    private ArrayList<String> items = new ArrayList<>();

    public void tambahItem(String item) {
        items.add(item);
    }

    public void hapusItem(String item) {
        items.remove(item);
    }

    // Tampilkan keranjang dalam bentuk tabel dengan harga dan total
    public void tampilkanKeranjang(ArrayList<String> daftarPembelianGalon, Galon[] galons) {
        System.out.println("=== Isi Keranjang ===");
        if (daftarPembelianGalon == null || daftarPembelianGalon.isEmpty()) {
            System.out.println("Keranjang kosong.");
            return;
        }
        System.out.printf("| %-3s | %-10s | %-15s | %-10s | %-10s |\n", "No", "Brand", "Jenis", "Jumlah", "Harga");
        System.out.println("---------------------------------------------------------------");
        int total = 0;
        for (int i = 0; i < daftarPembelianGalon.size(); i++) {
            String item = daftarPembelianGalon.get(i);
            // Format item: Brand xJumlah (Jenis)
            // Contoh: Aqua x2 (Dengan galon baru)
            String[] parts = item.split(" x| \\(");
            String brand = parts[0].trim();
            int jumlah = Integer.parseInt(parts[1].split(" ")[0]);
            String jenis = parts[2].replace(")", "").trim();

            int hargaSatuan = 0;
            for (Galon g : galons) {
                if (g.getBrand().equalsIgnoreCase(brand)) {
                    if (jenis.equalsIgnoreCase("Dengan galon baru")) {
                        hargaSatuan = g.getPrice();
                    } else {
                        hargaSatuan = g.getPrice() - 30000;
                    }
                    break;
                }
            }
            int hargaTotal = hargaSatuan * jumlah;
            total += hargaTotal;
            System.out.printf("| %-3d | %-10s | %-15s | %-10d | Rp%-9d |\n", i + 1, brand, jenis, jumlah, hargaTotal);
        }
        System.out.println("---------------------------------------------------------------");
        System.out.printf("%44s Rp%d\n", "Total:", total);
    }

    // Overload agar tetap kompatibel dengan pemanggilan lama
    public void tampilkanKeranjang() {
        tampilkanKeranjang(this.items, new Galon[0]);
    }

    public void kosongkanKeranjang() {
        items.clear();
    }

    public ArrayList<String> getItems() {
        return items;
    }
}
