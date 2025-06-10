public class Pembayaran {
	private int price;
	private int id;
	private String metodePembayaran;
	private String status;
	public Pembayaran(int price, String metodePembayaran, String status, int id) {
		super();
		this.price = price;
		this.metodePembayaran = metodePembayaran;
		this.status = status;
		this.id = id;
	}

	public int getPrice() {
		return price;
	}
	public String getStatus() {
		return status;
	}
	public String getMetodePembayaran() {
		return metodePembayaran;
	}
	public void prosesPembayaran() {
		System.out.println("Menunggu pembayaran dari " + this.id);
	}
	public void printInfo() {
		System.out.println("Pembayaran id: " + this.id + " sudah selesai");
	}

	public static void main(String[] args) {
		java.util.Scanner scanner = new java.util.Scanner(System.in);

		System.out.print("Masukkan ID pembayaran: ");
		int id = Integer.parseInt(scanner.nextLine());
		System.out.print("Masukkan harga: ");
		int price = Integer.parseInt(scanner.nextLine());

		System.out.println("Pilih metode pembayaran:");
		System.out.println("1. QRIS");
		System.out.println("2. Cash");
		System.out.print("Pilihan (1/2): ");
		int pilihan = Integer.parseInt(scanner.nextLine());

		String metodePembayaran;
		switch (pilihan) {
			case 1: metodePembayaran = "QRIS"; break;
			case 2: 
				metodePembayaran = "Cash";
				break;
			default: metodePembayaran = "QRIS";
		}

		String status = "Menunggu";

		if ("QRIS".equals(metodePembayaran)) {
			QRIS qris = new QRIS(price, metodePembayaran, status, id, id);
			qris.tampilkanQr();
			System.out.println("======= QR CODE ======");
			System.out.println("| [] []   []   [] [] |");
			System.out.println("|   [] [] [] []   [] |");
			System.out.println("| []   []   [] []    |");
			System.out.println("|   [] []   []   []  |");
			System.out.println("| []   [] []   [] [] |");
			System.out.println("======================");
			qris.prosesPembayaran();
			qris.verifikasiPembayaran();
			qris.printInfo();
		} else if ("Cash".equals(metodePembayaran)) {
			Pembayaran pembayaran = new Pembayaran(price, metodePembayaran, status, id);
			pembayaran.prosesPembayaran();
			System.out.println("Terimakasih sudah membeli, jangan lupa bayar ya!");
			pembayaran.printInfo();
		}

		scanner.close();
	}
}
