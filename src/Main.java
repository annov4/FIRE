import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите год");
        try {
            int startYear = scanner.nextInt();

            if (startYear < 2002 || startYear > 2021) {
                throw new IllegalArgumentException("\n" +
                        "Введите год с 2002 по 2021");
            }
            System.out.println(Calculator.fireCalculator(startYear));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            scanner.close();

        }
    }
}