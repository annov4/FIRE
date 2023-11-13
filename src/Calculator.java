public class Calculator {
    public static double fireCalculator(int startYear) {
        int indexYear = startYear - 2002;
        double maxWithdrawalPercent = 0.5;
        double capital = 1;
        while (indexYear <= 20) {
            double stockIndex = calculateStockIndex(indexYear);
            double inflationRate = calculateInflationRate(indexYear);
            double baseExpenses = capital * maxWithdrawalPercent / 100;
            double updatedBaseExpenses = calculateUpdatedBaseExpenses(baseExpenses, inflationRate);
            double currentCapital = calculateCurrentCapital(capital,stockIndex,baseExpenses);


            if (currentCapital > 0) {
                maxWithdrawalPercent += 0.5;
            } else {
                maxWithdrawalPercent -= 0.5;
            }

            indexYear++;
        }

        return maxWithdrawalPercent;
    }

    private static double calculateStockIndex(int indexYear) {
        double stockIndex = (Constants.MOEX_RATE[indexYear] - Constants.MOEX_RATE[indexYear - 1]) / Constants.MOEX_RATE[indexYear - 1];
        return stockIndex;
    }

    private static double calculateInflationRate(int indexYear) {
        double inflationRate = Constants.INFLATION_RATE[indexYear - 1];
        return inflationRate;
    }

    private static double calculateUpdatedBaseExpenses(double baseExpenses, double inflationRate) {
        double updatedBaseExpenses = baseExpenses * (1 + inflationRate / 100);
        return updatedBaseExpenses;
    }

    private static double calculateCurrentCapital(double capital, double stockIndex, double updatedBaseExpenses) {
        double currentCapital = capital * (1 + stockIndex) - updatedBaseExpenses;
        return currentCapital;
    }
}