class RaceEntry {
    protected String bibNumber;
    protected double entryFee;
    protected double paid;

    private static int bibCounter = 0;
    private final String entryCode;

    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4)
            throw new IllegalArgumentException();

        this.bibNumber = bibNumber;
        this.entryFee = entryFee;

        bibCounter++;
        entryCode = "ENTRY" + bibCounter;
    }

    void pay(double amount) {
        paid += amount;
    }

    void pay(double amount, String mode) {
        pay(amount);
        System.out.println("Paying via " + mode);
    }

    double getBalanceDue() {
        return entryFee - paid;
    }

    static boolean isValidDiscountCode(String code) {
        if (code == null || code.length() != 5)
            return false;

        return code.charAt(0) == 'M'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isDigit(code.charAt(3))
                && Character.isUpperCase(code.charAt(4));
    }

    static int getBibCounter() {
        return bibCounter;
    }

    static String settleNight(RaceEntry[] entries) {
        int processed = 0;
        int skipped = 0;
        int relay = 0;
        int individual = 0;

        for (RaceEntry e : entries) {
            if (e == null) {
                skipped++;
                continue;
            }

            processed++;

            if (e instanceof RelayTeamEntry)
                relay++;
            else
                individual++;
        }

        return processed + " processed | " + skipped +
                " null skipped | " + relay +
                " relay | " + individual + " individual";
    }
}

class RunnerEntry extends RaceEntry {
    String category;

    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }
}

class EliteRunnerEntry extends RunnerEntry {
    double sponsorBonus;

    public EliteRunnerEntry(String bibNumber, double entryFee,
                            String category, double sponsorBonus) {
        super(bibNumber, entryFee, category);
        this.sponsorBonus = sponsorBonus;
    }
}

class RelayTeamEntry extends RaceEntry {
    int teamSize;

    public RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }
}