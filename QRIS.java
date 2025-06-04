package DPBO.TUBES;

public class QRIS extends Pembayaran{
	private int QrisID;

	public QRIS(int price, String metode, int qrisID) {
		super(price, metode);
		QrisID = qrisID;
	}
	public void tampilkanQr() {
		System.out.println(QrisID);
	}
	public void pengecekanPembayaran() {
		return;
	}
	
}
