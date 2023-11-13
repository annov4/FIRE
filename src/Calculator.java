public class Calculator {
    public static double fireCalculator(int currentYear) {
        int baseYear = 2001; // переменная для хранения базового года, с 2002 мы начинаем копить
        int startYear = currentYear - baseYear;
        double percentVariable = 0.5; // процентная переменная, устнавливаем шаг, с которым будет меняться максимальный процент
        double maxWithdrawalPercent = 0;
        maxWithdrawalPercent += percentVariable;
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

            maxWithdrawalPercent = calculateMaxWithdrawalPercent(maxWithdrawalPercent, capital, percentVariable);
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

    private static double calculateMaxWithdrawalPercent(double maxWithdrawalPercent, double currentCapital, double percentVariable ) {
        if (currentCapital > 0) {
            return maxWithdrawalPercent += percentVariable;
        } else {
            return maxWithdrawalPercent -= percentVariable;
        }

    }
}