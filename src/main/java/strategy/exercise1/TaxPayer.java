package strategy.exercise1;

public class TaxPayer {
    public static final int COMPANY = 0;
    public static final int EMPLOYEE = 1;
    public static final int TRUST = 2;

    private final double income;
    private final int type;
    private TaxStrategy strategy;

    public TaxPayer(int type, double income) {
        this.type = type;
        this.income = income;
    }

    public double getIncome() {
        return income;
    }

    public double extortCash() {
        switch (type) {
            case COMPANY:
                strategy = new CompanyTax(this);
                return strategy.extortCash();
            case EMPLOYEE:
                strategy = new EmployeeTax(this);
                return strategy.extortCash();
            case TRUST:
                strategy = new TrustTax(this);
                return strategy.extortCash();
            default:
                throw new IllegalArgumentException();
        }
    }
}