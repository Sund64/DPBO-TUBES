package Gab;

import java.util.Date;

public class MainNotifikasiPembayaran {
    public static void main(String[] args) {
        System.out.println("--- Menguji Notification ---");
        Notification notif1 = new Notification(1, "Budi", "Pesanan Anda telah dikirim.", new Date());
        notif1.send();
        notif1.printInfo();

        Notification notif2 = new Notification(2, "Ani", "Ada promo spesial untuk Anda!", null);
        notif2.printInfo();

        System.out.println("\n--- Menguji Pembayaran ---");
        Pembayaran pembayaran1 = new Pembayaran(150000, "Transfer Bank", "Pending", 101);
        pembayaran1.prosesPembayaran();
        System.out.println("Harga: " + pembayaran1.getPrice());
        System.out.println("Metode Pembayaran: " + pembayaran1.getMetodePembayaran());
        System.out.println("Status: " + pembayaran1.getStatus());
        pembayaran1.printInfo();

        System.out.println("\n--- Menguji QRIS ---");
        QRIS qris1 = new QRIS(75000, "QRIS", "Selesai", 201, 123456789);
        qris1.prosesPembayaran();
        qris1.tampilkanQr();
        qris1.verifikasiPembayaran();
        qris1.printInfo();
        System.out.println("Harga QRIS: " + qris1.getPrice());
        System.out.println("Metode Pembayaran QRIS: " + qris1.getMetodePembayaran());
        System.out.println("Status QRIS: " + qris1.getStatus());
    }
}