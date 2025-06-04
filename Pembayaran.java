package DPBO.TUBES;

public class Pembayaran {
	private int price;
	private String metode;
	public Pembayaran(int price, String metode) {
		super();
		this.price = price;
		this.metode = metode;
	}
	public int getPrice() {
		return price;
	}
	public void printInfo() {
		System.out.println("Pilih metode pembayaran");
		System.out.println("QRIS/CASH");
	}
	
	
}
