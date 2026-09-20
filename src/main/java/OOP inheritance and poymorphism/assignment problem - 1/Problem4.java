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

    String announce() {
        return "Race Entry | Bib: " + bibNumber +
                " | Balance: " + getBalanceDue();
    }
}

class RunnerEntry extends RaceEntry {
    String category;

    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }

    @Override
    String announce() {
        return "Runner Entry | Bib: " + bibNumber +
                " | Category: " + category +
                " | Balance: " + getBalanceDue();
    }
}

class RelayTeamEntry extends RaceEntry {
    int teamSize;

    public RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }

    @Override
    String announce() {
        return "Relay Team | Bib: " + bibNumber +
                " | Team Size: " + teamSize +
                " | Balance: " + getBalanceDue();
    }
}

class Main {
    static String announceAll(RaceEntry[] entries) {
        StringBuilder sb = new StringBuilder();

        for (RaceEntry e : entries) {
            sb.append(e.announce());

            if (e instanceof RelayTeamEntry) {
                RelayTeamEntry r = (RelayTeamEntry) e;
                sb.append(" [Team size via downcast: ")
                  .append(r.teamSize)
                  .append("]");
            }

            sb.append(" | ");
        }

        return sb.toString();
    }
}