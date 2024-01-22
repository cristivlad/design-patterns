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

    private double getTaxRate() {
        return 0.40;
    }

    public double calculateTax() {
        return disability || income < 0 ? 0.0 : income * getTaxRate();
    }
}