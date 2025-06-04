public class Refill extends Galon{
	private int refillPrice;

	public Refill(int id, double volume, String brand, int price, int stock, int refillPrice) {
		super(id, volume, brand, price, stock);
		this.refillPrice = refillPrice;
	}

	public int getRefillPrice() {
		return refillPrice;
	}
	
	public void printRefillInfo() {
        System.out.printf("| %-3d | %-10s | %-6.1fL | Rp%-8d |\n",
                getId(), getBrand(), getVolume(), refillPrice);
    }
}
