public class Calculator {
    public static double fireCalculator(int currentYear) {
        final int BASE_YEAR = 2001; // переменная для подсчета индекса года, где 2002 = 1 и т.д.
        int startYear = currentYear - BASE_YEAR;
        final double PERCENT_VARIABLE = 0.5; // процентная переменная, устнавливаем шаг, с которым будет меняться максимальный процент
        double maxWithdrawalPercent = 0;
        maxWithdrawalPercent += PERCENT_VARIABLE;
        double capital = 1;
        double baseExpenses;
        while (capital > 0) {
            capital = 100;
            baseExpenses = capital * maxWithdrawalPercent / 100;
            for (int indexYear = startYear; indexYear < Constants.INFLATION_RATE.length; indexYear++) {

                double stockIndex = calculateStockIndex(indexYear);
                double inflationRate = calculateInflationRate(indexYear);
                baseExpenses = calculateUpdatedBaseExpenses(baseExpenses, inflationRate);

                double currentCapital = calculateCurrentCapital(capital, stockIndex, baseExpenses);
                capital = currentCapital;
            }

            maxWithdrawalPercent = calculateMaxWithdrawalPercent(maxWithdrawalPercent, capital, PERCENT_VARIABLE);
        }
        return maxWithdrawalPercent;
    }

    private static double calculateStockIndex(int indexYear) {
        double stockIndex = (Constants.MOEX_RATE[indexYear]/Constants.MOEX_RATE[indexYear - 1]);
        return stockIndex;
    }

    private static double calculateInflationRate(int indexYear) {
        double inflationRate = Constants.INFLATION_RATE[indexYear];
        return inflationRate;
    }

    private static double calculateUpdatedBaseExpenses(double baseExpenses, double inflationRate) {
        baseExpenses = baseExpenses * (1 + inflationRate / 100);
        return baseExpenses;
    }


    private static double calculateCurrentCapital(double capital, double stockIndex, double baseExpenses) {
        capital = capital - baseExpenses;//
        capital = capital * stockIndex;
        return capital;
    }

    private static double calculateMaxWithdrawalPercent(double maxWithdrawalPercent, double currentCapital, double PERCENT_VARIABLE ) {
        if (currentCapital > 0) {
            return maxWithdrawalPercent + PERCENT_VARIABLE;
        } else {
            return maxWithdrawalPercent - PERCENT_VARIABLE;
        }

    }
}