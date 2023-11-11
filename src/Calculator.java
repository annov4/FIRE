public class Calculator {
    public static double fireCalculator(int startYear) {

        double maxWithdrawalPercent = 0.5;
        double capital = 100;
        double[] moexIndex = Constants.MOEX_RATE;
        double[] inflationRate = Constants.INFLATION_RATE;

        while (capital > 0) {
            // Расчет расходов на текущий год
            double baseExpenses = capital * maxWithdrawalPercent / 100;

            for (int i = startYear - 2001; i <= 20; i++) {
                // Расчет расходов
                baseExpenses = baseExpenses * (1 + inflationRate[i - 1] / 100);
                // Расчет нового капитала после вычета расходов и добавления доходов
                capital = capital * (1 + (moexIndex[i] - moexIndex[i - 1]) / moexIndex[i - 1]) - baseExpenses;
            }
            if (capital > 0) {
                maxWithdrawalPercent += 0.5;

            } else {
                maxWithdrawalPercent -= 0.5;
                break;
            }
        }
        return maxWithdrawalPercent;
    }
}