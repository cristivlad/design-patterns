package strategy.exercise1;

public class EmployeeTax implements TaxStrategy {

    public static final double EMPLOYEE_RATE = 0.45;
    private final TaxPayer taxPayer;

    public EmployeeTax(TaxPayer taxPayer) {
        this.taxPayer = taxPayer;
    }

    @Override
    public double extortCash() {
        return taxPayer.getIncome() * EMPLOYEE_RATE;
    }
}
