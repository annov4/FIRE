public class Calculator {
    public static double fireCalculator(int startYear) {
        int currentYear = startYear - 2001;
        double maxWithdrawalPercent = 0.5;
        double capital = 1;
        while (capital > 0) {
            double baseExpenses = capital * maxWithdrawalPercent / 100;
            capital = 1;

            for (int indexYear = currentYear; indexYear <= Constants.INFLATION_RATE.length; indexYear++) {

                double stockIndex = calculateStockIndex(indexYear);
                double inflationRate = calculateInflationRate(indexYear);
                double updatedBaseExpenses = calculateUpdatedBaseExpenses(baseExpenses, inflationRate);
                double currentCapital = calculateCurrentCapital(capital, stockIndex, updatedBaseExpenses);
                maxWithdrawalPercent = calculateMaxWithdrawalPercent(maxWithdrawalPercent, currentCapital);

                baseExpenses = updatedBaseExpenses;
                capital = currentCapital;
            }

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

    private static double calculateMaxWithdrawalPercent(double maxWithdrawalPercent, double currentCapital) {
        if (currentCapital > 0) {
            maxWithdrawalPercent += 0.5;
        } else {
            maxWithdrawalPercent -= 0.5;
        }
        return maxWithdrawalPercent;
    }
}


