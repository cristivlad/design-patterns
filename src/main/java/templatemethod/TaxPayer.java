package templatemethod;

public abstract class TaxPayer {
    /**
     * This will become our template method.
     */
    public final double calculateTax() {
        return isTaxExempt() ? 0.0 : getIncome() * getTaxRate();
    }

    protected abstract double getIncome();
    protected abstract double getTaxRate();
    protected abstract boolean isTaxExempt();
}
