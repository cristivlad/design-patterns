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

    public double calculateTax() {
        if (isForeignResident()) {
            return 0.0;
        }
        if (income < 20000) {
            return 0.0;
        } else if (income < 50000) {
            return income * 0.1;
        } else if (income < 100000) {
            return income * 0.25;
        } else {
            return income * 0.45;
        }
    }
}
