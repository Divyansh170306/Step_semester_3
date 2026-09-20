class RaceEntry {
    protected String bibNumber;
    protected double entryFee;
    protected double paid;

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

    void announce() {
        System.out.println("Race Entry | Bib: " + bibNumber +
                " | Balance: " + getBalanceDue());
    }
}

class RunnerEntry extends RaceEntry {
    String category;

    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }

    void announce() {
        System.out.println("Runner Entry | Bib: " + bibNumber +
                " | Category: " + category +
                " | Balance: " + getBalanceDue());
    }
}

class EliteRunnerEntry extends RunnerEntry {
    double sponsorBonus;

    public EliteRunnerEntry(String bibNumber, double entryFee,
                            String category, double sponsorBonus) {
        super(bibNumber, entryFee, category);
        this.sponsorBonus = sponsorBonus;
    }

    void announce() {
        System.out.println("Elite Runner | Bib: " + bibNumber +
                " | Category: " + category +
                " | Sponsor Bonus: " + sponsorBonus +
                " | Balance: " + getBalanceDue());
    }
}

class RelayTeamEntry extends RaceEntry {
    int teamSize;

    public RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }

    void announce() {
        System.out.println("Relay Team | Bib: " + bibNumber +
                " | Team Size: " + teamSize +
                " | Balance: " + getBalanceDue());
    }
}

class Main {
    static String classifyGeneration(RaceEntry entry) {
        if (entry instanceof EliteRunnerEntry)
            return "Multilevel descendant (3 generations deep)";
        if (entry instanceof RelayTeamEntry)
            return "Hierarchical sibling (independent branch)";
        return "Base or direct descendant";
    }

    static double getTotalBalanceDue(RaceEntry[] entries) {
        double total = 0;

        for (RaceEntry e : entries)
            total += e.getBalanceDue();

        return total;
    }
}