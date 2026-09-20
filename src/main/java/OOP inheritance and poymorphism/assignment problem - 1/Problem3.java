class RaceEntry {
    protected String bibNumber;
    protected double entryFee;
    protected double paid;
    private double[] lateFeeHistory = new double[10];
    private int feeCount = 0;

    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4)
            throw new IllegalArgumentException();

        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
    }

    void pay(double amount) {
        paid += amount;
    }

    double getBalanceDue() {
        return entryFee - paid;
    }

    protected void applyLateFee(double amount) {
        entryFee += amount;
        lateFeeHistory[feeCount++] = amount;
    }

    double[] getLateFeeHistory() {
        double[] result = new double[feeCount];

        for (int i = 0; i < feeCount; i++)
            result[i] = lateFeeHistory[i];

        return result;
    }
}

class RunnerEntry extends RaceEntry {
    String category;

    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}