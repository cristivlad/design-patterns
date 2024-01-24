package templatemethod;

public class Employee extends TaxPayer {
    private final boolean foreignResident;
    private final double income;

    public Employee(boolean foreignResident,
                    double income) {
        this.foreignResident = foreignResident;
        this.income = income;
    }

    private boolean isForeignResident() {
        return foreignResident;
    }

    @Override
    protected double getIncome() {
        return income;
    }

    @Override
    protected double getTaxRate() {
        if (income < 20000) {
            return 0.0;
        } else if (income < 50000) {
            return  0.1;
        } else if (income < 100000) {
            return 0.25;
        } else {
            return 0.45;
        }
    }

    @Override
    protected boolean isTaxExempt() {
        return isForeignResident();
    }
}
