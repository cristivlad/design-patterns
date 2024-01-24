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

    @Override
    protected double getIncome() {
        return income;
    }

    @Override
    protected double getTaxRate() {
        return 0.29;
    }

    @Override
    protected boolean isTaxExempt() {
        return isNonProfit() || income < 0;
    }
}
