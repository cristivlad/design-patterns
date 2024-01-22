package templatemethod;

public class Company extends TaxPayer {
    private final boolean nonProfit;
    private final double income;

    public Company(boolean nonProfit, double income) {
        this.nonProfit = nonProfit;
        this.income = income;
    }

    private boolean isNonProfit() {
        return nonProfit;
    }

    public double calculateTax() {
        if (isNonProfit() || income < 0) {
            return 0.0;
        }
        return income * 0.29;
    }
}
