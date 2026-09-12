import java.util.Scanner;

public class PhoneNumberMasker {

    static String maskPhoneNumber(String phone) {
        if (phone.length() <= 4) {
            return phone;
        }

        String lastFour = phone.substring(phone.length() - 4);
        String masked = "";

        for (int i = 0; i < phone.length() - 4; i++) {
            masked = masked + "*";
        }

        return masked + lastFour;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String phone = sc.nextLine();

        System.out.println(maskPhoneNumber(phone));
    }
}