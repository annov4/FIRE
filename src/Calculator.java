public class Calculator {
    public static double fireCalculator(int startYear) {
        int currentYear = startYear;
        double maxWithdrawalPercent = 0;
        double currentCapital = 1;
        while (currentYear <= 2022  || currentCapital > 0) {
            double stockIndex = calculateStockIndex(currentYear);
            double inflationRate = calculateInflationRate(currentYear);
            double baseExpenses = calculateBaseExpenses(currentCapital, inflationRate);
            double withdrawalPercent = calculateWithdrawalPercent(currentYear, baseExpenses, stockIndex);

            if (withdrawalPercent > maxWithdrawalPercent) {
                maxWithdrawalPercent = withdrawalPercent;
            }
            currentCapital -= baseExpenses;
            currentYear++;
        }
        return maxWithdrawalPercent;
    }

    private static double calculateStockIndex(int currentYear) {
        //расчет индекса московской биржи на текущий год
        double stockIndex = (Constants.MOEX_RATE[currentYear] - Constants.MOEX_RATE[currentYear - 1]) / Constants.MOEX_RATE[currentYear - 1];
        return stockIndex;
    }

    private static double calculateInflationRate(int currentYear) {
        double inflationRate = Constants.INFLATION_RATE[currentYear];
        return inflationRate;
    }

    private static double calculateBaseExpenses(double currentCapital, double inflationRate) {
        return currentCapital * (1 + inflationRate / 100);
    }

    private static double calculateWithdrawalPercent(int currentYear, double baseExpenses, double stockIndex) {
        double withdrawalPercent = (1 - baseExpenses) * 100;
        return withdrawalPercent;
    }
}

