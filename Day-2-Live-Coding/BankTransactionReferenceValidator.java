import java.util.Scanner;

public class BankTransactionReferenceValidator {
    static String normalizeReference(String raw) {
        String reference = raw.trim();
        if (reference.length() < 3) return reference.toUpperCase();
        return reference.substring(0, 3).toUpperCase() + reference.substring(3);
    }

    static String validateAndFormat(String reference) {
        if (reference.length() != 14) return "Invalid: reference must be exactly 14 characters";
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(reference.charAt(i)))
                return "Invalid: bank code must be 3 letters";
        }
        for (int i = 3; i < reference.length(); i++) {
            if (!Character.isDigit(reference.charAt(i)))
                return "Invalid: date and sequence must be digits";
        }
        String date = reference.substring(3, 9);
        StringBuilder formatted = new StringBuilder("[");
        formatted.append(reference.substring(0, 3)).append("] DATE: ")
                .append(date, 0, 2).append('/').append(date, 2, 4).append('/')
                .append(date, 4, 6).append(" | SEQ: ").append(reference.substring(9));
        return formatted.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String normalized = normalizeReference(sc.nextLine());
        System.out.println(validateAndFormat(normalized));
        sc.close();
    }
}
