public class Calculator {
    public static double fireCalculator(int startYear) {

        double maxWithdrawalPercent = 0;
        double capital = 100;
        double[] moexIndex = Constants.MOEX_RATE;
        double[] inflationRate = Constants.INFLATION_RATE;

        // Расчет индекса биржи за текущий год
        double moexIndexInStartYear = (moexIndex[startYear - 2002] - moexIndex[startYear - 2003]) / moexIndex[startYear - 2003];

        // Расчет расходов на текущий год
        double baseExpenses = capital * maxWithdrawalPercent / 100;

        // Расчет доходов (прироста капитала) за текущий год
        capital = capital * (1 + moexIndexInStartYear) - baseExpenses;

        while (capital > 0) {
            for (int i = startYear - 2001; i < 20; i++)
                // Расчет нового капитала после вычета расходов и добавления доходов
                capital = capital * (1 + (moexIndex[i] - moexIndex[i - 1] / moexIndex[i - 1])) - baseExpenses * (1 + inflationRate[i - 1]);

            if (capital > 0) {
                maxWithdrawalPercent += 0.5;

            } else {
                break;
            }
        }
        return maxWithdrawalPercent;
    }
}