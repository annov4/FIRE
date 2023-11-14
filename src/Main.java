import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите год");
        try {
            int currentYear = scanner.nextInt();

            if (currentYear < 2002 || currentYear > 2021) {
                throw new IllegalArgumentException("\n" +
                        "Введите год с 2002 по 2021");
            }
            System.out.println(Calculator.fireCalculator(currentYear));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            scanner.close();

        }
    }
}