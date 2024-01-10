package strategy.exercise1;

public class CompanyTax implements TaxStrategy {
    public static final double COMPANY_RATE = 0.30;
    private final TaxPayer taxPayer;

    public CompanyTax(TaxPayer taxPayer) {
        this.taxPayer = taxPayer;
    }

    @Override
    public double extortCash() {
        return taxPayer.getIncome() * COMPANY_RATE;
    }
}
