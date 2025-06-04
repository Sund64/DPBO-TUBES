import java.util.Scanner;
import java.util.ArrayList;

public class ForgotPassword {
    private int OTPCode;
    private String email;
    private static final int FIXED_OTP_CODE = 1234;

    public ForgotPassword(String email) {
        this.email = email;
        this.OTPCode = FIXED_OTP_CODE;
    }

    public int getOTPCode() {
        return OTPCode;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Kode OTP Anda adalah: " + OTPCode;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> emailList = new ArrayList<>(); 

        String email;
        do {
            System.out.print("Masukkan email Anda: ");
            email = scanner.nextLine();

            if (!email.endsWith("@gmail.com")) {
                System.out.println("Email tidak valid! Masukkan email yang benar (harus @gmail.com).");
            } else {
                emailList.add(email);
            }
        } while (!email.endsWith("@gmail.com"));

        ForgotPassword fp = new ForgotPassword(email);
        System.out.println(fp);

        scanner.close();
    }
}
