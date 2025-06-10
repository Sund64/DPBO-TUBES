import java.util.Scanner;

public class MainChatAndChatChannel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Admin admin = new Admin(1, "Admin", "admin@example.com", "admin123", "Kosultansi");

        User user = new User(2, "Dina", "dina@email.com", "user123", "0824324234", 'L', "2000-01-01");
        ChatChannel channel = new ChatChannel(1, admin, user);

        channel.sendMessage("Halo " + user.getName() + ", apakah ada yang bisa saya bantu?", admin);
        channel.printChatHistory();

        while (true) {
            System.out.print("\nUser, masukkan pesan ('exit' untuk keluar): ");
            String message = scanner.nextLine();

            if (message.equalsIgnoreCase("exit")) {
                break;
            }

            channel.sendMessage(message, user);
            channel.sendMessage("Baik kak permintaan anda akan kami proses", admin);
            channel.printChatHistory();
        }

        System.out.println("Percakapan berakhir.");
        scanner.close();
    }
}
