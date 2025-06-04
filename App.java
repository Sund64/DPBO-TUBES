package halreward.tubes;
import java.util.Scanner;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Scanner scanner = new Scanner(System.in);

        User user1 = new User(1, "Fadhan", "fadhan@email.com", 150);

        Reward reward1 = new Reward(101, "Gratis Ongkir", 100, "Berlaku untuk 1x refill galon");
        Reward reward2 = new Reward(102, "Gratis Refil", 50, "Refil galon gratis 1x");
        Reward reward3 = new Reward(103, "Promo Diskon", 25, "Diskon 10% untuk isi ulang berikutnya");

        int choice;
        do {
            System.out.println("\n-------------- MENU REWARD --------------");
            System.out.println("Selamat Datang Dihalaman Reward Kami " + user1.getName());
            System.out.println("Poin Anda saat ini: " + user1.getPoints());
            System.out.println("1. Gratis Ongkir (100 poin)");
            System.out.println("2. Gratis Refil (50 poin)");
            System.out.println("3. Promo Diskon (25 poin)");
            System.out.println("0. Keluar");
            System.out.print("Pilih reward yang ingin ditebus (0/1/2/3): ");
            choice = scanner.nextInt();

            Reward selectedReward = null;

            switch (choice) {
                case 1:
                    selectedReward = reward1;
                    break;
                case 2:
                    selectedReward = reward2;
                    break;
                case 3:
                    selectedReward = reward3;
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
                selectedReward.redeem(user1);
                System.out.println("Sisa poin Anda: " + user1.getPoints());
            }
        } while (choice != 0);

        scanner.close();
    }
}

