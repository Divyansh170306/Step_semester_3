public class BusTicketAccount {

    protected String bookingId;
    protected double ticketFare;

    static {
        System.out.println("Fleet reconciliation system initialized");
    }

    public BusTicketAccount(String bookingId, double ticketFare) {

        if (bookingId == null || bookingId.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid booking ID");
        }

        if (ticketFare < 0) {
            throw new IllegalArgumentException("Fare cannot be negative");
        }

        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
    }

    public BusTicketAccount(String bookingId) {
        this(bookingId, 0.0);
    }

    public final double calculatePenalty(int minutesLate) {

        if (minutesLate < 0) {
            throw new IllegalArgumentException(
                    "Minutes late cannot be negative");
        }

        if (minutesLate == 0) {
            return 0.0;
        }

        return ticketFare * 0.01 * minutesLate;
    }

    public void processAccount(
            BusTicketAccount account,
            double amount,
            int minutesLate) {

        if (account == null) {
            return;
        }

        double penalty = account.calculatePenalty(minutesLate);

        if (account instanceof Sleeper) {
            System.out.println(
                    "Sleeper account: " + account.bookingId);
            System.out.println("Penalty: " + penalty);
        } else {
            System.out.println(
                    "Regular account: " + account.bookingId);
            System.out.println("Penalty: " + penalty);
        }
    }

    public static void processBatch(
            BusTicketAccount[] accounts,
            double[] amounts,
            int[] minutesLateArray) {

        if (accounts == null ||
            amounts == null ||
            minutesLateArray == null) {

            return;
        }

        int limit = Math.min(
                accounts.length,
                Math.min(amounts.length, minutesLateArray.length)
        );

        int processed = 0;
        int nullSkipped = 0;
        int sleeper = 0;
        int regular = 0;
        double grandTotal = 0.0;

        BusTicketAccount processor =
                new BusTicketAccount("PROCESSOR");

        for (int i = 0; i < limit; i++) {

            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            processor.processAccount(
                    accounts[i],
                    amounts[i],
                    minutesLateArray[i]
            );

            double penalty =
                    accounts[i].calculatePenalty(minutesLateArray[i]);

            grandTotal += penalty;
            processed++;

            if (accounts[i] instanceof Sleeper) {
                sleeper++;
            } else {
                regular++;
            }
        }

        System.out.println();
        System.out.println(processed + " processed");
        System.out.println(nullSkipped + " null skipped");
        System.out.println(sleeper + " sleeper");
        System.out.println(regular + " regular");
        System.out.println("Grand total penalties = "
                + grandTotal);
    }

    public static void main(String[] args) {

        BusTicketAccount[] accounts = {
                new Sleeper("BK001", 2000),
                null,
                new BusTicketAccount("BK002", 1200)
        };

        double[] amounts = {
                1200, 900, 700
        };

        int[] minutesLate = {
                10, 5, 0
        };

        processBatch(
                accounts,
                amounts,
                minutesLate
        );
    }
}


class Sleeper extends BusTicketAccount {

    public Sleeper(String bookingId, double ticketFare) {
        super(bookingId, ticketFare);
    }
}