package strategy.exercise1;

public class TrustTax implements TaxStrategy {

    public static final double TRUST_RATE = 0.35;
    private final TaxPayer taxPayer;

    public TrustTax(TaxPayer taxPayer) {
        this.taxPayer = taxPayer;
    }

    @Override
    public double extortCash() {
        return taxPayer.getIncome() * TRUST_RATE;
    }
}
