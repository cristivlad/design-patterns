package templatemethod;

public class Trust extends TaxPayer {
    private final boolean disability;
    private final double income;

    public Trust(boolean disability, double income) {
        this.disability = disability;
        this.income = income;
    }

    public boolean isDisability() {
        return disability;
    }

    protected double getTaxRate() {
        return 0.40;
    }

    @Override
    protected boolean isTaxExempt() {
        return disability || income < 0;
    }

    @Override
    protected double getIncome() {
        return income;
    }
}