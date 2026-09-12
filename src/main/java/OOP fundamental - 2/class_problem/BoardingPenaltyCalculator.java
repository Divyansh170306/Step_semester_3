public final class BoardingPenaltyCalculator {

    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    public final double calculatePenalty(double ticketFare, int minutesLate) {

        if (ticketFare < 0) {
            throw new IllegalArgumentException(
                    "Ticket fare cannot be negative");
        }

        if (minutesLate < 0) {
            throw new IllegalArgumentException(
                    "Minutes late cannot be negative");
        }

        if (minutesLate == 0) {
            return 0.0;
        }

        double penalty = 0.0;

        // Minutes 1-5 : 0.5%
        int firstTier = Math.min(minutesLate, 5);
        penalty += firstTier * ticketFare * 0.005;

        // Minutes 6-15 : 1%
        if (minutesLate > 5) {
            int secondTier = Math.min(minutesLate, 15) - 5;
            penalty += secondTier * ticketFare * 0.01;
        }

        // Minutes 16 onwards : 2%
        if (minutesLate > 15) {
            int thirdTier = minutesLate - 15;
            penalty += thirdTier * ticketFare * 0.02;
        }

        // Minimum flat-fee floor
        double minimumPenalty =
                ticketFare * minimumPenaltyPercent / 100.0;

        return Math.max(penalty, minimumPenalty);
    }

    public static void main(String[] args) {

        BoardingPenaltyCalculator calculator =
                new BoardingPenaltyCalculator(1.0);

        System.out.println(
                calculator.calculatePenalty(1000, 0));

        System.out.println(
                calculator.calculatePenalty(1000, 1));

        System.out.println(
                calculator.calculatePenalty(1000, 16));
    }
}